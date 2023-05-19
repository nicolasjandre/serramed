package br.com.serratec.serramed.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDto {

    @NotBlank(message = "Campo 'nome' não pode estar vazio")
    @Size(min = 3, message = "Campo 'nome' deve ter ao menos 3 caracteres")
    private String nome;

    @NotNull(message = "Campo 'departamentoId' não pode ser nulo")
    private Long departamentoId;
}
