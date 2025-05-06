package defsu.sc.records.general;

import defsu.sc.components.SelectBox;
import defsu.sc.core.*;
import defsu.sc.maps.general.MapCountry;

import java.util.Date;



public class Country extends MapCountry implements SuRecord {
    public String ID = "id";
    public static SuField.FieldList _fields;

    public Country(){
        initialize();
    }

    public Country(Long id){
        initialize();
        ObjectCore.load(this,id);
    }


    @Override
    public void initialize() {
        if(_fields == null){
            _fields = new SuField.FieldList();

            SuField f = new SuField();
            f.setDataName(ID);
            f.setTitle("ID");
            f.setDefaultValue(null);
            _fields.add(f);

            f = new SuField();
            f.setDataName("commonName");
            f.setDefaultValue("");
            f.setMaxLength(64);
            f.setTitle("Ülke Adı(Genel)");
            f.setRequired(true);
            _fields.add(f);

            f = new SuField();
            f.setDataName("nativeName");
            f.setDefaultValue("");
            f.setMaxLength(64);
            f.setTitle("Ülke Adı(Lokal)");
            f.setRequired(true);
            _fields.add(f);

            f = new SuField();
            f.setDataName("a2Code");
            f.setDefaultValue("");
            f.setMaxLength(2);
            f.setTitle("Ülke Kodu(2)");
            _fields.add(f);

            f = new SuField();
            f.setDataName("a3Code");
            f.setDefaultValue("");
            f.setMaxLength(3);
            f.setTitle("Ülke Kodu(3)");
            _fields.add(f);

            f = new SuField();
            f.setDataName("phoneCode");
            f.setDefaultValue("");
            f.setMaxLength(3);
            f.setTitle("Telefon Kodu");
            _fields.add(f);

            f = new SuField();
            f.setDataName("flagIcon");
            f.setDefaultValue("");
            f.setTitle("Bayrak Resmi");
            _fields.add(f);

            f = new SuField();
            f.setDataName("currency");
            f.setDefaultValue("");
            f.setMaxLength(8);
            f.setTitle("Para Birimi");
            _fields.add(f);

            f = new SuField();
            f.setDataName("language");
            f.setDefaultValue("");
            f.setMaxLength(8);
            f.setTitle("Resmi Dili");
            _fields.add(f);

            f = new SuField();
            f.setDataName("timezone");
            f.setDefaultValue("");
            f.setMaxLength(16);
            f.setTitle("Zaman Dilimi");
            _fields.add(f);

            f = new SuField();
            f.setDataName("capital");
            f.setDefaultValue("");
            f.setMaxLength(64);
            f.setTitle("Başkent");
            _fields.add(f);

            f = new SuField();
            f.setDataName("region");
            f.setDefaultValue("");
            f.setMaxLength(64);
            f.setTitle("Bölge");
            _fields.add(f);

            f = new SuField();
            f.setDataName("subregion");
            f.setDefaultValue("");
            f.setMaxLength(64);
            f.setTitle("Alt Bölge");
            _fields.add(f);

            f = new SuField();
            f.setDefaultValue("");
            f.setDataName("latLong");
            f.setTitle("Enlem Boylam");
            _fields.add(f);

            f = new SuField();
            f.setDataName("createdAt");
            f.setDefaultValue(new Date());
            f.setFieldType(SuField.FieldType.DATE);
            f.setTitle("Oluşturulma Tarihi");
            _fields.add(f);

            f = new SuField();
            f.setDataName("updatedAt");
            f.setDefaultValue(new Date());
            f.setFieldType(SuField.FieldType.DATE);
            f.setTitle("Değiştirilme Tarihi");
            _fields.add(f);

            f = new SuField();
            f.setDataName("visible");
            f.setDefaultValue(true);
            f.setDisplayType(SuField.DisplayType.CHECKBOX);
            f.setTitle("Silinme Durumu");
            _fields.add(f);

            f = new SuField();
            f.setDataName("status");
            f.setDefaultValue(1);
            f.setDisplayType(SuField.DisplayType.SELECT);
            f.setComboAdapter(new SelectBox("1::Aktif&&0::Pasif"));
            f.setTitle("Durum");
            _fields.add(f);

            f = new SuField();
            f.setDataName("createdBy");
            f.setDefaultValue(0L);
            f.setTitle("Oluşturan");
            _fields.add(f);

            f = new SuField();
            f.setDataName("updatedBy");
            f.setDefaultValue(0L);
            f.setTitle("Düzenleyen");
            _fields.add(f);

        }
        ObjectCore.setFieldsToDefault(this);
    }

    @Override
    public void process() {

    }

    @Override
    public SuResponse save() {
        SuResponse response = new SuResponse();


        HibernateCore.save(this);

        response.setMessage(SuResponse.SAVE_SUCCESS);
        return response;
    }

    @Override
    public SuResponse delete() {
        SuResponse response = new SuResponse();
        response.setMessage(SuResponse.DELETE_SUCCESS);
        return response;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public boolean disableLog() {
        return false;
    }

    @Override
    public boolean hasDuplicate() {
        return false;
    }

    @Override
    public RecordProps getField() {
        RecordProps props = new RecordProps();
        props.fields = _fields;
        props.idField = ID;
        props.title = "Ülke";
        return props;
    }
}
