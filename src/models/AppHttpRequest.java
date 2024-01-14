package models;

import java.util.HashMap;
import java.util.Map;

public class AppHttpRequest {
    public AppHttpMethod method;
    public String path;
    public Map<String, String> params = new HashMap<>();

    public static AppHttpRequest parse(String data) {
        AppHttpRequest request = new AppHttpRequest();
        String[] split = data.split(" ");

        request.method = AppHttpMethod.valueOf(split[0].trim());
        request.path = split[1].trim();

        String params = null;
        switch (request.method) {
            case GET:
                int index = request.path.indexOf("?");
                if (index >= 0) {
                    params = request.path.substring(index + 1);
                    request.path = request.path.substring(0, index);
                }
                break;
            case POST:
                String[] split2 = data.split("\r\n\r\n");
                if (split2.length > 1) {
                    params = split2[1];
                }
                break;
        }

        if (params != null) {
            String[] splitParams = params.split("&");
            for (String param : splitParams) {
                String[] splitParam = param.split("=");

                String key = splitParam[0].trim();
                String value = null;
                if (splitParam.length > 1) {
                    value = splitParam[1];
                }
                request.params.put(key, value);
            }
        }
        return request;
    }
}
