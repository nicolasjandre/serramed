package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.exception.BadRequestException;
import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Endereco;
import br.com.serratec.serramed.domain.model.Hospital;
import br.com.serratec.serramed.domain.repository.HospitalRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.hospital.HospitalRequestDto;
import br.com.serratec.serramed.dto.hospital.HospitalResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

		Endereco endereco = mapper.map(dto.getEndereco(), Endereco.class);

		Hospital hospital = mapper.map(dto, Hospital.class);

		hospital.getDepartamentos().forEach(departamento -> departamento.setHospital(hospital));
		endereco.setEstado(endereco.getEstado().toUpperCase());
		hospital.setEndereco(endereco);

		hospital.setId(null);
		return mapper.map(hospitalRepository.save(hospital),
				HospitalResponseDto.class);
	}

	@Override
	public HospitalResponseDto findById(Long id) {
		
		Hospital hospital = findByIdAndCheckIfExists(id);

		return mapper.map(hospital, HospitalResponseDto.class);
	}

	@Override
	public List<HospitalResponseDto> findAll() {
		return hospitalRepository.findAll().stream()
				.map(hospital -> mapper.map(hospital, HospitalResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public HospitalResponseDto updateById(HospitalRequestDto dto, Long id) {

		if (Objects.nonNull(dto.getDepartamentos())) {
			throw new BadRequestException("Campo 'departamentos' deve estar nulo: não é possível atualizar os departamentos, opte por deletar ou criar novos");
		}

		if (Objects.nonNull(dto.getEndereco())) {
			throw new BadRequestException("Campo 'endereco' deve estar nulo: não é possível atualizar o endereco por este endpoint, opte por atualizar através do update do endereco");
		}

		Hospital hospitalDb = findByIdAndCheckIfExists(id);
		var hospital = mapper.map(dto, Hospital.class);

		hospital.setId(id);
		hospital.setEndereco(hospitalDb.getEndereco());
		hospital.setDepartamentos(hospitalDb.getDepartamentos());

		return mapper.map(hospitalRepository.save(hospital),
				HospitalResponseDto.class);
	}

	@Override
	public void deleteById(Long id) {
		findByIdAndCheckIfExists(id);
		hospitalRepository.deleteById(id);
	}

	private Hospital findByIdAndCheckIfExists(Long id) {

		Optional<Hospital> hospitalOpt = hospitalRepository.findById(id);

		if (hospitalOpt.isEmpty()) {
			throw new NotFoundException("Hospital de id=[" + id + "] não encontrado");
		}

		return hospitalOpt.get();
	}
}
