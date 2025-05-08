package defsu.sc.core;

import defsu.sc.components.FormBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Form yapılarını ve bileşenlerini yönetmek, yetkilendirmek için kullanılan sınıf
 */
public class AuthCore {

    // Kayıtlı formlar listesi
    private static final List<FormBuilder> forms = new ArrayList<>();

    /**
     * Form ekler
     */
    public static void addForm(FormBuilder form) {
        forms.add(form);
    }

    /**
     * Kayıtlı formları döndürür
     */
    public static List<FormBuilder> getForms() {
        return forms;
    }


    /**
     * Tüm formların bileşenlerini hiyerarşik yapıda döndürür
     */
    public static List<FormComponentInfo> getAuthorisationComponents() {
        List<FormComponentInfo> result = new ArrayList<>();

        for (FormBuilder form : forms) {
            Map<String, Object> formDefinition = form.build();

            FormComponentInfo formInfo = new FormComponentInfo();
            formInfo.setId((String) formDefinition.get("id"));
            formInfo.setTitle((String) formDefinition.get("title"));
            formInfo.setType("form");

            // Main bölümünü analiz et
            if (formDefinition.containsKey("main")) {
                FormComponentInfo mainSection = new FormComponentInfo();
                mainSection.setId("main");
                mainSection.setTitle("Ana Ekran");
                mainSection.setType("section");

                analyzeSection(formDefinition, "main", mainSection);
                formInfo.getChildren().add(mainSection);
            }

            // Detail bölümünü analiz et
            if (formDefinition.containsKey("detail")) {
                FormComponentInfo detailSection = new FormComponentInfo();
                detailSection.setId("detail");
                detailSection.setTitle("Detay Ekranı");
                detailSection.setType("section");

                analyzeSection(formDefinition, "detail", detailSection);
                formInfo.getChildren().add(detailSection);
            }

            result.add(formInfo);
        }

        return result;
    }

    /**
     * Bir form bölümünü analiz eder
     */
    @SuppressWarnings("unchecked")
    private static void analyzeSection(Map<String, Object> formDefinition, String sectionKey, FormComponentInfo parentInfo) {
        Map<String, Object> sectionData = (Map<String, Object>) formDefinition.get(sectionKey);

        // Genel bileşenleri analiz et
        if (sectionData.containsKey("components")) {
            FormComponentInfo componentsGroup = new FormComponentInfo();
            componentsGroup.setId("components");
            componentsGroup.setTitle("Bileşenler");
            componentsGroup.setType("group");

            List<Map<String, Object>> components = (List<Map<String, Object>>) sectionData.get("components");
            for (Map<String, Object> component : components) {
                FormComponentInfo componentInfo = new FormComponentInfo();
                componentInfo.setId((String) component.get("id"));
                componentInfo.setTitle((String) component.get("title"));
                componentInfo.setType((String) component.get("type"));

                componentsGroup.getChildren().add(componentInfo);
            }

            if (!componentsGroup.getChildren().isEmpty()) {
                parentInfo.getChildren().add(componentsGroup);
            }
        }

        // Liste bileşenini analiz et
        if (sectionData.containsKey("list")) {
            Map<String, Object> list = (Map<String, Object>) sectionData.get("list");

            FormComponentInfo listInfo = new FormComponentInfo();
            listInfo.setId((String) list.get("id"));
            listInfo.setTitle((String) list.get("title"));
            listInfo.setType("list");

            // Sütunları analiz et
            if (list.containsKey("columns")) {
                FormComponentInfo columnsGroup = new FormComponentInfo();
                columnsGroup.setId("columns");
                columnsGroup.setTitle("Sütunlar");
                columnsGroup.setType("group");

                List<Map<String, Object>> columns = (List<Map<String, Object>>) list.get("columns");
                for (Map<String, Object> column : columns) {
                    FormComponentInfo columnInfo = new FormComponentInfo();
                    columnInfo.setId((String) column.get("id"));
                    columnInfo.setTitle((String) column.get("title"));
                    columnInfo.setType("column");

                    columnsGroup.getChildren().add(columnInfo);
                }

                if (!columnsGroup.getChildren().isEmpty()) {
                    listInfo.getChildren().add(columnsGroup);
                }
            }

            // DockedItems analiz et
            if (list.containsKey("dockedItems")) {
                FormComponentInfo dockedItemsGroup = new FormComponentInfo();
                dockedItemsGroup.setId("dockedItems");
                dockedItemsGroup.setTitle("Docked Items");
                dockedItemsGroup.setType("group");

                Map<String, Object> dockedItems = (Map<String, Object>) list.get("dockedItems");
                if (dockedItems.containsKey("items")) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>) dockedItems.get("items");
                    for (Map<String, Object> item : items) {
                        FormComponentInfo itemInfo = new FormComponentInfo();
                        itemInfo.setId((String) item.get("id"));
                        itemInfo.setTitle((String) item.get("title"));
                        itemInfo.setType((String) item.get("type"));

                        dockedItemsGroup.getChildren().add(itemInfo);
                    }
                }

                if (!dockedItemsGroup.getChildren().isEmpty()) {
                    listInfo.getChildren().add(dockedItemsGroup);
                }
            }

