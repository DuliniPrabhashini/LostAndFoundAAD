package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String image;
    private String role;

}
