package defsu.sc.forms.general;

import defsu.sc.core.SuField;
import defsu.sc.components.FormBuilder;
import defsu.sc.records.general.Country;

import java.util.HashMap;
import java.util.Map;

public class FormCountry extends FormBuilder {
    public FormCountry() {
        // Country record örneği al
        Country country = new Country();
        SuField.FieldList fields = country.getField().fields;

        // Form builder oluştur

        this.setId("countryForm")
                .setTitle("Ülke Formu")
                .setWidth(0)
                .setHeight(0)
                .setMaximized(true)
                .setIcon("globe-icon");

        // Ana form yapılandırması
        FormBuilder.MainFormSection mainForm = this.main();

        mainForm.addComponent("string").setTitle("Özel komponent").setHeight(50).addListener("servis", "action", "params");
        mainForm.addField(fields.get("commonName"));
        // Ana liste oluştur
        FormBuilder.ListBuilder mainList = mainForm.createList();
        mainList.setId("countryList")
                .setTitle("Ülke Listesi")
                .setService("/country")
                .setAction("list")
                .setPageSize(20)
                .setMultiSelect(true);

        // Field'lardan sütunlar ekle
        mainList.addColumn(fields.get("commonName"))
                .addColumn(fields.get("nativeName"))
                .addColumn(fields.get("a2Code"))
                .addColumn(fields.get("region"))
                .addColumn(fields.get("capital"));

        // Liste için DockedItems oluştur
        FormBuilder.DockedItemsBuilder listDocked = mainList.createDockedItems();
        listDocked.addNewButton()
                .setTitle("Yeni Ülke")
                .setService("/country")
                .setAction("new");

        listDocked.addEditButton()
                .setTitle("Ülkeyi Düzenle")
                .setService("/country")
                .setAction("edit")
                .setParams("selectedId");

        listDocked.addDeleteButton()
                .setTitle("Ülkeyi Sil")
                .setConfirmMessage("Bu ülkeyi silmek istediğinize emin misiniz?")
                .setService("/country")
                .setAction("delete")
                .setParams("selectedId");

        // Export butonu ekle
        listDocked.addButton("exportButton")
                .setTitle("Excel'e Aktar")
                .setIcon("excel-icon")
                .setService("/country")
                .setAction("export")
                .setParams("format=excel");

        // Detay form yapılandırması
        FormBuilder.DetailFormSection detailForm = this.detail();

        // Temel bilgiler grubu
        FormBuilder.FieldGroupBuilder basicGroup = detailForm.createFieldGroup("basicInfo", "Temel Bilgiler");

        // Field'ları gruba ekle
        basicGroup.addField(fields.get("commonName"))
                .addField(fields.get("nativeName"))
                .addField(fields.get("a2Code"))
                .addFieldWithListeners(fields.get("a3Code")).addListener("change", "setA3Code", "selectedValue").end()
                .addField(fields.get("a3Code"));

        // Coğrafi bilgiler grubu
        FormBuilder.FieldGroupBuilder geoGroup = detailForm.createFieldGroup("geoInfo", "Coğrafi Bilgiler");

        // Field'ları gruba ekle
        geoGroup.addField(fields.get("region"))
                .addField(fields.get("subregion"))
                .addField(fields.get("capital"))
                .addField(fields.get("latLong"));

        // Diğer bilgiler grubu
        FormBuilder.FieldGroupBuilder otherGroup = detailForm.createFieldGroup("otherInfo", "Diğer Bilgiler");

        // Field'ları gruba ekle
        otherGroup.addField(fields.get("currency"))
                .addField(fields.get("language"))
                .addField(fields.get("timezone"))
                .addField(fields.get("phoneCode"));

        // Şehirler için özel bir liste içeren grup
        FormBuilder.FieldGroupBuilder citiesGroup = detailForm.createFieldGroup("citiesGroup", "Şehirler");

        // Grup içinde şehirler listesi oluştur
        FormBuilder.ListBuilder citiesList = citiesGroup.createNestedList();
        citiesList.setId("cityList")
                .setTitle("Şehirler")
                .setService("/city")
                .setAction("listByCountry")
                .setParams(new HashMap<String, Object>() {{ put("countryId", "recordId"); }})
                .setPageSize(10)
                .setMultiSelect(true).setEditable(true).addListener("/country", "editRow", "FormCountry.editRow");


        // Şehir listesi için sütunlar ekle
        citiesList.addColumn("name")
                .setTitle("Şehir Adı")
                .setColumnType("string")
                .setSearchable(true)
                .setSortable(true);

        citiesList.addColumn("population")
                .setTitle("Nüfus")
                .setColumnType("number")
                .setSearchable(true)
                .setSortable(true);

        citiesList.addColumn("isCapital")
                .setTitle("Başkent mi?")
                .setColumnType("boolean")
                .setSearchable(true)
                .setSortable(true);

        // Şehir listesi docked items
        FormBuilder.DockedItemsBuilder citiesListDocked = citiesList.createDockedItems();
        citiesListDocked.addNewButton()
                .setTitle("Yeni Şehir")
                .setService("/city")
                .setAction("new")
                .setParams("countryId=recordId");

        citiesListDocked.addEditButton()
                .setTitle("Şehri Düzenle")
                .setService("/city")
                .setAction("edit")
                .setParams("selectedId");

        citiesListDocked.addDeleteButton()
                .setTitle("Şehri Sil")
                .setConfirmMessage("Bu şehri silmek istediğinize emin misiniz?")
                .setService("/city")
                .setAction("delete")
                .setParams("selectedId");

        // Detay form için DockedItems
        FormBuilder.DockedItemsBuilder detailDocked = detailForm.createDockedItems();
        detailDocked.addSaveButton()
                .setTitle("Ülkeyi Kaydet")
                .setService("/country")
                .setAction("save");

        detailDocked.addButton("closeButton")
                .setTitle("Kapat")
                .setAction("closeForm");

        detailDocked.addButton("printButton")
                .setTitle("Yazdır")
                .setIcon("print-icon")
                .setService("/country")
                .setAction("print")
                .setParams("recordId");

    }

}