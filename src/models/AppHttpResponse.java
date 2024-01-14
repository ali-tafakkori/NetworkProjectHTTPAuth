package models;

public class AppHttpResponse {
    public AppHttpStatus status  = AppHttpStatus.OK;
    public String content = "";

    @Override
    public String toString() {
        return "HTTP/1.1 " + status.code + " " + status.name() + "\r\nContent-Length: " + content.getBytes().length + "\r\n\r\n" + content;
    }
}
