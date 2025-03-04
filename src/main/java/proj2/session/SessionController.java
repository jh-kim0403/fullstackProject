package proj2.session;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/setSession")
    public String setSession(@RequestParam String name, HttpSession session) {
        session.setAttribute("username", name);
        return "Session set for user: " + name;
    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return username != null ? "Session found for user: " + username : "No session found.";
    }

    @GetMapping("/invalidateSession")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "Session invalidated.";
    }
}