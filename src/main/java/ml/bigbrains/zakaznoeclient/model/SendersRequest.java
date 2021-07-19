package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class SendersRequest {

    private String requestUrl = "v2.3/inbox/senders";

    public Map<String,String> getParamMap()
    {
        return new HashMap<>();
    }
}
