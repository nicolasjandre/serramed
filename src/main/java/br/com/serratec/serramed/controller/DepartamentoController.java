package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.iCrud.ICRUDController;
import br.com.serratec.serramed.domain.service.DepartamentoService;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoRequestDto;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController implements ICRUDController<DepartamentoRequestDto, DepartamentoResponseDto> {


	@Autowired
	private DepartamentoService departamentoService;

	@Override
	@PostMapping
	@Operation(summary = "Cria um novo departamento", description = "Campo 'nome' não pode estar vazio.")
	public ResponseEntity<DepartamentoResponseDto> create(@Valid @RequestBody DepartamentoRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.create(dto));
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um departamento específico pelo ID", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<DepartamentoResponseDto> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findById(id));
	}

	@Override
	@GetMapping
	@Operation(summary = "Retorna todos os departamentos cadastrados.")
	public ResponseEntity<List<DepartamentoResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
	}

	@Override
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza todas as propriedades do departamento.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<DepartamentoResponseDto> updateById(@Valid @RequestBody DepartamentoRequestDto dto,@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.updateById(dto,id));
	}

	@Override
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um departamento.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
