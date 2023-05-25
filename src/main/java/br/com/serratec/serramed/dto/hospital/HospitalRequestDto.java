package br.com.serratec.serramed.dto.hospital;

import java.util.List;

import br.com.serratec.serramed.dto.derpartamento.DepartamentoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRequestDto {

	@NotBlank(message = "Campo 'nome' não pode estar vazio")
	private String nome;

	@NotBlank(message = "Campo 'telefone' não pode estar vazio")
	private String telefone;

	@NotNull(message = "Campo 'endereco' não pode estar vazio")
	private EnderecoRequestDto endereco;

	@NotNull(message = "Campo 'departamentos' não pode estar vazio")
	private List<DepartamentoRequestDto> departamentos;
}
