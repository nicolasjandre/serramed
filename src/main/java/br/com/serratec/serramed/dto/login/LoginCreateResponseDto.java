package br.com.serratec.serramed.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.domain.model.Funcionario;
import br.com.serratec.serramed.domain.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateResponseDto {

    private Long id;

    private String email;

    @JsonIgnoreProperties({ "login", "departamentos" })
    private Medico medico;

    @JsonIgnoreProperties({ "login", "departamento" })
    private Funcionario funcionario;
}
