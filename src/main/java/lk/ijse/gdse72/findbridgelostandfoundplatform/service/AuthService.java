package lk.ijse.gdse72.findbridgelostandfoundplatform.service;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AuthDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.AuthResponsiveDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.RegisterDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Role;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.User;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.UserRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
//    private final UserRepo userRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthResponsiveDto authenticate(AuthDto authDto) {
//        System.out.println("username "+authDto.getUsername());
//        User user = userRepo.findByUserName(authDto.getUsername()).orElseThrow(() ->
//                new UsernameNotFoundException("User`Not Found"));
//
//        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
//            System.out.println("Password is incorrect !");
//            throw new BadCredentialsException("Incorrect password");
//        }
//        String token = jwtUtil.generateToken(user.getUserName(),user.getRole().name());
////        String token = jwtUtil.generateToken(user.getUserName(),user.getRole());
//        return new AuthResponsiveDto(token);
//    }
//
//    public String register(RegisterDto registerDto) {
//        if (userRepo.findByUserName(registerDto.getUserName()).isPresent()) {
//            System.out.println("User already exists !");
//            throw new RuntimeException("Username Already Exists");
//        }
//// Convert string role to enum
//        Role role;
//        try {
//            role =registerDto.getRole();
//            System.out.println("role "+role);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid role !");
//            throw new RuntimeException("Invalid role: " + registerDto.getRole());
//        }
//
//        User user = User.builder()
////                .id(registerDto.getId())
//                .firstName(registerDto.getFirstName())
//                .lastName(registerDto.getLastName())
//                .email(registerDto.getEmail())
//                .userName(registerDto.getUserName())
//                .password(passwordEncoder.encode(registerDto.getPassword()))
//                .image(registerDto.getImage())
//                .role(role) // Use enum instead of string
//                .build();
//        userRepo.save(user);
//        return "User Registration Success";
//    }

    // change 03 : comment above code and add following part
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponsiveDto authenticate(AuthDto authDto) {
        User user = userRepo.findByUserName(authDto.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found"));

        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        String token = jwtUtil.generateToken(user.getUserName(), user.getRole().name());
        return new AuthResponsiveDto(token);
    }

    public String register(RegisterDto registerDto, MultipartFile imageFile) throws IOException {
        if (userRepo.findByUserName(registerDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username Already Exists");
        }

        Role role = registerDto.getRole();
        String userId = generateUserId(role);

        byte[] imageBytes = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageBytes = imageFile.getBytes();
        }

        User user = User.builder()
                .id(userId)
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .userName(registerDto.getUserName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .image(imageBytes)
                .role(role)
                .build();

        userRepo.save(user);
        return "User Registration Success";
    }

    private String generateUserId(Role role) {
        Integer maxId = null;
        String prefix = "";

        switch (role) {
            case ADMIN:
                prefix = "A";
                maxId = userRepo.findMaxAdminId(); // You'll need to add this method
                break;
            case USER:
                prefix = "U";
                maxId = userRepo.findMaxUserId();
                break;
            case SPONSOR:
                prefix = "SP";
                maxId = userRepo.findMaxSponsorId();
                break;
        }

        int nextId = (maxId != null) ? maxId + 1 : 1;
        return prefix + String.format("%03d", nextId);
    }



}
