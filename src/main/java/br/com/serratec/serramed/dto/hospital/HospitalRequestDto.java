package br.com.serratec.serramed.dto.hospital;

import java.util.List;

import br.com.serratec.serramed.dto.derpartamento.DepartamentoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

	@Valid
	private EnderecoRequestDto endereco;

	@Valid
	private List<DepartamentoRequestDto> departamentos;
}
