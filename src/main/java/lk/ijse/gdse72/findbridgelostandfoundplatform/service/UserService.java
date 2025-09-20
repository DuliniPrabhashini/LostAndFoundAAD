package lk.ijse.gdse72.findbridgelostandfoundplatform.service;

import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.UserDto;
import lk.ijse.gdse72.findbridgelostandfoundplatform.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(UserDto userDto);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByKeyword(String keyword);

}
