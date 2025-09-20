package lk.ijse.gdse72.findbridgelostandfoundplatform.service;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.FoundItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoundItemService {
    void saveFoundItem(FoundItemDto foundItemDto);
    void updateFoundItem(FoundItemDto foundItemDto);
    void deleteFoundItem(FoundItemDto foundItemDto);
    List<FoundItemDto> getFoundItems();
    List<FoundItemDto> getFoundItemsByKeyword(String keyword);
}
