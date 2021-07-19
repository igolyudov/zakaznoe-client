package ml.bigbrains.zakaznoeclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sender {
    private Long id;
    private String type;
    private String name;
    private String address;
    private String subName;
    private String region;
    private String inn;
}
