package br.com.serratec.serramed.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.dto.funcionario.FuncionarioResponseDto;
import br.com.serratec.serramed.dto.medico.MedicoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateResponseDto {

    private Long id;

    private String email;

    @JsonIgnoreProperties({ "departamentos" })
    private MedicoResponseDto medico;

    @JsonIgnoreProperties({ "departamento" })
    private FuncionarioResponseDto funcionario;
}
