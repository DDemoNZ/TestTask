package task.api.dto;

import java.security.Principal;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserDto implements Principal {

    @NotBlank
    @Length(min = 1, max = 255)
    private String name;
    @NotBlank
    @Length(min = 1, max = 255)
    private String password;
    @Min(1)
    private Integer age;

    public UserDto() {
    }

    public UserDto(@NotBlank @Length(min = 1, max = 255) String name) {
        this.name = name;
    }

    public UserDto(@NotBlank @Length(min = 1, max = 255) String name, @Min(1) Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserDto(@NotBlank @Length(min = 1, max = 255) String name,
                   @NotBlank @Length(min = 1, max = 255) String password) {
        this.name = name;
        this.password = password;
    }

    public UserDto(@NotBlank @Length(min = 1, max = 255) String name,
                   @NotBlank @Length(min = 1, max = 255) String password, @Min(1) Integer age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name)
                && Objects.equals(password, userDto.password)
                && Objects.equals(age, userDto.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, age);
    }
}
