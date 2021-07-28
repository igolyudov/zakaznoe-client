package ml.bigbrains.zakaznoeclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

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

//Код разряда почтового отправления:
//0 - Без разряда;
//1 - Правительственное;
//2 - Воинское;
//3 - Служебное;
//4 - Судебное;
//5 - Президентское;
//6 - Кредитное;
//7 - Межоператорское
    private Integer mailRank;

//    Статус вручения/возврата:
//    DELIVERED (Письмо имеет статус Вручено)
//    RETURNED (Письмо имеет статус Возврат)
    private LetterStatus finalStatus;
    private String finalStatusTimestamp;

//    Ассоциативный массив с атрибутами письма. Доступные значения:
//    offence.carNumber - государственный регистрационный номер автомобиля;
//    offence.date - дата и время соверiения нарушения (в миллисекундах);
//    offence.place.address - место совершения нарушения;
//    offence.violation - описание нарушения.
//    Помимо этих атрибутов в этом списке содержатся данные, извлеченные из PDF отправления:
//    @pdf_car_model - марка авто (пример: ФОРД ФОКУС)
//    @pdf_state_sign - государственный регистрационный номер автомобиля (например: Н123МО799)
//    @pdf_violation_date - дата нарушения (например: 06.09.2019)
//    @pdf_violation_time - время нарушения (например: 05:59)
//    @pdf_ruling_date - дата поставления (например: 18.09.2019)
//    @pdf_fine_with_discount - размер штрафа с учетом скидки, рублей (например: 500)
//    @pdf_fine_full - полный размер штрафа, рублей (например: 1000)
//    @pdf_discount_percentage - размер скидки, проценты (50)
//    @pdf_violation_article - статья нарушения (например: ч.5 ст.12.16)
//    @pdf_fine_description - описание нарушения (например: п.10.2 ПДД РФ двигался со скоростью 82 км/ч при
//              максимально разрешенной 60 км/ч, чем превысил максимально разрешенную на данном участке скорость на 22 км/ч.)
    private Map<String,String> attributes;
}
