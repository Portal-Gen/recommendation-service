package portalgen.recommendationservice.exception;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import org.springframework.http.HttpStatus;

public enum InternalServerError implements ResponseError {
    UNKNOWN(0, "UNKNOWN"),
    INTERNAL_SERVER_ERROR(100, "INTERNAL ERROR");

    private int errorCode;
    private String message;

    private InternalServerError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public int getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public Integer getCode() {
        return this.errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}
