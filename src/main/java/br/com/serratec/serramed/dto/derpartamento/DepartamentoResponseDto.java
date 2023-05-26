package br.com.serratec.serramed.dto.derpartamento;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.dto.funcionario.FuncionarioResponseDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;
import br.com.serratec.serramed.dto.medico.MedicoResponseDto;
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
	private HospitalResponseDto hospital;

	@JsonIgnoreProperties({ "departamentos" })
	private List<MedicoResponseDto> medicos;

	@JsonIgnoreProperties({ "departamento" })
	private List<FuncionarioResponseDto> funcionarios;
}
