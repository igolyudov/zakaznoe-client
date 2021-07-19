package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class LettersRequest {
    private String requestUrl = "v2.4/inbox/letters";

    private Category category;
    private List<String> senderType;
    private List<Long> senderId;
    private LocalDateTime timeRangeBegin;
    private LocalDateTime timeRangeEnd;
    private String pagingState;
    private Long count;

    public LettersRequest(Category category, List<String> senderType, List<Long> senderId, LocalDateTime timeRangeBegin, LocalDateTime timeRangeEnd, String pagingState, Long count) {
        this.category = category;
        this.senderType = senderType;
        this.senderId = senderId;
        this.timeRangeBegin = timeRangeBegin;
        this.timeRangeEnd = timeRangeEnd;
        this.pagingState = pagingState;
        this.count = count;
    }

    public Map<String,String> getParamMap() {
        Map<String, String> params = new HashMap<>();
        if(category!=null)
            params.put("category", category.name().toLowerCase());
        if(senderType!=null && !senderType.isEmpty())
            params.put("senderType",String.join(",", senderType));
        if(senderId!=null && !senderId.isEmpty())
            params.put("senderId",String.join(",",senderId.stream().map(Object::toString).collect(Collectors.toList())));
        if(timeRangeBegin!=null && timeRangeEnd!=null) {
            params.put("timeRangeBegin", Long.toString(timeRangeBegin.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
            params.put("timeRangeEnd", Long.toString(timeRangeEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        }
        if(pagingState!=null)
            params.put("pagingState",pagingState);
        if(count!=null)
            params.put("count",Long.toString(count));
        return params;
    }

}
