package defsu.sc.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

public class FKPair implements Serializable {
    @Serial
    private static final long serialVersionUID = 1112233L;

    @JsonProperty("key")
    public long key;

    @JsonProperty("value")
    public String value;

    public FKPair(long key, String value) {
        setKey(key);
        setValue(value.length() > 150 ? value.substring(0, 150) : value);
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
