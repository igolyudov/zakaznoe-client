package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SenderRequest {
    private String requestUrl = "v2.3/senders/{id}";
    private Long id;

    public SenderRequest(Long id) {
        this.id = id;
    }

    public Map<String,String> getParamMap() {
        return new HashMap<>();
    }


    public String getRequestUrl()
    {
        return requestUrl.replaceAll("\\{id\\}",Long.toString(id));
    }
}
