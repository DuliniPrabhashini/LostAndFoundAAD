package lk.ijse.gdse72.findbridgelostandfoundplatform.repository;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepo extends JpaRepository<LostItem, Long> {
    @Query("SELECT l FROM LostItem l WHERE " +
            "LOWER(l.lostItemType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.lostItemName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.lostItemDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.lostLocation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.extraDetails) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LostItem> findByKeyword(@Param("keyword") String keyword);
}
