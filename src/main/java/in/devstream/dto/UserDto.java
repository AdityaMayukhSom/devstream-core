package in.devstream.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto implements Serializable {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String fullname;
}
