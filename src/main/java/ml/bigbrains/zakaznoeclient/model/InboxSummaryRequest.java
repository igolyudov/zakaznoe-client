package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Data
public class InboxSummaryRequest {
    private String requestUrl = "v2.0/inboxsummary";

    private LocalDateTime timeRangeBegin;
    private LocalDateTime timeRangeEnd;

    public InboxSummaryRequest(LocalDateTime timeRangeBegin, LocalDateTime timeRangeEnd) {
        this.timeRangeBegin = timeRangeBegin;
        this.timeRangeEnd = timeRangeEnd;
    }

    public Map<String,String> getParamMap()
    {
        Map<String,String> params = new HashMap<>();
        if(timeRangeBegin!=null && timeRangeEnd!=null) {
            params.put("timeRangeBegin", Long.toString(timeRangeBegin.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
            params.put("timeRangeEnd", Long.toString(timeRangeEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        }
        return params;
    }

}
