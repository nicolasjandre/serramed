package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.exception.BadRequestException;
import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Endereco;
import br.com.serratec.serramed.domain.repository.EnderecoRepository;
import br.com.serratec.serramed.domain.service.iCrud.ICRUDService;
import br.com.serratec.serramed.dto.endereco.EnderecoRequestDto;
import br.com.serratec.serramed.dto.endereco.EnderecoResponseDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService implements ICRUDService<EnderecoRequestDto, EnderecoResponseDto> {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public EnderecoResponseDto create(EnderecoRequestDto dto) {
		
		Endereco endereco = mapper.map(dto, Endereco.class);
		return mapper.map(enderecoRepository.save(endereco), EnderecoResponseDto.class);
	}

	@Override
	public EnderecoResponseDto findById(Long id) {
		Endereco endereco = checkIfEnderecoExists(id);

		return mapper.map(endereco,EnderecoResponseDto.class);
	}

	@Override
	public List<EnderecoResponseDto> findAll() {
		return enderecoRepository.findAll().stream()
				.map(endereco -> mapper.map(endereco, EnderecoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public EnderecoResponseDto updateById(EnderecoRequestDto dto, Long id) {
		this.findById(id);
		Endereco endereco = mapper.map(dto, Endereco.class);
		endereco.setId(id);
		return mapper.map(enderecoRepository.save(endereco),EnderecoResponseDto.class);
	}

	@Override
	public void deleteById(Long id) {
		Endereco endereco = checkIfEnderecoExists(id);

		if (Objects.nonNull(endereco.getHospital())) {
			throw new BadRequestException("Não é possível deletar um endereço que está relacionado a um hospital");
		}

		enderecoRepository.deleteById(id);
	}

	private Endereco checkIfEnderecoExists(Long id) {
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		
		if (enderecoOpt.isEmpty()){
			throw new NotFoundException("Endereço de id=[" + id + "] não encontrado");
		}

		return enderecoOpt.get();
	}
}