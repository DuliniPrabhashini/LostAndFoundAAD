package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdvertisementDto {
    private Long id;
    private String date;
    private String time;
    private String media;
    private String moreDetails;

    // If linking to users, you might want to include user ID or user DTO
    // private Long userId;
    // private UserDto userDto;
}
