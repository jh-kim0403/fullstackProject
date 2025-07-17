package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.LoginRequestDTO;
import proj2.service.DashboardLoginService;
import proj2.service.DashboardService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class DashboardLoginController {

    private final DashboardLoginService dashboardLoginService;

    public DashboardLoginController(DashboardLoginService dashboardLoginService){
        this.dashboardLoginService = dashboardLoginService;

    }

    @RequestMapping("/api/dashboard/login")
    @PostMapping
    public int loginDashboardUser(@RequestBody LoginRequestDTO loginRequestDTO){
        return dashboardLoginService.loginDashboardUser(loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword());
    }
}
