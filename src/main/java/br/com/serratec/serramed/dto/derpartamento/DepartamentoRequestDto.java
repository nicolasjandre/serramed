package br.com.serratec.serramed.dto.derpartamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoRequestDto {

	@NotBlank(message = "Campo 'nome' não pode estar vazio")
	private String nome;

	@NotNull(message = "Campo 'hospitalId' não pode estar vazio")
	private Long hospitalId;
}
