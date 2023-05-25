package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Hospital;
import br.com.serratec.serramed.domain.repository.DepartamentoRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoRequestDto;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoService implements ICRUDService<DepartamentoRequestDto, DepartamentoResponseDto> {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartamentoResponseDto create(DepartamentoRequestDto dto) {
		Hospital hospital = mapper.map(hospitalService.findById(dto.getHospitalId()), Hospital.class);
		Departamento departamento = mapper.map(dto, Departamento.class);
		departamento.setHospital(hospital);

		departamento.setId(null);
		return mapper.map(departamentoRepository.save(departamento), DepartamentoResponseDto.class);
	}

	@Override
	public DepartamentoResponseDto findById(Long id) {
		Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);
		
		if (departamentoOpt.isEmpty()) {
			throw new NotFoundException("Departamento de id=[" + id + "] não encontrado");
		}
		
		return mapper.map(departamentoOpt.get(), DepartamentoResponseDto.class);
	}

	@Override
	public List<DepartamentoResponseDto> findAll() {
		return departamentoRepository.findAll().stream()
				.map(departamento -> mapper.map(departamento, DepartamentoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DepartamentoResponseDto updateById(DepartamentoRequestDto dto, Long id) {
		this.findById(id);
		
		Departamento departamento = mapper.map(dto, Departamento.class);
		Hospital hospital = mapper.map(hospitalService.findById(dto.getHospitalId()), Hospital.class);

		departamento.setId(id);
		departamento.setHospital(hospital);

		return mapper.map(departamentoRepository.save(departamento), DepartamentoResponseDto.class);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		departamentoRepository.deleteById(id);
	}
}
