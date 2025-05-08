package defsu.sc.components;

import defsu.sc.core.SuField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tamamen manuel form yapısı oluşturmak için geliştirilmiş FormBuilder sınıfı.
 * SuField'lardan doğrudan bileşen oluşturmayı destekler.
 * İnşallah yapıyı unutmam :D
 * TODO : id'lere göre yetki sistemi eklenecek.
 */
public class FormBuilder {
    // Form temel yapısı
    private final Map<String, Object> formStructure = new HashMap<>();

    public FormBuilder() {
        // Ana bölümler oluştur
        formStructure.put("main", new HashMap<>());
        formStructure.put("detail", new HashMap<>());
    }

    // ===================== GENEL FORM AYARLARI =====================

    public FormBuilder setId(String id) {
        formStructure.put("id", id);
        return this;
    }

    public FormBuilder setTitle(String title) {
        formStructure.put("title", title);
        return this;
    }

    public FormBuilder setWidth(int width) {
        formStructure.put("width", width);
        return this;
    }

    public FormBuilder setHeight(int height) {
        formStructure.put("height", height);
        return this;
    }

    public FormBuilder setMaximized(boolean maximized) {
        formStructure.put("maximized", maximized);
        return this;
    }

    public FormBuilder setIcon(String icon) {
        formStructure.put("icon", icon);
        return this;
    }

    // ===================== FORM COMPONENT BUILDER =====================

    /**
     * SuField'dan bir form bileşeni oluşturur
     */
    public static Map<String, Object> createComponentFromField(SuField field) {
        Map<String, Object> component = new HashMap<>();
        component.put("dataName", field.getDataName());
        component.put("title", field.getTitle());
        component.put("id", field.getDataName() + "Field");
        component.put("required", field.getRequired());
        component.put("fieldData", field); // Direkt field verilerini gönder

        // Field tipine göre component tipini belirle
        String type = determineComponentType(field);
        component.put("type", type);

        // Özel component özellikleri
        if (field.getMaskRegex() != null && !field.getMaskRegex().isEmpty()) {
            component.put("regex", field.getMaskRegex());
        }

        if (field.getMaxLength() > 0) {
            component.put("maxLength", field.getMaxLength());
        }

        if (field.getWidth() > 0) {
            component.put("width", field.getWidth());
        }

        if (field.getHeight() > 0) {
            component.put("height", field.getHeight());
        }

        // Field tipi decimal ise
        if (field.getFieldType() == SuField.FieldType.DECIMAL) {
            component.put("decimal", true);
        }

        return component;
    }

    /**
     * SuField'dan bir sütun oluşturur
     */
    public static Map<String, Object> createColumnFromField(SuField field) {
        Map<String, Object> column = new HashMap<>();
        column.put("dataName", field.getDataName());
        column.put("title", field.getTitle());
        column.put("id", field.getDataName() + "Column");
        column.put("searchable", field.getSearchable());
        column.put("sortable", field.getSortable());
        column.put("fieldData", field); // Direkt field verilerini gönder

        // Field tipine göre column tipini belirle
        String columnType = determineColumnType(field);
        column.put("columnType", columnType);

        return column;
    }

    /**
     * Field tipine göre component tipini belirler
     */
    private static String determineComponentType(SuField field) {
        switch (field.getDisplayType()) {
            case CHECKBOX:
                return "checkbox";
            case SELECT:
            case SELECT2:
                return "combobox";
            case TEXTAREA:
                return "textarea";
            case HIDDEN:
                return "hidden";
            default:
                switch (field.getFieldType()) {
                    case DATE:
                        return "datefield";
                    case DATETIME:
                        return "datetimefield";
                    case BOOLEAN:
                        return "checkbox";
                    case INTEGER:
                    case LONG:
                        return "numberfield";
                    case DECIMAL:
                        return "numberfield";
                    default:
                        return "textfield";
                }
        }
    }

    /**
     * Field tipine göre column tipini belirler
     */
    private static String determineColumnType(SuField field) {
        switch (field.getFieldType()) {
            case DATE:
            case DATETIME:
                return "date";
            case BOOLEAN:
                return "boolean";
            case INTEGER:
            case DECIMAL:
            case LONG:
                return "number";
            default:
                return "string";
        }
    }

    // ===================== ANA YAPI =====================

    /**
     * Ana form yapısını oluşturur
     */
    public MainFormSection main() {
        return new MainFormSection();
    }

