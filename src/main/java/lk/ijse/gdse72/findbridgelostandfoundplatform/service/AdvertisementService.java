package lk.ijse.gdse72.findbridgelostandfoundplatform.service;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {
    void saveAdvertisement(AdvertisementDto advertisementDto);
    void updateAdvertisement(AdvertisementDto advertisementDto);
    void deleteAdvertisement(AdvertisementDto advertisementDto);
    List<AdvertisementDto> getAllAdvertisements();
    List<AdvertisementDto> getAdvertisementsByKeyword(String keyword);
    List<AdvertisementDto> getAdvertisementsByDate(String date);
}
