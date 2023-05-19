package br.com.serratec.serramed.domain.service;

import br.com.serratec.serramed.domain.repository.EnderecoRepository;
import br.com.serratec.serramed.domain.service.CRUD.CRUDService;
import br.com.serratec.serramed.dto.EnderecoDto.EnderecoRequestDto;
import br.com.serratec.serramed.dto.EnderecoDto.EnderecoResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService implements CRUDService<EnderecoRequestDto, EnderecoResponseDto> {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public EnderecoResponseDto create(EnderecoRequestDto dto) {
		return null;
	}

	@Override
	public EnderecoResponseDto findById(Long id) {
		return null;
	}

	@Override
	public List<EnderecoResponseDto> findAll() {
		return null;
	}

	@Override
	public EnderecoResponseDto updateById(EnderecoRequestDto dto, Long id) {
		return null;
	}

	@Override
	public void deleteById(Long id) {

	}
}
