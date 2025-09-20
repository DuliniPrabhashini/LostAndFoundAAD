package lk.ijse.gdse72.findbridgelostandfoundplatform.controller;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.FoundItemDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.FoundItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/foundItem")
@RequiredArgsConstructor
@CrossOrigin
public class FoundItemController {

    private final FoundItemService foundItemService;

    // save foundItem
    @PostMapping("saveFoundItem")
    public ResponseEntity<ApiResponse<String>> saveFoundItem(@RequestBody FoundItemDto foundItemDto) {
        foundItemService.saveFoundItem(foundItemDto);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        201,
                        "Found Item Saved !",
                        null
                ), HttpStatus.CREATED
        );
    }

    // update foundItem
    @PutMapping("updateFoundItem")
    public ResponseEntity<ApiResponse<String>> updateFoundItem(@RequestBody FoundItemDto foundItemDto) {
        foundItemService.updateFoundItem(foundItemDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Found Item updated successfully !",
                null
        ));
    }

    // delete foundItem
    @DeleteMapping("deleteFoundItem")
    public ResponseEntity<ApiResponse<String>> deleteFoundItem(@RequestBody FoundItemDto foundItemDto) {
        foundItemService.deleteFoundItem(foundItemDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Found Item deleted successfully !",
                null
        ));
    }

    // search by keyword
    @GetMapping("searchFounditem/{keyword}")
    public ResponseEntity<ApiResponse<List<FoundItemDto>>> searchFoundItem(@PathVariable String keyword) {
        List<FoundItemDto> foundItemDtos = foundItemService.getFoundItemsByKeyword(keyword);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Found result successfully !",
                foundItemDtos
        ));
    }
}
