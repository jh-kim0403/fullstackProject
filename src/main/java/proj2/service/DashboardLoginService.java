package proj2.service;

import org.springframework.stereotype.Service;
import proj2.repos.DashboardLoginRepo;

@Service
public class DashboardLoginService {

    private final DashboardLoginRepo dashboardLoginRepo;

    public DashboardLoginService(DashboardLoginRepo dashboardLoginRepo){
        this.dashboardLoginRepo = dashboardLoginRepo;
    }

    public int loginDashboardUser(String username, String password){
        return dashboardLoginRepo.loginDashboardUser(username, password);
    }
}
