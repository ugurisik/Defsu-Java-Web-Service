package defsu.sc.core;

import defsu.sc.utils.SystemUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectCore {
    private static final Logger logger = LoggerFactory.getLogger(ObjectCore.class);
    public static String PAGE_SIZE = "30";

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

    public static ListResult list(SuRecord record, String searchParam, String sortParam, int page, int pageSize) {
        ListResult result = new ListResult();
        result.page = page;

        try {
            HibernateCore instance = HibernateCore.getInstance();
            EntityManager em = instance.getEntityManager();

            // Criteria API için Builder ve Query oluştur
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object> query = cb.createQuery();
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);

            // Entity sınıfını al
            Class<?> entityClass = record.getClass().getSuperclass();

            // From clause oluştur
            Root<?> root = query.from(entityClass);
            Root<?> countRoot = countQuery.from(entityClass);

            // Select clause oluştur
            query.select(root);
            countQuery.select(cb.count(countRoot));

            // Join ve Where koşullarını ekle
            List<SearchParams> searchParams = searchParam != null ? splitSearchParam(record, searchParam) : new ArrayList<>();
            Map<String, Join<?, ?>> joins = new HashMap<>();
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> countPredicates = new ArrayList<>();

            // Search parametrelerini işle
            for (SearchParams param : searchParams) {
                Predicate predicate = null;
                Predicate countPredicate = null;

                // Join gerekiyorsa ekle
                if (param.databaseRecord != null && !param.databaseRecord.isEmpty()) {
                    try {
                        // Join entity sınıfını al
                        Class<?> joinEntityClass = Class.forName(param.databaseRecord);

                        // İlişkili alanı bul (genellikle joinEntityId şeklinde olur)
                        String joinFieldName = param.fieldName;

                        // Eğer join daha önce yapılmamışsa ekle
                        if (!joins.containsKey(param.databaseRecord)) {
                            Join<?, ?> join = root.join(joinFieldName, JoinType.LEFT);
                            joins.put(param.databaseRecord, join);

                            // Count query için de join ekle
                            Join<?, ?> countJoin = countRoot.join(joinFieldName, JoinType.LEFT);
                        }

                        // Join yapıldıktan sonra filtreleri ekle
                        Join<?, ?> join = joins.get(param.databaseRecord);

                        // Arama tipine göre predicate oluştur
                        switch (param.type.toUpperCase()) {
                            case "=":
                                predicate = cb.equal(join.get(param.fieldName), param.searchValue);
                                countPredicate = cb.equal(join.get(param.fieldName), param.searchValue);
                                break;
                            case "LIKE":
                                predicate = cb.like(join.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                                countPredicate = cb.like(join.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                                break;
                            case "NOTLIKE":
                                predicate = cb.notLike(join.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                                countPredicate = cb.notLike(join.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                                break;
                            case "<>":
                                predicate = cb.notEqual(join.get(param.fieldName), param.searchValue);
                                countPredicate = cb.notEqual(join.get(param.fieldName), param.searchValue);
                                break;
                            case "STARTSWITH":
                                predicate = cb.like(join.get(param.fieldName).as(String.class), param.searchValue + "%");
                                countPredicate = cb.like(join.get(param.fieldName).as(String.class), param.searchValue + "%");
                                break;
                            case "ENDSWITH":
                                predicate = cb.like(join.get(param.fieldName).as(String.class), "%" + param.searchValue);
                                countPredicate = cb.like(join.get(param.fieldName).as(String.class), "%" + param.searchValue);
                                break;
                            default:
                                predicate = cb.equal(join.get(param.fieldName), param.searchValue);
                                countPredicate = cb.equal(join.get(param.fieldName), param.searchValue);
                                break;
                        }
                    } catch (ClassNotFoundException e) {
                        logger.error("Join entity class bulunamadı: {}", param.databaseRecord, e);
                    }
                } else {
                    // Normal alan sorgusu (join olmadan)
                    // Arama tipine göre predicate oluştur
                    switch (param.type.toUpperCase()) {
                        case "=":
                            predicate = cb.equal(root.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            countPredicate = cb.equal(countRoot.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            break;
                        case "LIKE":
                            predicate = cb.like(root.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                            countPredicate = cb.like(countRoot.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                            break;
                        case "NOTLIKE":
                            predicate = cb.notLike(root.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                            countPredicate = cb.notLike(countRoot.get(param.fieldName).as(String.class), "%" + param.searchValue + "%");
                            break;
                        case "<>":
                            predicate = cb.notEqual(root.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            countPredicate = cb.notEqual(countRoot.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            break;
                        case "STARTSWITH":
                            predicate = cb.like(root.get(param.fieldName).as(String.class), param.searchValue + "%");
                            countPredicate = cb.like(countRoot.get(param.fieldName).as(String.class), param.searchValue + "%");
                            break;
                        case "ENDSWITH":
                            predicate = cb.like(root.get(param.fieldName).as(String.class), "%" + param.searchValue);
                            countPredicate = cb.like(countRoot.get(param.fieldName).as(String.class), "%" + param.searchValue);
                            break;
                        default:
                            predicate = cb.equal(root.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            countPredicate = cb.equal(countRoot.get(param.fieldName), convertValue(param.searchValue, record.getField().fields.get(param.fieldName)));
                            break;
                    }
                }

                if (predicate != null) {
                    predicates.add(predicate);
                    countPredicates.add(countPredicate);
                }
            }

            // Standart filtreler ekle (visible=true vb.)
            try {
                predicates.add(cb.equal(root.get("visible"), true));
                countPredicates.add(cb.equal(countRoot.get("visible"), true));
            } catch (Exception e) {
                // visible alanı yoksa geç
            }

            // Where clause ekle
            if (!predicates.isEmpty()) {
                query.where(cb.and(predicates.toArray(new Predicate[0])));
                countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
            }

            // Order by ekle
            if (sortParam != null && !sortParam.isEmpty()) {
                List<OrderParams> orderParams = splitSortParam(record, sortParam);
                List<Order> orders = new ArrayList<>();

                for (OrderParams param : orderParams) {
                    if (param.databaseRecord != null && !param.databaseRecord.isEmpty()) {
                        // Join ile sıralama
                        if (joins.containsKey(param.databaseRecord)) {
                            Join<?, ?> join = joins.get(param.databaseRecord);
                            if ("asc".equalsIgnoreCase(param.order)) {
                                orders.add(cb.asc(join.get(param.fieldName)));
                            } else {
                                orders.add(cb.desc(join.get(param.fieldName)));
                            }
                        }
                    } else {
                        // Normal alan sıralaması
                        if ("asc".equalsIgnoreCase(param.order)) {
                            orders.add(cb.asc(root.get(param.fieldName)));
                        } else {
                            orders.add(cb.desc(root.get(param.fieldName)));
                        }
                    }
                }

                if (!orders.isEmpty()) {
                    query.orderBy(orders);
                }
            }

            // Toplam kayıt sayısını al
            Long totalCount = em.createQuery(countQuery).getSingleResult();
            result.total = totalCount.intValue();

            // Sayfalama parametrelerini ayarla
            TypedQuery<Object> typedQuery = em.createQuery(query);
            typedQuery.setFirstResult((page - 1) * pageSize);
            typedQuery.setMaxResults(pageSize);

            // Sonuçları al
            List<Object> entities = typedQuery.getResultList();
            SuField.FieldList f = null;
            // Entity listesini SuRecord listesine dönüştür
            result.records = new ArrayList<>();
            for (Object entity : entities) {
                SuRecord newRecord = record.getClass().getDeclaredConstructor().newInstance();
                ObjectCore.copyPojoToRecord(entity, newRecord);
                newRecord.process();
                f = newRecord.getField().fields;
                result.records.add(newRecord);
            }
            result.fieldList = f;

        } catch (Exception e) {
            logger.error("Liste sorgusu hatası: {}", e.getMessage(), e);
            result.records = new ArrayList<>();
            result.total = 0;
        }

        return result;
    }

    // Temel list metodunu ekleyelim
    public static ListResult list(SuRecord record) {
        return list(record, null, null, 1, Integer.parseInt(PAGE_SIZE));
    }

    // Sayfalama ile list
    public static ListResult list(SuRecord record, int page) {
        return list(record, null, null, page, Integer.parseInt(PAGE_SIZE));
    }

    // Arama ve sayfalama ile list
    public static ListResult list(SuRecord record, String searchParam, int page) {
        return list(record, searchParam, null, page, Integer.parseInt(PAGE_SIZE));
    }

    // Arama, sıralama ve sayfalama ile list
    public static ListResult list(SuRecord record, String searchParam, String sortParam, int page) {
        return list(record, searchParam, sortParam, page, Integer.parseInt(PAGE_SIZE));
    }


    public static SuField.FieldList getFieldList(SuRecord record) {
        SuField.FieldList fieldList = record.getField().fields;
        for (SuField field : fieldList.getFields()) {
            field.setActualValue(getFieldValue(record, field.getDataName()));
            field.setDisplayValue(field.getActualValue());
        }
        return fieldList;
    }

    public static List<SuField.FieldList> getFieldLists(List<SuRecord> records) {
        List<SuField.FieldList> props = new ArrayList<>();
        for (SuRecord record : records) {
            props.add(getFieldList(record));
        }
        return props;
    }

    private static Object convertValue(String value, SuField field) {
        if (value == null || field == null) {
            return value;
        }
        try {
            switch (field.getFieldType()) {
                case INTEGER:
                    return Integer.parseInt(value);
                case DECIMAL:
                    return Double.parseDouble(value);
                case BOOLEAN:
                    return Boolean.parseBoolean(value);
                case LONG:
                    return Long.parseLong(value);
                case DATE:
                    SimpleDateFormat sdff = new SimpleDateFormat(SystemUtils.DATE_FORMAT);
                    return sdff.parse(value);
                case DATETIME:
                    SimpleDateFormat sdf = new SimpleDateFormat(SystemUtils.DATETIME_FORMAT);
                    return sdf.parse(value);
                default:
                    return value;
            }
        } catch (Exception e) {
            logger.error("Değer dönüşüm hatası: {}", e.getMessage());
            return value;
        }
    }

    public static class ListResult implements Serializable {
        public List<SuRecord> records;
        public int total;
        public int page;
        public SuField.FieldList fieldList;
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
