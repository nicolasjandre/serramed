package br.com.serratec.serramed.dto.medico;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.dto.derpartamento.DepartamentoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDto {

    private Long id;

    private String nome;

    @JsonIgnoreProperties({ "medicos", "funcionarios" })
    private List<DepartamentoResponseDto> departamentos;
}
