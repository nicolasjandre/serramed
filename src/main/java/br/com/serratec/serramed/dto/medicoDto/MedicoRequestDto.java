package br.com.serratec.serramed.dto.medicoDto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequestDto {

    @NotBlank(message = "Campo 'nome' não pode estar vazio")
    @Size(min = 3, message = "Campo 'nome' deve ter ao menos 3 caracteres")
    private String nome;

    @NotNull(message = "Campo 'listaDepartamentoId' não pode ser nulo")
    private List<Long> listaDepartamentoId;
}