package br.com.serratec.serramed.dto.funcionario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.domain.model.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDto {

    private Long id;

    private String nome;

    @JsonIgnoreProperties({ "medicos", "funcionarios" })
    private Departamento departamento;
}
