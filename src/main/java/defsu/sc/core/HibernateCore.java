package defsu.sc.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class HibernateCore {

    private static HibernateCore instance;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @Autowired
    public HibernateCore(PlatformTransactionManager transactionManager) {
        instance = this;
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public static HibernateCore getInstance() {
        return instance;
    }

    public static SuRecord save(SuRecord record){
        String idField = record.getField().idField;
        Object idVal = ObjectCore.getFieldValue(record, idField);
        record.process();

        if(idVal == null){
            return insert(record);
        }else if(idVal.equals("ErrorInvoke")){
            return record;
        }else{
            return update(record);
        }
    }

    public static SuRecord insert(SuRecord record) {
        HibernateCore instance = getInstance();
        Object pojo = ObjectCore.getRecordToPojo(record);
        return instance.transactionTemplate.execute(status -> {
            instance.getEntityManager().persist(pojo);
            ObjectCore.copyPojoToRecord(pojo, record);
            return record;
        });
    }

    public static SuRecord update(SuRecord record) {
        HibernateCore instance = getInstance();
        Object pojo = ObjectCore.getRecordToPojo(record);
        return instance.transactionTemplate.execute(status -> {
            instance.getEntityManager().merge(pojo);
            ObjectCore.copyPojoToRecord(pojo, record);
            return record;
        });
    }

    public static SuRecord delete(SuRecord record) {
        HibernateCore instance = getInstance();
        Object pojo = ObjectCore.getRecordToPojo(record);
        return instance.transactionTemplate.execute(status -> {
            instance.getEntityManager().remove(pojo);
            ObjectCore.copyPojoToRecord(pojo, record);
            return record;
        });
    }


    // Statik getirme metodu
    public static <T extends SuRecord> T get(Long id, Class<T> recordClass) {
        HibernateCore instance = getInstance();
        return instance.transactionTemplate.execute(status -> {
            try {
                Class<?> entityClass = recordClass.getSuperclass();
                Object entity = instance.getEntityManager().find(entityClass, id);

                if (entity == null) {
                    return null;
                }

                T record = recordClass.getDeclaredConstructor().newInstance();
                ObjectCore.copyPojoToRecord(entity, record);

                return record;
            } catch (Exception e) {
                throw new RuntimeException("Entity getirme hatası: " + e.getMessage(), e);
            }
        });
    }
}