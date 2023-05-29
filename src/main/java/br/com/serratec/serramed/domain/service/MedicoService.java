package br.com.serratec.serramed.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Departamento;
import br.com.serratec.serramed.domain.model.Medico;
import br.com.serratec.serramed.domain.repository.DepartamentoRepository;
import br.com.serratec.serramed.domain.repository.MedicoRepository;
import br.com.serratec.serramed.domain.service.icrud.ICRUDService;
import br.com.serratec.serramed.dto.medico.MedicoRequestDto;
import br.com.serratec.serramed.dto.medico.MedicoResponseDto;

@Service
public class MedicoService implements ICRUDService<MedicoRequestDto, MedicoResponseDto> {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public MedicoResponseDto create(MedicoRequestDto dto) {

        Medico medico = new Medico();

        List<Departamento> departamentos = getDepartamentos(dto);
        
        medico.setId(null);
        medico.setDepartamentos(departamentos);
        medico.setNome(dto.getNome());

        return mapper.map(medicoRepository.save(medico), MedicoResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        findMedicoByIdAndCheckIfExists(id);
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

        Medico medico = findMedicoByIdAndCheckIfExists(id);

        return mapper.map(medico, MedicoResponseDto.class);
    }

    @Override
    public MedicoResponseDto updateById(MedicoRequestDto dto, Long id) {

        Medico medico = findMedicoByIdAndCheckIfExists(id);

        List<Departamento> departamentos = getDepartamentos(dto);

        medico.setDepartamentos(departamentos);
        medico.setNome(dto.getNome());

        return mapper.map(medicoRepository.save(medico), MedicoResponseDto.class);
    }

    private List<Departamento> getDepartamentos(MedicoRequestDto dto) {

        List<Departamento> listaDepartamentos = new ArrayList<>();

        dto.getListaDepartamentoId().forEach(departamentoId -> {
            Optional<Departamento> departamentoOpt = departamentoRepository.findById(departamentoId);

            if (departamentoOpt.isEmpty()) {
                throw new NotFoundException("Departamento de id=[" + departamentoId + "] não encontrado");
            }

            listaDepartamentos.add(departamentoOpt.get());
        });

        return listaDepartamentos;
    }

    private Medico findMedicoByIdAndCheckIfExists(Long id) {
        
        Optional<Medico> medicoOpt = medicoRepository.findById(id);

        if (medicoOpt.isEmpty()) {
            throw new NotFoundException("Médico de id=[" + id + "] não encontrado");
        }

        return medicoOpt.get();
    }
}
