package lk.ijse.gdse72.findbridgelostandfoundplatform.service.impl;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.LostItemDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.LostItem;
import lk.ijse.gdse72.findbridgelostandfoundplatform.exceptions.ResourceNotFound;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.LostItemRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.LostItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LostItemServiceImpl implements LostItemService {

    @Autowired
    private final LostItemRepo lostItemRepo;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public void saveLostItem(LostItemDto lostItemDto) {
        System.out.println("Service implementation ekata yanawa\n");
        if (lostItemDto==null){
            log.error("lostItemDto is null");
            throw new IllegalArgumentException("lostItemDto is null");
        }
        System.out.println("model mapper save ekata kalin line eka\n");
        lostItemRepo.save(modelMapper.map(lostItemDto, LostItem.class));
        System.out.println("model mapper eken save method eka call wenawa\n");
    }

    @Override
    public void updateLostItem(LostItemDto lostItemDto) {
        if (lostItemDto==null){
            log.error("lostItemDto is null");
            throw new IllegalArgumentException("lostItemDto is null");
        }
        lostItemRepo.save(modelMapper.map(lostItemDto, LostItem.class));
    }

    @Override
    public void deleteLostItem(LostItemDto lostItemDto) {
        if (String.valueOf(lostItemDto.getLostItemId())==null){
            log.error("lostItemDto is null");
            throw new IllegalArgumentException("lostItemDto is null");
        }
        lostItemRepo.deleteById(lostItemDto.getLostItemId());
    }

    @Override
    public List<LostItemDto> getLostItems() {
        List<LostItem> lostItems = lostItemRepo.findAll();
        if (lostItems.isEmpty()){
            log.error("Lost Items are empty");
            throw new ResourceNotFound("No Lost Items found !");
        }
        return modelMapper.map(lostItems, new TypeToken<List<LostItemDto>>() {}.getType());
    }

    @Override
    public List<LostItemDto> getLostItemsByKeyword(String keyword) {
        if (keyword==null){
            log.error("keyword is null");
            throw new IllegalArgumentException("Empty keyword ! Please provide a valid keyword");
        }

        List<LostItem> allLostItems = lostItemRepo.findByKeyword(keyword);
        if (allLostItems.isEmpty()){
            log.error("Lost Items are empty");
            throw new ResourceNotFound("No Lost Items found !");
        }

        return modelMapper.map(allLostItems, new TypeToken<List<LostItemDto>>() {}.getType());

    }
}
