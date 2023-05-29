package br.com.serratec.serramed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.serramed.controller.icrud.ICRUDController;
import br.com.serratec.serramed.domain.service.MedicoService;
import br.com.serratec.serramed.dto.medico.MedicoRequestDto;
import br.com.serratec.serramed.dto.medico.MedicoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
@Tag(name = "Medico", description = "Todos os endpoints relacionados a entidade Medico")
public class MedicoController implements ICRUDController<MedicoRequestDto, MedicoResponseDto> {

    @Autowired
    private MedicoService medicoService;

    @Override
    @PostMapping
    @Operation(summary = "Salva um cadastro de médico no banco de dados.", description = "Campo 'nome' não pode estar vazio"
            +
            "<br>Campo 'nome' deve ter ao menos 3 caracteres" +
            "<br>Campo 'listaDepartamentoId' não pode ser nulo")
    public ResponseEntity<MedicoResponseDto> create(@RequestBody @Valid MedicoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um médico específico do banco buscando-o por ID.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    @Operation(summary = "Retorna todos os médicos cadastrados no banco de dados.")
    public ResponseEntity<List<MedicoResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de um médico específico buscando-o por ID.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>")
    public ResponseEntity<MedicoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findById(id));

    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um médico específico no banco de dados.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>"
            +
            "<br>Campo 'nome' não pode estar vazio" +
            "<br>Campo 'nome' deve ter ao menos 3 caracteres" +
            "<br>Campo 'listaDepartamentoId' não pode ser nulo")
    public ResponseEntity<MedicoResponseDto> updateById(@RequestBody @Valid MedicoRequestDto dto,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.updateById(dto, id));
    }
}
