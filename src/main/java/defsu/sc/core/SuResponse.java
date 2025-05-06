package defsu.sc.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class SuResponse implements Serializable {
    public static String SAVE_SUCCESS = "Kayıt Başarılı!";
    public static String DELETE_SUCCESS = "Silme Başarılı!";
    public static String UPDATE_SUCCESS = "Güncelleme Başarılı!";

    public static String SAVE_FAILED = "Kayıt Başarısız!";
    public static String DELETE_FAILED = "Silme Başarısız!";
    public static String UPDATE_FAILED = "Güncelleme Başarısız!";


    @JsonProperty("status")
    public StatusCode status = StatusCode.OK;

    @JsonProperty("message")
    public String message = "";

    @JsonProperty("data")
    public Object data;

    public SuResponse(){
        setStatus(StatusCode.OK);
        setMessage("");
        setData(null);
    }

    public enum StatusCode {
        OK(200, "OK"),
        CREATED(201, "Created"),
        NO_CONTENT(204, "No Content"),
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        FORBIDDEN(403, "Forbidden"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        NOT_IMPLEMENTED(501, "Not Implemented"),
        FAILED(999, "Failed"),
        DUPLICATE(1000, "Duplicate");
        private final int code;
        private final String message;
        StatusCode(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }


    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
