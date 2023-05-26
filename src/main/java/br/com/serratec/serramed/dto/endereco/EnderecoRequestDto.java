package br.com.serratec.serramed.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDto {

	@NotBlank(message = "Campo 'rua' não pode estar vazio")
	private String rua;

	@NotBlank(message = "Campo 'numero' não pode estar vazio")
	private String numero;

	private String complemento;

	@NotBlank(message = "Campo 'bairro' não pode estar vazio")
	private String bairro;

	@NotBlank(message = "Campo 'cidade' não pode estar vazio")
	private String cidade;

	@NotBlank(message = "Campo 'estado' não pode estar vazio")
	@Size(min = 2, max = 2, message = "Campo 'estado' deve ter 2 caracteres")
	private String estado;

	@NotBlank(message = "Campo 'cep' não pode estar vazio" )
	@Size(min = 8, max = 8, message = "Campo 'cep' precisa ter 8 dígitos")
	private String cep;
}
