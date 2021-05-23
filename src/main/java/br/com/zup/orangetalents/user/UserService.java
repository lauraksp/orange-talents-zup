package br.com.zup.orangetalents.user;
import br.com.zup.orangetalents.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
