package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Hospital;
import br.com.serratec.serramed.domain.repository.HospitalRepository;
import br.com.serratec.serramed.domain.service.iCrud.ICRUDService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService implements ICRUDService<HospitalRequestDto, HospitalResponseDto> {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public HospitalResponseDto create(HospitalRequestDto dto) {
		Hospital hospital = mapper.map(dto, Hospital.class);
		return mapper.map(hospitalRepository.save(hospital),
				HospitalResponseDto.class);
	}

	@Override
	public HospitalResponseDto findById(Long id) {
		Optional<Hospital> hospitalOpt = hospitalRepository.findById(id);
		if (hospitalOpt.isEmpty()){
			throw  new NotFoundException("Hospital de id=[" + id + "] não encontrado");
		}
		return mapper.map(hospitalOpt.get(),HospitalResponseDto.class);
	}

	@Override
	public List<HospitalResponseDto> findAll() {
		return hospitalRepository.findAll().stream()
				.map(hospital -> mapper.map(hospital, HospitalResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public HospitalResponseDto updateById(HospitalRequestDto dto, Long id) {
		this.findById(id);
		Hospital hospital = mapper.map(dto,Hospital.class);
		hospital.setId(id);
		return mapper.map(hospitalRepository.save(hospital),HospitalResponseDto.class);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		hospitalRepository.deleteById(id);
	}
}
