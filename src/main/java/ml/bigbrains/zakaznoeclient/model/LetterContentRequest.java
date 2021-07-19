package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class LetterContentRequest {

    private String requestUrl = "v2.1/inbox/letters/{id}/content";
    private String id;

    public LetterContentRequest(String id) {
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
