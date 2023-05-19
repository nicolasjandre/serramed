package br.com.serratec.serramed.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.serramed.domain.model.Medico;
import br.com.serratec.serramed.domain.repository.MedicoRepository;
import br.com.serratec.serramed.domain.service.CRUD.CRUDService;
import br.com.serratec.serramed.dto.medicoDto.MedicoRequestDto;
import br.com.serratec.serramed.dto.medicoDto.MedicoResponseDto;

@Service
public class MedicoService implements CRUDService<MedicoRequestDto, MedicoResponseDto> {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public MedicoResponseDto create(MedicoRequestDto dto) {

        Medico medico = new Medico();

        dto.getListaDepartamentoId().forEach(departamentoId -> {

            // Procurar pelo departamento passando o ID
            // TODO

            // Setar departamento dentro do objeto médico
            // TODO
        });

        medico.setNome(dto.getNome());

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
            throw new RuntimeException();
        }

        return mapper.map(medicoOpt.get(), MedicoResponseDto.class);
    }

    @Override
    public MedicoResponseDto updateById(MedicoRequestDto dto, Long id) {
        
        Medico medico = this.findById(id);

        dto.getListaDepartamentoId().forEach(departamentoId -> {

            // Procurar pelo departamento passando o ID
            // TODO

            // Setar departamento dentro do objeto médico
            // TODO
        });

        medico.setNome(dto.getNome());

        return mapper.map(medico, MedicoResponseDto.class);
    }
}
