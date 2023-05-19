package br.com.serratec.serramed.controller;

import br.com.serratec.serramed.controller.CRUD.CRUDController;
import br.com.serratec.serramed.domain.service.HospitalService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController implements CRUDController<HospitalRequestDto, HospitalResponseDto> {

	@Autowired
	private HospitalService hospitalService;

	@Override
	public ResponseEntity<HospitalResponseDto> create(HospitalRequestDto dto) {
		return null;
	}

	@Override
	public ResponseEntity<HospitalResponseDto> findById(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<HospitalResponseDto>> findAll() {
		return null;
	}

	@Override
	public ResponseEntity<HospitalResponseDto> updateById(HospitalRequestDto dto, Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deleteById(Long id) {
		return null;
	}
}
