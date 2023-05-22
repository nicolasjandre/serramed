package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.iCrud.ICRUDController;
import br.com.serratec.serramed.domain.service.HospitalService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController implements ICRUDController<HospitalRequestDto, HospitalResponseDto> {

	@Autowired
	private HospitalService hospitalService;

	@Override
	@PostMapping
	@Operation(summary = "Cria uma novo Hospital", description = "Campo 'nome' não pode estar vazio!")
	public ResponseEntity<HospitalResponseDto> create(@Valid @RequestBody HospitalRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.create(dto));
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um hospital específico pelo ID", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<HospitalResponseDto> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findById(id));
	}

	@Override
	@GetMapping
	@Operation(summary = "Retorna todos os hospitais cadastrados.")
	public ResponseEntity<List<HospitalResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findAll());
	}

	@Override
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza todas as propriedades do hospital.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<HospitalResponseDto> updateById(@Valid @RequestBody HospitalRequestDto dto, @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.updateById(dto, id));
	}

	@Override
	@DeleteMapping("/{id}")
	@Operation(summary = "Delela um hospital.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		hospitalService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
