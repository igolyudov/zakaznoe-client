package ml.bigbrains.zakaznoeclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Charge {
    private Long amount;
    private String bankName;
    private Long bik;
    private Long kpp;
    private String descriprion;
    private String oktmo;
    private String operationAccount;
    private String correspondingAccount;
    private Long period;
    private String recipient;
    private String uin;
    private String type;
    private String kbk;
    private String name;
}
