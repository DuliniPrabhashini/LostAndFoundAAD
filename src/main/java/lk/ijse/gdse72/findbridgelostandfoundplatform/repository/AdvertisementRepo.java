package lk.ijse.gdse72.findbridgelostandfoundplatform.repository;

import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepo extends JpaRepository<Advertisement, Long> {
    // Search advertisements by keyword in moreDetails field
    @Query("SELECT a FROM Advertisement a WHERE LOWER(a.moreDetails) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Advertisement> findByKeyword(@Param("keyword") String keyword);

    // Find advertisements by date
    List<Advertisement> findByDate(String date);

    // Find advertisements by date range (if needed)
    @Query("SELECT a FROM Advertisement a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Advertisement> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
