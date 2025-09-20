package lk.ijse.gdse72.findbridgelostandfoundplatform.controller;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AuthDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.RegisterDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.User;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.UserRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.AuthService;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.TokenBlackList;
import lk.ijse.gdse72.findbridgelostandfoundplatform.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
//public class AuthController {
//    private final AuthService authService;
//    private final TokenBlackList tokenBlackList;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDto registerDto) {
//        return ResponseEntity.ok(new ApiResponse(
//                200,
//                "OK",
//                authService.register(registerDto)
//
//        ));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse> login(@RequestBody AuthDto authDto) {
//        return ResponseEntity.ok(new ApiResponse(
//                200,"OK", authService.authenticate(authDto)
//        ));
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<ApiResponse> logout(@RequestHeader("Authorization") String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            String liveToken = token.substring(7);
//            Date expiry = jwtUtil.extractExpiration(liveToken);
//            tokenBlackList.addToken(liveToken, expiry);
//            return ResponseEntity.ok(new ApiResponse(200, "Logged out successfully", null));
//        }
//        return ResponseEntity.badRequest().body(new ApiResponse(400, "Invalid token", null));
//    }
//
//}

// change 04 : comment above code and add following part

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;
    private final TokenBlackList tokenBlackList;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(
            @RequestPart RegisterDto registerDto,
            @RequestPart(required = false) MultipartFile image) {
        try {
            return ResponseEntity.ok(new ApiResponse(
                    200,
                    "OK",
                    authService.register(registerDto, image)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(new ApiResponse(
                200,"OK", authService.authenticate(authDto)
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String liveToken = token.substring(7);
            Date expiry = jwtUtil.extractExpiration(liveToken);
            tokenBlackList.addToken(liveToken, expiry);
            return ResponseEntity.ok(new ApiResponse(200, "Logged out successfully", null));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(400, "Invalid token", null));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);

            // Get user details from repository
            User user = userRepo.findByUserName(username).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

            // Convert image bytes to base64 for frontend
            String imageBase64 = null;
            if (user.getImage() != null) {
                imageBase64 = Base64.getEncoder().encodeToString(user.getImage());
            }

            Map<String, Object> profileData = new HashMap<>();
            profileData.put("id", user.getId());
            profileData.put("firstName", user.getFirstName());
            profileData.put("lastName", user.getLastName());
            profileData.put("email", user.getEmail());
            profileData.put("userName", user.getUserName());
            profileData.put("role", user.getRole());
            profileData.put("image", imageBase64);

            return ResponseEntity.ok(new ApiResponse(200, "Success", profileData));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(400, "Invalid token", null));
    }
}
