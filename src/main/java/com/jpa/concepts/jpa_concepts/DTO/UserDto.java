package com.jpa.concepts.jpa_concepts.DTO;

import com.jpa.concepts.jpa_concepts.Validations.ValidImageUrl;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //private Integer userId;
    @NotBlank(message="User name is required")
    private String name;
    @Email(message="Invalid email format")
    @NotBlank(message="User email is required")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{4,}$",message="password must contain uppercase,lowercase,a number and be at least 4 characters long")
    private String password;
    @ValidImageUrl(message="User image url is invalid")
    private String userImageUrl;
}
