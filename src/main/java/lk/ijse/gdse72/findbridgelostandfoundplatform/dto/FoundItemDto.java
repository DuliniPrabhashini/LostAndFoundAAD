package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoundItemDto {
    private long foundItemId;
    private String foundItemType;
    private String foundItemName;
    private String foundItemDescription;
    private String foundTime;
    private String foundDate;
    private String foundLocation;
    private String foundItemImage;
    private String extraDetails;
}
