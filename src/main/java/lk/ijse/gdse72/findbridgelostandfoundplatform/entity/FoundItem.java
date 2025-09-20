package lk.ijse.gdse72.findbridgelostandfoundplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoundItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foundItemId;
    private String foundItemType;
    private String foundItemName;
    private String foundItemDescription;
    private String foundTime;
    private String foundDate;
    private String foundLocation;
    private String foundItemImage; // uparima images 5 k add karanna puluwan wenna wenas karanna
    private String extraDetails;
}
