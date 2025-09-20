package lk.ijse.gdse72.findbridgelostandfoundplatform.controller;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.LostItemDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.LostItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lostItem")
@RequiredArgsConstructor
@CrossOrigin
public class LostItemController {

    private final LostItemService lostItemService;

    //save lostItem
    @PostMapping("saveLostItem")
    public ResponseEntity<ApiResponse<String>> saveLostItem(@RequestBody LostItemDto lostItemDto) {
        System.out.println(lostItemDto);
        System.out.println("Dto eka controller ekata enawa\n");
        lostItemService.saveLostItem(lostItemDto);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        201,
                        "Lost Item Saved !",
                        null
                ), HttpStatus.CREATED
        );
    }

    // update lostItem
    @PutMapping("updateLostItem")
    public ResponseEntity<ApiResponse<String>> updateLostItem(@RequestBody LostItemDto lostItemDto) {
        lostItemService.updateLostItem(lostItemDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Lost Item updated successfully !",
                null
        ));
    }

    // delete lostItem
    @DeleteMapping("deleteLostItem")
    public ResponseEntity<ApiResponse<String>> deleteLostItem(@RequestBody LostItemDto lostItemDto) {
        lostItemService.deleteLostItem(lostItemDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Lost Item deleted successfully !",
                null
        ));
    }

    // search by keyword
    @GetMapping("searchLostItem/{keyword}")
    public ResponseEntity<ApiResponse<List<LostItemDto>>> searchLostItem(@PathVariable String keyword) {
        List<LostItemDto> lostItemDtos = lostItemService.getLostItemsByKeyword(keyword);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Found result successfully !",
                lostItemDtos
        ));
    }

}
