package defsu.sc.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObjectCore {
    private static final Logger logger = LoggerFactory.getLogger(ObjectCore.class);
    public static String CRITERIA_SEARCH = "@search";
    public static String CRITERIA_SORT = "@sort";
    public static String CRITERIA_ORDER_ASC = "A";
    public static String CRITERIA_ORDER_DESC = "D";

    // SORT PARAMS LIKE "field1:asc,field2:desc"
    // SEARCH PARAMS LIKE "field1::searchValue::=,field2::searchValue::LIKE,field3::searchValue::NOTLIKE,field6::searchValue::<>,field7::searchValue::STARTSWITH,field8::searchValue::ENDSWITH"

    public static void setFieldsToDefault(SuRecord record) {
        SuRecord.RecordProps props = record.getField();
        SuField.FieldList fieldList = props.fields;
        for (SuField field : fieldList.getFields()) {
            try {
                if(field.getDefaultValue() == null){
                    continue;
                }
                setFieldValue(record, field.getDataName(), field.getDefaultValue());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void setFieldValue(SuRecord r, String fieldName, Object value) {
        SuField field = r.getField().fields.get(fieldName);
        if (field != null) {
            String accessorName = generateAccessorName("set", fieldName);
            try {
                Class<?> valueClass = determineValueClass(value);
                Method m = r.getClass().getMethod(accessorName, valueClass);
                m.invoke(r, value);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private static Class<?> determineValueClass(Object value) {
        Class<?> clazz = value.getClass();
        if (clazz == String.class) {
            return String.class;
        }
        if (clazz.equals(Boolean.class)) {
            return Boolean.TYPE;
        } else if (clazz.equals(Integer.class)) {
            return Integer.TYPE;
        } else if (clazz.equals(Float.class)) {
            return Float.TYPE;
        } else if (clazz.equals(Double.class)) {
            return Double.TYPE;
        }
        return clazz;
    }

    private static String generateAccessorName(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).replace("i", "I").toUpperCase(Locale.getDefault()) + fieldName.substring(1);
    }

    public static Object getFieldValue(SuRecord r, String fieldName) {
        SuField field = r.getField().fields.get(fieldName);
        if (field != null) {
            String accessorName = generateAccessorName("get", fieldName);
            try {
                Method m = r.getClass().getMethod(accessorName);
                return m.invoke(r);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return "ErrorInvoke";
    }

    public static Object getRecordToPojo(SuRecord record) {
        Object pojo = null;
        try {
            Class<?> superClass = record.getClass().getSuperclass();
            pojo = superClass.getDeclaredConstructor().newInstance();
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(record);
                field.set(pojo, value);
            }
            return pojo;
        } catch (Exception e) {
            logger.error("SuRecord to POJO dönüşüm hatası: {}", e.getMessage(), e);
            return record;
        }
    }

    public static void copyPojoToRecord(Object pojo, SuRecord record) {
        try {
            Field[] fields = pojo.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(pojo);
                field.set(record, value);
            }
        } catch (Exception e) {
            logger.error("POJO to SuRecord kopyalama hatası: {}", e.getMessage(), e);
        }
    }

    public static void load(SuRecord r, Long id) {
        SuRecord loadedRecord = HibernateCore.get(id, r.getClass());
        if (loadedRecord != null) {
            loadedRecord.process();
            copyRecordValues(loadedRecord, r);
        }
    }

    private static void copyRecordValues(SuRecord source, SuRecord target) {
        try {
            Class<?> entityClass = source.getClass().getSuperclass();
            Field[] entityFields = entityClass.getDeclaredFields();
            for (Field field : entityFields) {
                field.setAccessible(true);
                Object value = field.get(source);
                field.set(target, value);
            }

            Class<?> recordClass = source.getClass();
            Field[] recordFields = recordClass.getDeclaredFields();

            for (Field field : recordFields) {
                field.setAccessible(true);
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    Object value = field.get(source);
                    field.set(target, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Record değerlerini kopyalama hatası: " + e.getMessage(), e);
        }
    }

    public static ListResult list(SuRecord record) {
        ListResult result = new ListResult();


        return result;
    }


    public static List<OrderParams> splitSortParam(SuRecord r, String sortParam){
        List<OrderParams> orderParams = new ArrayList<>();
        SuField.FieldList fieldList = r.getField().fields;
        String[] params = sortParam.split(",");
        for (String param : params) {
            if(param.split(":").length != 2){
                continue;
            }
            String fieldName = param.split(":")[0];
            String order = param.split(":")[1];
            SuField field = fieldList.get(fieldName);
            if(field != null){
                OrderParams orderP = new OrderParams();
                orderP.order = order;
                orderP.fieldName = fieldName;
                orderP.databaseRecord = field.getDatabaseRecord();
                orderParams.add(orderP);
            }
        }
        return orderParams;
    }

    public static List<SearchParams> splitSearchParam(SuRecord r, String searchParam){
        List<SearchParams> searchParams = new ArrayList<>();
        SuField.FieldList fieldList = r.getField().fields;
        String[] params = searchParam.split(",");
        for (String param : params) {
            if(param.split("::").length != 3){
                continue;
            }
            String fieldName = param.split("::")[0];
            String value = param.split("::")[1];
            String type = param.split("::")[2];
            SuField field = fieldList.get(fieldName);
            if(field != null && value != null && type != null){
                SearchParams searchP = new SearchParams();
                searchP.fieldName = fieldName;
                searchP.type = type;
                searchP.searchValue = value;
                searchP.databaseRecord = field.getDatabaseRecord();
                searchParams.add(searchP);
            }
        }
        return searchParams;
    }

    public static class ListResult implements Serializable {
        public List<SuRecord> records;
        public int total;
        public int page;
    }

    private static class OrderParams{
        public String fieldName;
        public String order;
        public String databaseRecord;
    }

    private static class SearchParams{
        public String fieldName;
        public String searchValue;
        public String type;
        public String databaseRecord;
    }
}
