package proj2.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RecaptchaResponse {
    private boolean success;

    @JsonProperty("challenge_ts")
    private String challengeTs;

    private String hostname;

    @JsonProperty("error-codes")
    private List<String> errorCodes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getters and setters for other fields if needed
}