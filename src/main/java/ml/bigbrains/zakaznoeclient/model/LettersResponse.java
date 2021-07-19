package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

import java.util.List;

@Data
public class LettersResponse {
    private List<Letter> data;
    private Boolean moreDataAvailable;
    private String pagingState;
}
