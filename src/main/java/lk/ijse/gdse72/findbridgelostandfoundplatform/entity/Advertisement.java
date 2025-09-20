package lk.ijse.gdse72.findbridgelostandfoundplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String time;
    private String media; // This can store image path, video URL, or other media references
    private String moreDetails;

    // You might want to add relationship mapping if advertisements are linked to users
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;
}
