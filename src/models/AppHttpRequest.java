package models;

public class AppHttpRequest {
    public AppHttpMethod method;
    public String path;

    public static AppHttpRequest parse(String data) {
        AppHttpRequest request = new AppHttpRequest();
        String[] split = data.split(" ");

        request.method = AppHttpMethod.valueOf(split[0].trim());
        request.path = split[1].trim();

        return request;
    }
}
