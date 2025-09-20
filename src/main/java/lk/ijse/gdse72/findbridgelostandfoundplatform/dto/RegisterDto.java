package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
//    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String image;
    private Role role;


}
