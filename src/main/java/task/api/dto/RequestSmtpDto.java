package task.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class RequestSmtpDto {

    @JsonProperty
    @Email
    private String email;
    @JsonProperty
    @NotEmpty
    @Length(min = 1, max = 255)
    private String message;

    public RequestSmtpDto() {
    }

    public RequestSmtpDto(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
