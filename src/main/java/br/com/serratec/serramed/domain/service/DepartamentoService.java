package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.exception.BadRequestException;
import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Funcionario;
import br.com.serratec.serramed.domain.model.Hospital;
import br.com.serratec.serramed.domain.repository.DepartamentoRepository;
import br.com.serratec.serramed.domain.repository.HospitalRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoRequestDto;
import br.com.serratec.serramed.dto.derpartamento.DepartamentoResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoService implements ICRUDService<DepartamentoRequestDto, DepartamentoResponseDto> {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartamentoResponseDto create(DepartamentoRequestDto dto) {

		if (Objects.isNull(dto.getHospitalId())) {
			throw new BadRequestException("Campo 'hospitalId' não pode estar vazio");
		}

		Hospital hospital = findHospitalByIdAndCheckIfExists(dto.getHospitalId());

		Departamento departamento = mapper.map(dto, Departamento.class);

		departamento.setHospital(hospital);

		departamento.setId(null);
		return mapper.map(departamentoRepository.save(departamento), DepartamentoResponseDto.class);
	}

	@Override
	public DepartamentoResponseDto findById(Long id) {

		Departamento departamento = findDepartamentoByIdAndCheckIfExists(id);

		return mapper.map(departamento, DepartamentoResponseDto.class);
	}

	@Override
	public List<DepartamentoResponseDto> findAll() {
		return departamentoRepository.findAll().stream()
				.map(departamento -> mapper.map(departamento, DepartamentoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DepartamentoResponseDto updateById(DepartamentoRequestDto dto, Long id) {

		Departamento departamentoDb = findDepartamentoByIdAndCheckIfExists(id);

		Departamento departamento = mapper.map(dto, Departamento.class);

		departamento.setId(id);

		if (Objects.isNull(dto.getHospitalId())) {
			departamento.setHospital(departamentoDb.getHospital());
		} else {
			Hospital hospital = findHospitalByIdAndCheckIfExists(dto.getHospitalId());

			departamento.setHospital(hospital);
		}

		return mapper.map(departamentoRepository.save(departamento), DepartamentoResponseDto.class);
	}

	@Override
	public void deleteById(Long id) {
		Departamento departamento = findDepartamentoByIdAndCheckIfExists(id);

		for (Funcionario funcionario : departamento.getFuncionarios()) {
			funcionario.setDepartamento(null);
		}

		departamentoRepository.deleteById(id);
	}

	private Hospital findHospitalByIdAndCheckIfExists(Long id) {

		Optional<Hospital> hospitalOpt = hospitalRepository.findById(id);

		if (hospitalOpt.isEmpty()) {
			throw new NotFoundException("Hospital de id=[" + id + "] não encontrado");
		}

		return hospitalOpt.get();
	}

	private Departamento findDepartamentoByIdAndCheckIfExists(Long id) {

		Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);

		if (departamentoOpt.isEmpty()) {
			throw new NotFoundException("Departamento de id=[" + id + "] não encontrado");
		}

		return departamentoOpt.get();
	}
}
