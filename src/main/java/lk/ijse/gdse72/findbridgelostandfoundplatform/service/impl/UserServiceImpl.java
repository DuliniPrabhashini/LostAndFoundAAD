package lk.ijse.gdse72.findbridgelostandfoundplatform.service.impl;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.UserDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.User;
import lk.ijse.gdse72.findbridgelostandfoundplatform.exceptions.ResourceNotFound;
import lk.ijse.gdse72.findbridgelostandfoundplatform.repository.UserRepo;
import lk.ijse.gdse72.findbridgelostandfoundplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        if (userDto==null){
            log.error("userDto is null");
            throw new IllegalArgumentException("userDto is null");
        }
        User user = User.builder().userName(userDto.getUserName()).password(passwordEncoder.encode(userDto.getPassword()))
                .id(userDto.getId())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).build();
        userRepo.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        if (userDto==null){
            log.error("userDto is null");
            throw new IllegalArgumentException("userDto is null");
        }
        userRepo.save(modelMapper.map(userDto, User.class));
    }

    @Override
    public void deleteUser(UserDto userDto) {
        if (userDto.getId()==null){
            log.error("id is null");
            throw new IllegalArgumentException("Empty id ! Please provide a valid id");
        }
        userRepo.findById(userDto.getId()).orElseThrow(()->new ResourceNotFound("User with id "+userDto.getId()+" not found !"));
        userRepo.deleteById(userDto.getId());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()){
            log.error("users is empty");
            throw new ResourceNotFound("No users found !");
        }

        return modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
    }

    @Override
    public List<UserDto> getUsersByKeyword(String keyword) {
        if (keyword==null){
            log.error("keyword is null");
            throw new IllegalArgumentException("Empty keyword ! Please provide a valid keyword");
        }

        List<User> allUsers = userRepo.findByKeyword(keyword);
        if (allUsers.isEmpty()){
            log.error("users are empty");
            throw new ResourceNotFound("No users found !");
        }

        return modelMapper.map(allUsers, new TypeToken<List<UserDto>>() {}.getType());
    }

}
