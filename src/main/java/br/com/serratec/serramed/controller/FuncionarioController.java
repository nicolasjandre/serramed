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
import br.com.serratec.serramed.domain.service.FuncionarioService;
import br.com.serratec.serramed.dto.funcionario.FuncionarioRequestDto;
import br.com.serratec.serramed.dto.funcionario.FuncionarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController implements ICRUDController<FuncionarioRequestDto, FuncionarioResponseDto> {

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    @PostMapping
    @Operation(summary = "Salva um cadastro de funcionário no banco de dados.", description = "Campo 'nome' não pode estar vazio"
            +
            "<br>Campo 'nome' deve ter ao menos 3 caracteres" +
            "<br>Campo 'departamentoId' não pode ser nulo")
    public ResponseEntity<FuncionarioResponseDto> create(@RequestBody @Valid FuncionarioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um médico específico do banco buscando-o por ID.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    @Operation(summary = "Retorna todos os médicos cadastrados no banco de dados.")
    public ResponseEntity<List<FuncionarioResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de um médico específico buscando-o por ID.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>")
    public ResponseEntity<FuncionarioResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findById(id));

    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um funcionário específico no banco de dados.", description = "<strong>Este método espera uma variável ID no URN da URI</strong><br>"
            +
            "<br>Campo 'nome' não pode estar vazio" +
            "<br>Campo 'nome' deve ter ao menos 3 caracteres" +
            "<br>Campo 'departamentoId' não pode ser nulo")
    public ResponseEntity<FuncionarioResponseDto> updateById(@RequestBody @Valid FuncionarioRequestDto dto,
            @PathVariable Long id) {
                funcionarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.updateById(dto, id));
    }
}