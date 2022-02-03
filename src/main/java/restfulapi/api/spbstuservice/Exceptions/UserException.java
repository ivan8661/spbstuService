package restfulapi.api.spbstuservice.Exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@JsonIgnoreProperties({"httpStatus", "stackTrace", "localizedMessage", "suppressed", "cause"})
public class UserException extends Exception {
    private UserExceptionType type;
    private String message;
    private Object data;

    public UserException(UserExceptionType type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public UserException(UserExceptionType type, String message) {
        this.type = type;
        this.message = message;
        this.data = null;
    }

    public UserException(UserExceptionType type, Object data) {
        this.type = type;
        this.message = type.getDefaultMessage();
        this.data = data;
    }

    public UserException(UserExceptionType type) {
        this.type = type;
        this.message = type.getDefaultMessage();
        this.data = null;
    }

    // Костыль чтоб не словить пустой ответ из-за ошибки сериализации
    public UserException(UserExceptionType type, Exception exception) {
        this.type = type;
        this.message = exception.getLocalizedMessage();
        HashMap<String, Object> debugInfo = new HashMap<>();
        debugInfo.put("message", exception.getMessage());
        debugInfo.put("stackTrace", exception.getStackTrace());
        debugInfo.put("class", exception.getClass().toString());
        this.data = debugInfo;
    }

    public int getId() {
        return type.getHTTPCode();
    }

    public String getCode() {
        return type.getCode();
    }

    public Object getData() {
        if (data == null || data.equals("")) {
            return getStackTrace();
        }
        return data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return type.getHttpStatus();
    }
}

