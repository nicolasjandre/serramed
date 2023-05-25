package br.com.serratec.serramed.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Medico;
import br.com.serratec.serramed.domain.repository.MedicoRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.medico.MedicoRequestDto;
import br.com.serratec.serramed.dto.medico.MedicoResponseDto;

@Service
public class MedicoService implements ICRUDService<MedicoRequestDto, MedicoResponseDto> {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public MedicoResponseDto create(MedicoRequestDto dto) {

        Medico medico = new Medico();

        dto.getListaDepartamentoId().forEach(departamentoId -> {
            Departamento departamento = mapper.map(departamentoService.findById(departamentoId), Departamento.class);
            medico.addDepartamento(departamento);
        });
        
        medico.setNome(dto.getNome());

        medico.setId(null);
        return mapper.map(medicoRepository.save(medico), MedicoResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        medicoRepository.deleteById(id);
    }

    @Override
    public List<MedicoResponseDto> findAll() {

        return medicoRepository.findAll().stream()
                .map(medico -> mapper.map(medico, MedicoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicoResponseDto findById(Long id) {

        Optional<Medico> medicoOpt = medicoRepository.findById(id);

        if (medicoOpt.isEmpty()) {
            throw new NotFoundException("Médico de id=[" + id + "] não encontrado");
        }

        return mapper.map(medicoOpt.get(), MedicoResponseDto.class);
    }

    @Override
    public MedicoResponseDto updateById(MedicoRequestDto dto, Long id) {

        Medico medico = mapper.map(this.findById(id), Medico.class);

        dto.getListaDepartamentoId().forEach(departamentoId -> {
            Departamento departamento = mapper.map(departamentoService.findById(departamentoId), Departamento.class);
            medico.addDepartamento(departamento);
        });
        medico.setNome(dto.getNome());

        return mapper.map(medicoRepository.save(medico), MedicoResponseDto.class);
    }
}
