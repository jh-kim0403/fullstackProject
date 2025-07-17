package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.UserDTO;
import proj2.repos.LoginRepository;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository, LoginRepository loginRepository1) {
        this.loginRepository = loginRepository;
    }

    public String login(String username, String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        Map<String, Object> userLogin = loginRepository.loginUser(username);
        if (userLogin != null) {
            if (passwordEncryptor.checkPassword(userLogin.get("password").toString(), encryptedPassword)){
                return "success";
            }
            return "password incorrect";
        }
        return "incorrect user email";
    }
}
