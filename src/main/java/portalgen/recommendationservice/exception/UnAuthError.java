package portalgen.recommendationservice.exception;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.springframework.http.HttpStatus;

public enum UnAuthError implements ResponseError {
    UNKNOWN(0, "UNKNOWN"),
    INVALID_ACCESS_TOKEN(100, "Access token expired or missed"),
    ILLEGAL_ACCESS_TOKEN(101, "Access token illegal"),
    FORBIDDEN(102, "Forbidden you do not have permission to access this resource");

    private int errorCode;
    private String message;

    private UnAuthError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public int getStatus() {
        return HttpStatus.UNAUTHORIZED.value();
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
