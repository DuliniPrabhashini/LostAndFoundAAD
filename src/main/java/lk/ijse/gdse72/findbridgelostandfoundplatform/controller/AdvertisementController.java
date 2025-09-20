package lk.ijse.gdse72.findbridgelostandfoundplatform.controller;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AdvertisementDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/advertisement")
@RequiredArgsConstructor
@CrossOrigin
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @PostMapping("saveAdvertisement")
    public ResponseEntity<ApiResponse<String>> saveAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.saveAdvertisement(advertisementDto);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        201,
                        "Advertisement Saved!",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("updateAdvertisement")
    public ResponseEntity<ApiResponse<String>> updateAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.updateAdvertisement(advertisementDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Advertisement updated successfully!",
                null
        ));
    }

    @DeleteMapping("deleteAdvertisement")
    public ResponseEntity<ApiResponse<String>> deleteAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.deleteAdvertisement(advertisementDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Advertisement deleted successfully!",
                null
        ));
    }

    @GetMapping("getAllAdvertisements")
    public ResponseEntity<ApiResponse<List<AdvertisementDto>>> getAllAdvertisements() {
        List<AdvertisementDto> advertisementDtos = advertisementService.getAllAdvertisements();
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Advertisements retrieved successfully!",
                advertisementDtos
        ));
    }

    @GetMapping("searchAdvertisement/{keyword}")
    public ResponseEntity<ApiResponse<List<AdvertisementDto>>> searchAdvertisement(@PathVariable String keyword) {
        List<AdvertisementDto> advertisementDtos = advertisementService.getAdvertisementsByKeyword(keyword);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Advertisements found successfully!",
                advertisementDtos
        ));
    }

    @GetMapping("getByDate/{date}")
    public ResponseEntity<ApiResponse<List<AdvertisementDto>>> getAdvertisementsByDate(@PathVariable String date) {
        List<AdvertisementDto> advertisementDtos = advertisementService.getAdvertisementsByDate(date);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Advertisements retrieved by date successfully!",
                advertisementDtos
        ));
    }
}
