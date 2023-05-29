package br.com.serratec.serramed.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Funcionario;
import br.com.serratec.serramed.domain.repository.DepartamentoRepository;
import br.com.serratec.serramed.domain.repository.FuncionarioRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.funcionario.FuncionarioRequestDto;
import br.com.serratec.serramed.dto.funcionario.FuncionarioResponseDto;

@Service
public class FuncionarioService implements ICRUDService<FuncionarioRequestDto, FuncionarioResponseDto> {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public FuncionarioResponseDto create(FuncionarioRequestDto dto) {

        Funcionario funcionario = mapper.map(dto, Funcionario.class);

        Departamento departamento = getDepartamento(dto);

        funcionario.setDepartamento(departamento);

        funcionario.setId(null);
        return mapper.map(funcionarioRepository.save(funcionario), FuncionarioResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {

        Funcionario funcionario = findByIdAndCheckIfExists(id);

        funcionario.getDepartamento().getFuncionarios().remove(funcionario);

        funcionarioRepository.deleteById(id);
    }

    @Override
    public List<FuncionarioResponseDto> findAll() {

        return funcionarioRepository.findAll().stream()
                .map(funcionario -> mapper.map(funcionario, FuncionarioResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioResponseDto findById(Long id) {

        Funcionario funcionario = findByIdAndCheckIfExists(id);

        return mapper.map(funcionario, FuncionarioResponseDto.class);
    }

    @Override
    public FuncionarioResponseDto updateById(FuncionarioRequestDto dto, Long id) {

        Funcionario funcionario = findByIdAndCheckIfExists(id);

        Departamento departamento = getDepartamento(dto);

        funcionario.setDepartamento(departamento);
        funcionario.setNome(dto.getNome());

        return mapper.map(funcionarioRepository.save(funcionario), FuncionarioResponseDto.class);
    }

    private Departamento getDepartamento(FuncionarioRequestDto dto) {
        
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(dto.getDepartamentoId());

        if (departamentoOpt.isEmpty()) {
            throw new NotFoundException("Departamento de id=[" + dto.getDepartamentoId() + "] não encontrado");
        }

        return departamentoOpt.get();
    }

    private Funcionario findByIdAndCheckIfExists(Long id) {
        
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);

        if (funcionarioOpt.isEmpty()) {
            throw new NotFoundException("Funcionário de id=[" + id + "] não encontrado");
        }

        return funcionarioOpt.get();
    }
}
