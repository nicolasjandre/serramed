package br.com.serratec.serramed.dto.HospitalDto;

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
}
