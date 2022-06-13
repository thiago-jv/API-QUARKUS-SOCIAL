package io.github.thiago.melo.quarkussocial.rest.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

}
