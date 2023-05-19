package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.repository.HospitalRepository;
import br.com.serratec.serramed.domain.service.iCrud.ICRUDService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService implements ICRUDService<HospitalRequestDto, HospitalResponseDto> {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public HospitalResponseDto create(HospitalRequestDto dto) {


		return null;
	}

	@Override
	public HospitalResponseDto findById(Long id) {
		return null;
	}

	@Override
	public List<HospitalResponseDto> findAll() {
		return null;
	}

	@Override
	public HospitalResponseDto updateById(HospitalRequestDto dto, Long id) {
		return null;
	}

	@Override
	public void deleteById(Long id) {

	}
}
