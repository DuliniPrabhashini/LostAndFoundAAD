package lk.ijse.gdse72.findbridgelostandfoundplatform.repository;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundItemRepo extends JpaRepository<FoundItem, Long> {
    @Query("SELECT l FROM FoundItem l WHERE " +
            "LOWER(l.foundItemType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.foundItemName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.foundItemDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.foundLocation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.extraDetails) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<FoundItem> findByKeyword(@Param("keyword") String keyword);
}