    /**
     * Detay form yapısını oluşturur
     */
    public DetailFormSection detail() {
        return new DetailFormSection();
    }

    /**
     * Form yapısını JSON olarak döndürür
     */
    public Map<String, Object> build() {
        return formStructure;
    }

    // ===================== MAIN FORM SECTION =====================

    /**
     * Ana form bölümü için builder
     */
    public class MainFormSection {
        private final Map<String, Object> mainSection;

        private MainFormSection() {
            this.mainSection = (Map<String, Object>) formStructure.get("main");
        }

        /**
         * Ana form için özellik ayarlar
         */
        public MainFormSection setProperty(String key, Object value) {
            mainSection.put(key, value);
            return this;
        }

        /**
         * Ana form için bileşen ekler
         */
        public ComponentBuilder addComponent(String type) {
            if (!mainSection.containsKey("components")) {
                mainSection.put("components", new ArrayList<>());
            }

            Map<String, Object> component = new HashMap<>();
            component.put("type", type);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> components = (List<Map<String, Object>>) mainSection.get("components");
            components.add(component);

            return new ComponentBuilder(this, component);
        }

        /**
         * Ana form için field ekler
         */
        public ComponentBuilder addField(SuField field) {
            if (!mainSection.containsKey("components")) {
                mainSection.put("components", new ArrayList<>());
            }

            Map<String, Object> component = createComponentFromField(field);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> components = (List<Map<String, Object>>) mainSection.get("components");
            components.add(component);

            return new ComponentBuilder(this, component);
        }

        /**
         * Ana form için liste oluşturur
         */
        public ListBuilder createList() {
            Map<String, Object> list = new HashMap<>();
            mainSection.put("list", list);
            return new ListBuilder(this, list);
        }

        /**
         * Ana form için DockedItems oluşturur
         */
        public DockedItemsBuilder createDockedItems() {
            Map<String, Object> dockedItems = new HashMap<>();
            dockedItems.put("items", new ArrayList<>());
            mainSection.put("dockedItems", dockedItems);
            return new DockedItemsBuilder(this, dockedItems);
        }
    }

    // ===================== DETAIL FORM SECTION =====================

    /**
     * Detay form bölümü için builder
     */
    public class DetailFormSection {
        private final Map<String, Object> detailSection;

        private DetailFormSection() {
            this.detailSection = (Map<String, Object>) formStructure.get("detail");
        }

        /**
         * Detay form için özellik ayarlar
         */
        public DetailFormSection setProperty(String key, Object value) {
            detailSection.put(key, value);
            return this;
        }

        /**
         * Detay form için grup paneli oluşturur
         */
        public FieldGroupBuilder createFieldGroup(String id, String title) {
            if (!detailSection.containsKey("fieldGroups")) {
                detailSection.put("fieldGroups", new ArrayList<>());
            }

            Map<String, Object> group = new HashMap<>();
            group.put("id", id);
            group.put("title", title);
            group.put("items", new ArrayList<>());

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> groups = (List<Map<String, Object>>) detailSection.get("fieldGroups");
            groups.add(group);

            return new FieldGroupBuilder(this, group);
        }

        /**
         * Detay form için liste oluşturur
         */
        public ListBuilder createList() {
            Map<String, Object> list = new HashMap<>();
            detailSection.put("list", list);
            return new ListBuilder(this, list);
        }

        /**
         * Detay form için DockedItems oluşturur
         */
        public DockedItemsBuilder createDockedItems() {
            Map<String, Object> dockedItems = new HashMap<>();
            dockedItems.put("items", new ArrayList<>());
            detailSection.put("dockedItems", dockedItems);
            return new DockedItemsBuilder(this, dockedItems);
        }
    }

    // ===================== FIELD GROUP BUILDER =====================

    /**
     * Field Grup Builder
     */
    public class FieldGroupBuilder {
        private final DetailFormSection parent;
        private final Map<String, Object> group;

        private FieldGroupBuilder(DetailFormSection parent, Map<String, Object> group) {
            this.parent = parent;
            this.group = group;
        }

        /**
         * Gruba özellik ekler
         */
        public FieldGroupBuilder setProperty(String key, Object value) {
            group.put(key, value);
            return this;
        }

        /**
         * Gruba field ekler (kendisine geri döner)
         */
        public FieldGroupBuilder addField(SuField field) {
            Map<String, Object> component = createComponentFromField(field);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
            items.add(component);

            return this;
        }

