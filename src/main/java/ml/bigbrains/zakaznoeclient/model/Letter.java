package ml.bigbrains.zakaznoeclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Letter {
    private String id;
    private String barcode;
    private Boolean registered;
    private Sender sender;
    private Recipient recipient;
    private Charge charge;
    private Document document;
}
