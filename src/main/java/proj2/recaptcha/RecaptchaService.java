package proj2.recaptcha;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RecaptchaService {

    private static final String SECRET_KEY = "6LdISPQqAAAAAMTdRAw-ZOfH_-y9oNwR_XFaQDaP"; // Replace with your secret key
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyRecaptcha(String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(VERIFY_URL)
                .queryParam("secret", SECRET_KEY)
                .queryParam("response", recaptchaResponse);

        RecaptchaResponse response = restTemplate.postForObject(
                builder.toUriString(), null, RecaptchaResponse.class);

        return response != null && response.isSuccess();
    }
}