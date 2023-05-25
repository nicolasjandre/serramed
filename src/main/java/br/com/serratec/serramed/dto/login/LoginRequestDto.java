package br.com.serratec.serramed.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Campo 'email' não pode estar vazio")
    private String email;

    @NotBlank(message = "Campo 'password' não pode estar vazio")
    private String password;
}