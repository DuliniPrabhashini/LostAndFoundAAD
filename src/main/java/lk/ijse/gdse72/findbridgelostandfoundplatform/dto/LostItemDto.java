//package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class LostItemDto {
//    private String lostItemId;
//    private String lostItemType;
//    private String lostItemName;
//    private String lostItemDescription;
//    private String lostTime;
//    private String lostDate;
//    private String lostLocation;
//    private String lostItemImage;
//    private String extraDetails;
//}

package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LostItemDto {
    private Long lostItemId;          // FIXED: match entity type
    private String lostItemType;
    private String lostItemName;
    private String lostItemDescription;
    private String lostTime;
    private String lostDate;
    private String lostLocation;
    private String lostItemImage;     // still a single image path / URL
    private String extraDetails;
}

