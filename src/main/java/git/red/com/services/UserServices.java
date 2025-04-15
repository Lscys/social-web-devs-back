package git.red.com.services;

import git.red.com.dto.UserDto;
import git.red.com.models.User;
import git.red.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }


    public String createUser (UserDto userDto) {

        User users = new User();

        users.setName(userDto.getName());
        users.setLast_name(userDto.getLast_name());
        users.setEmail(userDto.getCorreo());
        users.setPhone(userDto.getPhone());
        users.setImage(userDto.getImage());
        users.setUsuario(userDto.getUsuario());
        users.setPassword(userDto.getPassword());

        userRepository.save(users);

        return "Se creo correctamente el usuario";
    }

}