            parentInfo.getChildren().add(listInfo);
        }

        // Field gruplarını analiz et
        if (sectionData.containsKey("fieldGroups")) {
            FormComponentInfo fieldGroupsContainer = new FormComponentInfo();
            fieldGroupsContainer.setId("fieldGroups");
            fieldGroupsContainer.setTitle("Alan Grupları");
            fieldGroupsContainer.setType("group");

            List<Map<String, Object>> fieldGroups = (List<Map<String, Object>>) sectionData.get("fieldGroups");
            for (Map<String, Object> group : fieldGroups) {
                FormComponentInfo groupInfo = new FormComponentInfo();
                groupInfo.setId((String) group.get("id"));
                groupInfo.setTitle((String) group.get("title"));
                groupInfo.setType("fieldGroup");

                // Grup öğelerini analiz et
                if (group.containsKey("items")) {
                    List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
                    for (Map<String, Object> item : items) {
                        // Eğer liste ise farklı işle
                        if ("list".equals(item.get("type"))) {
                            FormComponentInfo nestedListInfo = new FormComponentInfo();
                            nestedListInfo.setId((String) item.get("id"));
                            nestedListInfo.setTitle((String) item.get("title"));
                            nestedListInfo.setType("nestedList");

                            // İç liste sütunlarını analiz et
                            if (item.containsKey("columns")) {
                                FormComponentInfo columnsGroup = new FormComponentInfo();
                                columnsGroup.setId("columns");
                                columnsGroup.setTitle("Sütunlar");
                                columnsGroup.setType("group");

                                List<Map<String, Object>> columns = (List<Map<String, Object>>) item.get("columns");
                                for (Map<String, Object> column : columns) {
                                    FormComponentInfo columnInfo = new FormComponentInfo();
                                    columnInfo.setId((String) column.get("id"));
                                    columnInfo.setTitle((String) column.get("title"));
                                    columnInfo.setType("column");

                                    columnsGroup.getChildren().add(columnInfo);
                                }

                                if (!columnsGroup.getChildren().isEmpty()) {
                                    nestedListInfo.getChildren().add(columnsGroup);
                                }
                            }

                            // İç liste dockedItems analiz et
                            if (item.containsKey("dockedItems")) {
                                FormComponentInfo dockedItemsGroup = new FormComponentInfo();
                                dockedItemsGroup.setId("dockedItems");
                                dockedItemsGroup.setTitle("Docked Items");
                                dockedItemsGroup.setType("group");

                                Map<String, Object> dockedItems = (Map<String, Object>) item.get("dockedItems");
                                if (dockedItems.containsKey("items")) {
                                    List<Map<String, Object>> dockedButtonItems = (List<Map<String, Object>>) dockedItems.get("items");
                                    for (Map<String, Object> buttonItem : dockedButtonItems) {
                                        FormComponentInfo buttonInfo = new FormComponentInfo();
                                        buttonInfo.setId((String) buttonItem.get("id"));
                                        buttonInfo.setTitle((String) buttonItem.get("title"));
                                        buttonInfo.setType((String) buttonItem.get("type"));

                                        dockedItemsGroup.getChildren().add(buttonInfo);
                                    }
                                }

                                if (!dockedItemsGroup.getChildren().isEmpty()) {
                                    nestedListInfo.getChildren().add(dockedItemsGroup);
                                }
                            }

                            groupInfo.getChildren().add(nestedListInfo);
                        } else {
                            // Normal bileşen
                            FormComponentInfo itemInfo = new FormComponentInfo();
                            itemInfo.setId((String) item.get("id"));
                            itemInfo.setTitle((String) item.get("title"));
                            itemInfo.setType((String) item.get("type"));

                            groupInfo.getChildren().add(itemInfo);
                        }
                    }
                }

                fieldGroupsContainer.getChildren().add(groupInfo);
            }

            if (!fieldGroupsContainer.getChildren().isEmpty()) {
                parentInfo.getChildren().add(fieldGroupsContainer);
            }
        }

        // DockedItems analiz et
        if (sectionData.containsKey("dockedItems")) {
            FormComponentInfo dockedItemsGroup = new FormComponentInfo();
            dockedItemsGroup.setId("dockedItems");
            dockedItemsGroup.setTitle("Docked Items");
            dockedItemsGroup.setType("group");

            Map<String, Object> dockedItems = (Map<String, Object>) sectionData.get("dockedItems");
            if (dockedItems.containsKey("items")) {
                List<Map<String, Object>> items = (List<Map<String, Object>>) dockedItems.get("items");
                for (Map<String, Object> item : items) {
                    FormComponentInfo itemInfo = new FormComponentInfo();
                    itemInfo.setId((String) item.get("id"));
                    itemInfo.setTitle((String) item.get("title"));
                    itemInfo.setType((String) item.get("type"));

                    dockedItemsGroup.getChildren().add(itemInfo);
                }
            }

            if (!dockedItemsGroup.getChildren().isEmpty()) {
                parentInfo.getChildren().add(dockedItemsGroup);
            }
        }
    }

    /**
     * Form bileşeni bilgisi sınıfı - sadece ID, title ve type bilgisi içerir
     */
    public static class FormComponentInfo {
        private String id;
        private String title;
        private String type;
        private List<FormComponentInfo> children = new ArrayList<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<FormComponentInfo> getChildren() {
            return children;
        }

        public void setChildren(List<FormComponentInfo> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "FormComponentInfo{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", children=" + children.size() +
                    '}';
        }
    }
}