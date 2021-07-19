package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LetterMetaInfoRequest {
    private String requestUrl = "v2.4/inbox/letters/{id}/meta-info";
    private String id;

    public LetterMetaInfoRequest(String id) {
        this.id = id;
    }

    public Map<String,String> getParamMap() {
        Map<String, String> params = new HashMap<>();
        return params;
    }


    public String getRequestUrl()
    {
        return requestUrl.replaceAll("\\{id\\}",id);
    }
}
