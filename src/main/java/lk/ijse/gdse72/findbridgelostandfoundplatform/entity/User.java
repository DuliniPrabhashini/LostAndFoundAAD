package lk.ijse.gdse72.findbridgelostandfoundplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

//    change 01: comment and add fields

//    private String image;
//    @Enumerated(EnumType.STRING) // Add this annotation
//    private Role role; // Change from String to Role enum

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private Role role;
}
