package ml.bigbrains.zakaznoeclient.model;

import lombok.Data;

@Data
public class InboxSummaryResponse {
    private Long unreadLettersCount;
    private Long readLettersCount;
    private Long archivedLettersCount;
}
