package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.iCrud.ICRUDController;
import br.com.serratec.serramed.domain.service.EnderecoService;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController implements ICRUDController<EnderecoRequestDto, EnderecoResponseDto> {

	@Autowired
	private EnderecoService enderecoService;

	@Override
	@PostMapping
	@Operation(summary = "Cria um endereço novo", description = "Campo 'nome' não pode estar vazio.")
	public ResponseEntity<EnderecoResponseDto> create(@Valid @RequestBody EnderecoRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.create(dto));
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um endereço específico pelo ID", description ="Verifica se o 'ID' passado no parâmetro existe." )
	public ResponseEntity<EnderecoResponseDto> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findById(id));
	}

	@Override
	@GetMapping
	@Operation(summary = "Retorna todos os endereços cadastrados")
	public ResponseEntity<List<EnderecoResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
	}

	@Override
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza todas as propriedades do endereço.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<EnderecoResponseDto> updateById(@Valid @RequestBody EnderecoRequestDto dto, Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.updateById(dto,id));
	}

	@Override
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um endereço", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		enderecoService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
