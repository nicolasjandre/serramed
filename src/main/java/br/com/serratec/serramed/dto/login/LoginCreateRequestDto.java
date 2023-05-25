package br.com.serratec.serramed.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateRequestDto {

    @Email(message = "Campo 'email' precisa ter um email válido")
    private String email;

    @Size(min = 6, message = "A senha precisa ter ao menos 6 caracteres")
    private String password;

    private String passwordConfirmation;

    private Long medicoId;

    private Long funcionarioId;
}
