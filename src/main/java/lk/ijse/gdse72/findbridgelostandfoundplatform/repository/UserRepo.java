package lk.ijse.gdse72.findbridgelostandfoundplatform.repository;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u WHERE " +
            "u.firstName LIKE %:keyword% OR " +
            "u.lastName LIKE %:keyword% OR " +
            "u.email LIKE %:keyword% OR " +
            "u.userName LIKE %:keyword%")
    List<User> findByKeyword(String keyword);

    // change 02 : add 2 methods

    @Query("SELECT MAX(CAST(SUBSTRING(u.id, 2) AS integer)) FROM User u WHERE u.id LIKE 'U%'")
    Integer findMaxUserId();

    @Query("SELECT MAX(CAST(SUBSTRING(u.id, 3) AS integer)) FROM User u WHERE u.id LIKE 'SP%'")
    Integer findMaxSponsorId();

    // Add this method to UserRepo
    @Query("SELECT MAX(CAST(SUBSTRING(u.id, 2) AS integer)) FROM User u WHERE u.id LIKE 'A%'")
    Integer findMaxAdminId();
}
