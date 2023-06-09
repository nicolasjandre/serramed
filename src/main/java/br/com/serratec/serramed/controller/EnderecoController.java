package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.domain.service.EnderecoService;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "Todos os endpoints relacionados a entidade Endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um endereço específico pelo ID", description ="Verifica se o 'ID' passado no parâmetro existe." )
	public ResponseEntity<EnderecoResponseDto> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findById(id));
	}

	@GetMapping
	@Operation(summary = "Retorna todos os endereços cadastrados")
	public ResponseEntity<List<EnderecoResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza todas as propriedades do endereço.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<EnderecoResponseDto> updateById(@Valid @RequestBody EnderecoRequestDto dto, @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.updateById(dto,id));
	}
}
