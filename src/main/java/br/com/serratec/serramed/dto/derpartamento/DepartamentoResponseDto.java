package br.com.serratec.serramed.dto.derpartamento;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.domain.model.Funcionario;
import br.com.serratec.serramed.domain.model.Hospital;
import br.com.serratec.serramed.domain.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoResponseDto {

	private Long id;

	private String nome;

	@JsonIgnoreProperties({ "departamentos", "endereco" })
	private Hospital hospital;

	@JsonIgnoreProperties({ "departamentos" })
	private List<Medico> medicos;

	@JsonIgnoreProperties({ "departamento" })
	private List<Funcionario> funcionarios;
}
