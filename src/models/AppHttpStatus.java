package models;

public enum AppHttpStatus {
    OK(200),
    Unauthorized(401),
    NotFound(404),
    ;

    public final int code;

    private AppHttpStatus(int code) {
        this.code = code;
    }
}
