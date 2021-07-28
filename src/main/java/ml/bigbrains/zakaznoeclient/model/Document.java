package ml.bigbrains.zakaznoeclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
    private String title;
    private String registrationDate;
}
