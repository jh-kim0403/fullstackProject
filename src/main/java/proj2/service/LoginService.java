package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.UserDTO;
import proj2.repos.LoginRepository;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public LoginService(LoginRepository loginRepository, LoginRepository loginRepository1) {
        this.loginRepository = loginRepository;
    }

    public String login(String username, String password) {
        Map<String, Object> userLogin = loginRepository.loginUser(username);
        if (userLogin != null) {
            if(userLogin.get("password").equals(password)) {
                return "success";
            }
            return "password incorrect";
        }
        return "incorrect user email";
    }
}
