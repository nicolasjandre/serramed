package br.com.serratec.serramed.dto.DepartamentoDto;

import br.com.serratec.serramed.domain.model.Hospital;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoRequestDto {

	@NotBlank(message = "Campo 'nome' não pode estar vazio")
	private String nome;

	private Long hospitalId;
}
