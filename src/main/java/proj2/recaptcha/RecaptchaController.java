package proj2.recaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecaptchaController {

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/api/recaptcha")
    public ResponseEntity<?> submitForm(@RequestBody RecaptchaDTO formData) {
        String recaptchaToken = formData.getRecaptchaToken();
        boolean isValid = recaptchaService.verifyRecaptcha(recaptchaToken);

        if (!isValid) {
            return ResponseEntity.badRequest().body("Invalid reCAPTCHA");
        }

        // Process the form data as needed
        return ResponseEntity.ok("Form submitted successfully!");
    }
}
