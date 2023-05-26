package br.com.serratec.serramed.dto.hospital;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.dto.derpartamento.DepartamentoResponseDto;
import br.com.serratec.serramed.dto.endereco.EnderecoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponseDto {

    private Long id;

    private String nome;

    private String telefone;

    @JsonIgnoreProperties({ "hospital" })
    private EnderecoResponseDto endereco;

    @JsonIgnoreProperties({ "hospital", "medicos", "funcionarios" })
    private List<DepartamentoResponseDto> departamentos;
}
