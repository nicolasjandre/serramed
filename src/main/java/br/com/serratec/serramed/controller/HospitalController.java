package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.icrud.ICRUDController;
import br.com.serratec.serramed.domain.service.HospitalService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@Tag(name = "Hospital", description = "Todos os endpoints relacionados a entidade Hospital")
public class HospitalController implements ICRUDController<HospitalRequestDto, HospitalResponseDto> {

	@Autowired
	private HospitalService hospitalService;

	private final String postExpectedJsonPreview = "{\"nome\":\"Hospital Teste\",\"telefone\":\"21223552221\",\"endereco\":{\"rua\":\"Doutor Maicon Silva castro de Moura Estevão\",\"numero\":\"13\",\"complemento\":\"Em frente ao Bramil\",\"bairro\":\"Lagoinha\",\"cidade\":\"Teresópolis\",\"estado\":\"RJ\",\"cep\":\"25995900\"},\"departamentos\":[{\"nome\":\"Endrocrinologista\"},{\"nome\":\"Nutricionista\"},{\"nome\":\"Pediatra\"}]}";

	@Override
	@PostMapping
	@Operation(summary = "Cria uma novo Hospital", description = "Campo 'nome' não pode estar vazio!")
	public ResponseEntity<HospitalResponseDto> create(
			@Valid @RequestBody @Schema(description = "JSON payload for creating a hospital", example = postExpectedJsonPreview) HospitalRequestDto dto) {
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
	public ResponseEntity<HospitalResponseDto> updateById(
			@Valid @RequestBody @Schema(description = "JSON payload for updating a hospital", example = "{\"nome\":\"Hospital Teste\",\"telefone\":\"21223552221\"}") HospitalRequestDto dto,
			@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.updateById(dto, id));
	}

	@Override
	@DeleteMapping("/{id}")
	@Operation(summary = "Delela um hospital.", description = "Verifica se o 'ID' passado no parâmetro existe.")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		hospitalService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