        /**
         * Gruba field ekler ve listener eklemek için ComponentBuilder döner
         */
        public ComponentBuilder addFieldWithListeners(SuField field) {
            Map<String, Object> component = createComponentFromField(field);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
            items.add(component);

            return new ComponentBuilder(this, component);
        }

        /**
         * Gruba bileşen ekler
         */
        public FieldGroupBuilder addComponent(String type) {
            Map<String, Object> component = new HashMap<>();
            component.put("type", type);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
            items.add(component);

            return this;
        }

        /**
         * Gruba bileşen ekler ve listener eklemek için ComponentBuilder döner
         */
        public ComponentBuilder addComponentWithListeners(String type) {
            Map<String, Object> component = new HashMap<>();
            component.put("type", type);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
            items.add(component);

            return new ComponentBuilder(this, component);
        }

        /**
         * Gruba yerleşik liste ekler
         */
        public ListBuilder createNestedList() {
            Map<String, Object> list = new HashMap<>();
            list.put("type", "list");

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) group.get("items");
            items.add(list);

            return new ListBuilder(this, list);
        }

        /**
         * Üst detay formuna dön
         */
        public DetailFormSection endGroup() {
            return parent;
        }
    }

    // ===================== COMPONENT BUILDER =====================

    /**
     * Bileşen oluşturucu
     */
    public class ComponentBuilder {
        private final Object parent;
        private final Map<String, Object> component;

        private ComponentBuilder(Object parent, Map<String, Object> component) {
            this.parent = parent;
            this.component = component;
        }

        /**
         * Bileşene özellik ekler
         */
        public ComponentBuilder setProperty(String key, Object value) {
            component.put(key, value);
            return this;
        }

        /**
         * Bileşene ID ekler
         */
        public ComponentBuilder setId(String id) {
            component.put("id", id);
            return this;
        }

        /**
         * Bileşene başlık ekler
         */
        public ComponentBuilder setTitle(String title) {
            component.put("title", title);
            return this;
        }

        /**
         * Bileşene genişlik ekler
         */
        public ComponentBuilder setWidth(int width) {
            component.put("width", width);
            return this;
        }

        /**
         * Bileşene yükseklik ekler
         */
        public ComponentBuilder setHeight(int height) {
            component.put("height", height);
            return this;
        }

        /**
         * Bileşene ikon ekler
         */
        public ComponentBuilder setIcon(String icon) {
            component.put("icon", icon);
            return this;
        }

        /**
         * Bileşene veri alanı ekler
         */
        public ComponentBuilder setDataName(String dataName) {
            component.put("dataName", dataName);
            return this;
        }

        /**
         * Bileşene zorunluluk ekler
         */
        public ComponentBuilder setRequired(boolean required) {
            component.put("required", required);
            return this;
        }

        /**
         * Bileşene listener ekler
         */
        public ComponentBuilder addListener(String service, String action, String params) {
            if (!component.containsKey("listeners")) {
                component.put("listeners", new ArrayList<>());
            }

            Map<String, Object> listener = new HashMap<>();

            if (service != null) {
                listener.put("service", service);
            }

            if (action != null) {
                listener.put("action", action);
            }

            if (params != null) {
                listener.put("params", params);
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listeners = (List<Map<String, Object>>) component.get("listeners");
            listeners.add(listener);

            return this;
        }

        /**
         * Üst nesneye dön
         */
        @SuppressWarnings("unchecked")
        public FieldGroupBuilder end() {
            if (parent instanceof FieldGroupBuilder) {
                return (FieldGroupBuilder) parent;
            }
            throw new UnsupportedOperationException("Parent nesne FieldGroupBuilder tipinde değil");
        }
    }

    // ===================== LIST BUILDER =====================

    /**
     * Liste oluşturucu
     */
    public class ListBuilder {
        private final Object parent;
        private final Map<String, Object> list;

        private ListBuilder(Object parent, Map<String, Object> list) {
            this.parent = parent;
            this.list = list;
            list.put("columns", new ArrayList<>());
        }

        /**
         * Listeye özellik ekler
         */
        public ListBuilder setProperty(String key, Object value) {
            list.put(key, value);
            return this;
        }

        /**
         * Listeye ID ekler
         */
        public ListBuilder setId(String id) {
            list.put("id", id);
            return this;
        }

        /**
         * Listeye başlık ekler
         */
        public ListBuilder setTitle(String title) {
            list.put("title", title);
            return this;
        }

        /**
         * Listeye servis ekler
         */
        public ListBuilder setService(String service) {
            list.put("service", service);
            return this;
        }

        /**
         * Listeye aksiyon ekler
         */
        public ListBuilder setAction(String action) {
            list.put("action", action);
            return this;
        }

        /**
         * Listeye parametre ekler
         */
        public ListBuilder setParams(Map<String, Object> params) {
            list.put("params", params);
            return this;
        }

        /**
         * Listeye sayfa boyutu ekler
         */
        public ListBuilder setPageSize(int pageSize) {
            list.put("pageSize", pageSize);
            return this;
        }

        /**
         * Listeye listener ekler
         */
        public ListBuilder addListener(String service, String action, String params) {
            if (!list.containsKey("listeners")) {
                list.put("listeners", new ArrayList<>());
            }

            Map<String, Object> listener = new HashMap<>();

            if (service != null) {
                listener.put("service", service);
            }

            if (action != null) {
                listener.put("action", action);
            }

            if (params != null) {
                listener.put("params", params);
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listeners = (List<Map<String, Object>>) list.get("listeners");
            listeners.add(listener);

            return this;
        }

        /**
         * Listeye çoklu seçim özelliği ekler
         */
        public ListBuilder setMultiSelect(boolean multiSelect) {
            list.put("multiSelect", multiSelect);
            return this;
        }

        /**
         * Listeyi düzenleme modunda açar
         */
        public ListBuilder setEditable(boolean editable) {
            list.put("editable", editable);
            return this;
        }

        /**
         * Listeye field'dan sütun ekler
         */
        public ListBuilder addColumn(SuField field) {
            Map<String, Object> column = createColumnFromField(field);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> columns = (List<Map<String, Object>>) list.get("columns");
            columns.add(column);

            return this;
        }

        /**
         * Listeye manuel sütun ekler
         */
        public ColumnBuilder addColumn(String dataName) {
            Map<String, Object> column = new HashMap<>();
            column.put("dataName", dataName);
            column.put("id", dataName + "Column");

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> columns = (List<Map<String, Object>>) list.get("columns");
            columns.add(column);

            return new ColumnBuilder(this, column);
        }

        /**
         * Liste için DockedItems oluşturur
         */
        public DockedItemsBuilder createDockedItems() {
            Map<String, Object> dockedItems = new HashMap<>();
            dockedItems.put("items", new ArrayList<>());
            list.put("dockedItems", dockedItems);
            return new DockedItemsBuilder(this, dockedItems);
        }

        /**
         * Üst nesneye dön
         */
        @SuppressWarnings("unchecked")
        public <T> T end() {
            return (T) parent;
        }
    }

    // ===================== COLUMN BUILDER =====================

    /**
     * Sütun oluşturucu
     */
    public class ColumnBuilder {
        private final ListBuilder parent;
        private final Map<String, Object> column;

        private ColumnBuilder(ListBuilder parent, Map<String, Object> column) {
            this.parent = parent;
            this.column = column;
        }

        /**
         * Sütuna özellik ekler
         */
        public ColumnBuilder setProperty(String key, Object value) {
            column.put(key, value);
            return this;
        }

        /**
         * Sütuna başlık ekler
         */
        public ColumnBuilder setTitle(String title) {
            column.put("title", title);
            return this;
        }

        /**
         * Sütuna tip ekler
         */
        public ColumnBuilder setColumnType(String columnType) {
            column.put("columnType", columnType);
            return this;
        }

        /**
         * Sütuna aranabilirlik ekler
         */
        public ColumnBuilder setSearchable(boolean searchable) {
            column.put("searchable", searchable);
            return this;
        }

        /**
         * Sütuna sıralanabilirlik ekler
         */
        public ColumnBuilder setSortable(boolean sortable) {
            column.put("sortable", sortable);
            return this;
        }

        /**
         * Sütuna düzenlenebilirlik ekler
         */
        public ColumnBuilder setEditable(boolean editable) {
            column.put("editable", editable);
            return this;
        }

        /**
         * Sütuna genişlik ekler
         */
        public ColumnBuilder setWidth(int width) {
            column.put("width", width);
            return this;
        }

        /**
         * Listeye geri dön
         */
        public ListBuilder end() {
            return parent;
        }
    }

    // ===================== DOCKED ITEMS BUILDER =====================

    /**
     * DockedItems oluşturucu
     */
    public class DockedItemsBuilder {
        private final Object parent;
        private final Map<String, Object> dockedItems;

        private DockedItemsBuilder(Object parent, Map<String, Object> dockedItems) {
            this.parent = parent;
            this.dockedItems = dockedItems;
        }

        /**
         * DockedItems'a özellik ekler
         */
        public DockedItemsBuilder setProperty(String key, Object value) {
            dockedItems.put(key, value);
            return this;
        }

        /**
         * DockedItems'a buton ekler
         */
        public ButtonBuilder addButton(String id) {
            Map<String, Object> button = new HashMap<>();
            button.put("type", "button");
            button.put("id", id);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) dockedItems.get("items");
            items.add(button);

            return new ButtonBuilder(this, button);
        }

        /**
         * DockedItems'a hızlı yeni oluştur butonu ekler
         */
        public ButtonBuilder addNewButton() {
            return addButton("newButton").setTitle("Yeni");
        }

        /**
         * DockedItems'a hızlı düzenle butonu ekler
         */
        public ButtonBuilder addEditButton() {
            return addButton("editButton").setTitle("Düzenle");
        }

        /**
         * DockedItems'a hızlı sil butonu ekler
         */
        public ButtonBuilder addDeleteButton() {
            return addButton("deleteButton").setTitle("Sil");
        }

        /**
         * DockedItems'a hızlı kaydet butonu ekler
         */
        public ButtonBuilder addSaveButton() {
            return addButton("saveButton").setTitle("Kaydet");
        }

        /**
         * Üst nesneye dön
         */
        @SuppressWarnings("unchecked")
        public <T> T end() {
            return (T) parent;
        }
    }

    // ===================== BUTTON BUILDER =====================

    /**
     * Buton oluşturucu
     */
    public class ButtonBuilder {
        private final DockedItemsBuilder parent;
        private final Map<String, Object> button;

        private ButtonBuilder(DockedItemsBuilder parent, Map<String, Object> button) {
            this.parent = parent;
            this.button = button;
        }

        /**
         * Butona özellik ekler
         */
        public ButtonBuilder setProperty(String key, Object value) {
            button.put(key, value);
            return this;
        }

        /**
         * Butona başlık ekler
         */
        public ButtonBuilder setTitle(String title) {
            button.put("title", title);
            return this;
        }

        /**
         * Butona ikon ekler
         */
        public ButtonBuilder setIcon(String icon) {
            button.put("icon", icon);
            return this;
        }

        /**
         * Butona onay mesajı ekler
         */
        public ButtonBuilder setConfirmMessage(String confirmMessage) {
            button.put("confirmMessage", confirmMessage);
            return this;
        }

        /**
         * Butona servis ekler
         */
        public ButtonBuilder setService(String service) {
            button.put("service", service);
            return this;
        }

        /**
         * Butona aksiyon ekler
         */
        public ButtonBuilder setAction(String action) {
            button.put("action", action);
            return this;
        }

        /**
         * Butona parametre ekler
         */
        public ButtonBuilder setParams(String params) {
            button.put("params", params);
            return this;
        }

        /**
         * Butona devre dışı bırakma ekler
         */
        public ButtonBuilder setDisabled(boolean disabled) {
            button.put("disabled", disabled);
            return this;
        }

        /**
         * Butona genişlik ekler
         */
        public ButtonBuilder setWidth(int width) {
            button.put("width", width);
            return this;
        }

        /**
         * Butona yükseklik ekler
         */
        public ButtonBuilder setHeight(int height) {
            button.put("height", height);
            return this;
        }

        /**
         * Butona listener ekler
         */
        public ButtonBuilder addListener(String service, String action, String params) {
            if (!button.containsKey("listeners")) {
                button.put("listeners", new ArrayList<>());
            }

            Map<String, Object> listener = new HashMap<>();

            if (service != null) {
                listener.put("service", service);
            }

            if (action != null) {
                listener.put("action", action);
            }

            if (params != null) {
                listener.put("params", params);
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listeners = (List<Map<String, Object>>) button.get("listeners");
            listeners.add(listener);

            return this;
        }

        /**
         * DockedItems'a geri dön
         */
        public DockedItemsBuilder end() {
            return parent;
        }
    }
}