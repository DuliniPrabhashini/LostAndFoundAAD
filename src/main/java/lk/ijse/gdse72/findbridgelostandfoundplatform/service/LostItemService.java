package lk.ijse.gdse72.findbridgelostandfoundplatform.service;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.LostItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LostItemService {
    void saveLostItem(LostItemDto lostItemDto);
    void updateLostItem(LostItemDto lostItemDto);
    void deleteLostItem(LostItemDto lostItemDto);
    List<LostItemDto> getLostItems();
    List<LostItemDto> getLostItemsByKeyword(String keyword);
}
