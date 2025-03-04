package proj2.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.LoginRequestDTO;
import proj2.DTO.UserDTO;
import proj2.service.LoginService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @RequestMapping("/login")
    public String Login(@RequestBody LoginRequestDTO userInfo){
        return loginService.login(userInfo.getEmail(), userInfo.getPassword());
    }
}
