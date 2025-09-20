package lk.ijse.gdse72.findbridgelostandfoundplatform.controller;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.UserDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Role;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    // save user
    @PostMapping("saveUser")
    public ResponseEntity<ApiResponse<String>> saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        201,
                        "User Saved !",
                        null
                ), HttpStatus.CREATED
        );
    }

    // update user
    @PutMapping("updateUser")
    public ResponseEntity<ApiResponse<String>> updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "User updated successfully !",
                null
        ));
    }

    // delete user
    @DeleteMapping("deleteUser")
    public ResponseEntity<ApiResponse<String>> deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "User deleted successfully !",
                null
        ));
    }

    // search by keyword
    @GetMapping("searchUser/{keyword}")
    public ResponseEntity<ApiResponse<List<UserDto>>> searchUser(@PathVariable String keyword) {
        List<UserDto> userDtos = userService.getUsersByKeyword(keyword);
        return ResponseEntity.ok(new ApiResponse<>(
           200,
           "Found result successfully !",
           userDtos
        ));
    }




}
