package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.iCrud.ICRUDController;
import br.com.serratec.serramed.domain.service.EnderecoService;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController implements ICRUDController<EnderecoRequestDto, EnderecoResponseDto> {

	@Autowired
	private EnderecoService enderecoService;

	@Override
	public ResponseEntity<EnderecoResponseDto> create(EnderecoRequestDto dto) {
		return null;
	}

	@Override
	public ResponseEntity<EnderecoResponseDto> findById(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<EnderecoResponseDto>> findAll() {
		return null;
	}

	@Override
	public ResponseEntity<EnderecoResponseDto> updateById(EnderecoRequestDto dto, Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deleteById(Long id) {
		return null;
	}
}
