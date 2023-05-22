package br.com.serratec.serramed.dto.hospital;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Endereco;
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
    private Endereco endereco;

    @JsonIgnoreProperties({ "hospital", "medicos", "funcionarios" })
    private List<Departamento> departamentos;
}
