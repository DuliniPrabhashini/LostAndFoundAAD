//package lk.ijse.gdse72.findbridgelostandfoundplatform.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class LostItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String lostItemId;
//    private String lostItemType;
//    private String lostItemName;
//    private String lostItemDescription;
//    private String lostTime;
//    private String lostDate;
//    private String lostLocation;
//    private String lostItemImage; // uparima images 5 k add karanna puluwan wenna wenas karanna
//    private String extraDetails; // phone number ekak, hoyala dena kenekta gift ekak deno wage special details
//}

package lk.ijse.gdse72.findbridgelostandfoundplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostItemId;  // FIXED: Use Long instead of String
    private String lostItemType;
    private String lostItemName;
    private String lostItemDescription;
    private String lostTime;
    private String lostDate;
    private String lostLocation;
    private String lostItemImage;  // you can later map multiple images in a separate table if needed
    private String extraDetails;   // phone number, gift info, etc.
}
