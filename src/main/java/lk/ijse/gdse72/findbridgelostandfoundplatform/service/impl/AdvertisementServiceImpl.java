package lk.ijse.gdse72.findbridgelostandfoundplatform.service.impl;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AdvertisementDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Advertisement;
import lk.ijse.gdse72.findbridgelostandfoundplatform.exceptions.ResourceNotFound;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.AdvertisementRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementRepo advertisementRepo;
    private final ModelMapper modelMapper;


    @Override
    public void saveAdvertisement(AdvertisementDto advertisementDto) {
        if (advertisementDto == null) {
            log.error("advertisementDto is null");
            throw new IllegalArgumentException("advertisementDto is null");
        }
        advertisementRepo.save(modelMapper.map(advertisementDto, Advertisement.class));
    }

    @Override
    public void updateAdvertisement(AdvertisementDto advertisementDto) {
        if (advertisementDto == null) {
            log.error("advertisementDto is null");
            throw new IllegalArgumentException("advertisementDto is null");
        }
        advertisementRepo.save(modelMapper.map(advertisementDto, Advertisement.class));
    }

    @Override
    public void deleteAdvertisement(AdvertisementDto advertisementDto) {
        if (advertisementDto == null || advertisementDto.getId() == null) {
            log.error("advertisementDto or its ID is null");
            throw new IllegalArgumentException("advertisementDto or its ID is null");
        }
        advertisementRepo.deleteById(advertisementDto.getId());
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        List<Advertisement> advertisements = advertisementRepo.findAll();
        if (advertisements.isEmpty()) {
            log.error("Advertisements are empty");
            throw new ResourceNotFound("No Advertisements found!");
        }
        return modelMapper.map(advertisements, new TypeToken<List<AdvertisementDto>>() {}.getType());
    }

    @Override
    public List<AdvertisementDto> getAdvertisementsByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            log.error("keyword is null or empty");
            throw new IllegalArgumentException("Empty keyword! Please provide a valid keyword");
        }

        List<Advertisement> advertisements = advertisementRepo.findByKeyword(keyword);
        if (advertisements.isEmpty()) {
            log.error("No advertisements found for keyword: {}", keyword);
            throw new ResourceNotFound("No Advertisements found for the given keyword!");
        }

        return modelMapper.map(advertisements, new TypeToken<List<AdvertisementDto>>() {}.getType());
    }

    @Override
    public List<AdvertisementDto> getAdvertisementsByDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            log.error("date is null or empty");
            throw new IllegalArgumentException("Empty date! Please provide a valid date");
        }

        List<Advertisement> advertisements = advertisementRepo.findByDate(date);
        if (advertisements.isEmpty()) {
            log.error("No advertisements found for date: {}", date);
            throw new ResourceNotFound("No Advertisements found for the given date!");
        }

        return modelMapper.map(advertisements, new TypeToken<List<AdvertisementDto>>() {}.getType());
    }
}
