package lk.ijse.gdse72.findbridgelostandfoundplatform.service.impl;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.FoundItemDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.FoundItem;
import lk.ijse.gdse72.findbridgelostandfoundplatform.exceptions.ResourceNotFound;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.FoundItemRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.FoundItemService;
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
public class FoundItemServiceImpl implements FoundItemService {

    @Autowired
    private final FoundItemRepo foundItemRepo;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public void saveFoundItem(FoundItemDto foundItemDto) {
        if (foundItemDto==null){
            log.error("foundItemDto is null");
            throw new IllegalArgumentException("foundItemDto is null");
        }
        foundItemRepo.save(modelMapper.map(foundItemDto, FoundItem.class));
    }

    @Override
    public void updateFoundItem(FoundItemDto foundItemDto) {
        if (foundItemDto==null){
            log.error("foundItemDto is null");
            throw new IllegalArgumentException("foundItemDto is null");
        }
        foundItemRepo.save(modelMapper.map(foundItemDto, FoundItem.class));
    }

    @Override
    public void deleteFoundItem(FoundItemDto foundItemDto) {
        if (String.valueOf(foundItemDto.getFoundItemId())==null){
            log.error("foundItemDto is null");
            throw new IllegalArgumentException("foundItemDto is null");
        }
        foundItemRepo.deleteById(foundItemDto.getFoundItemId());
    }


    @Override
    public List<FoundItemDto> getFoundItems() {
        List<FoundItem> foundItems = foundItemRepo.findAll();
        if (foundItems.isEmpty()){
            log.error("Found Items are empty");
            throw new ResourceNotFound("No Found Items found !");
        }
        return modelMapper.map(foundItems, new TypeToken<List<FoundItemDto>>() {}.getType());
    }

    @Override
    public List<FoundItemDto> getFoundItemsByKeyword(String keyword) {
        if (keyword==null){
            log.error("keyword is null");
            throw new IllegalArgumentException("Empty keyword ! Please provide a valid keyword");
        }

        List<FoundItem> allFoundItems = foundItemRepo.findByKeyword(keyword);
        if (allFoundItems.isEmpty()){
            log.error("Found Items are empty");
            throw new ResourceNotFound("No Found Items found !");
        }

        return modelMapper.map(allFoundItems, new TypeToken<List<FoundItemDto>>() {}.getType());

    }
}
