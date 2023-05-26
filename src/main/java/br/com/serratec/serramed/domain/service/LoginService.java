package br.com.serratec.serramed.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.serramed.domain.exception.BadRequestException;
import br.com.serratec.serramed.domain.exception.NotFoundException;
import br.com.serratec.serramed.domain.model.Funcionario;
import br.com.serratec.serramed.domain.model.Login;
import br.com.serratec.serramed.domain.model.Medico;
import br.com.serratec.serramed.domain.repository.FuncionarioRepository;
import br.com.serratec.serramed.domain.repository.LoginRepository;
import br.com.serratec.serramed.domain.repository.MedicoRepository;
import br.com.serratec.serramed.dto.login.LoginCreateRequestDto;
import br.com.serratec.serramed.dto.login.LoginCreateResponseDto;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Login> loginOpt = loginRepository.findByEmail(username);

        if (loginOpt.isEmpty()) {
            throw new NotFoundException("Usuário ou senha inválidos");
        }

        return loginOpt.get();
    }

    public LoginCreateResponseDto create(LoginCreateRequestDto dto) {

        if (!dto.getPassword().equals(dto.getPasswordConfirmation())) {
            throw new BadRequestException("A senha e a confirmação de senha precisam ser iguais");
        }

        if (Objects.isNull(dto.getMedicoId()) && Objects.isNull(dto.getFuncionarioId())) {
            throw new BadRequestException("O login precisa estar vinculado a um médico ou a um funcionário");
        } else if (Objects.nonNull(dto.getMedicoId()) && Objects.nonNull(dto.getFuncionarioId())) {
            throw new BadRequestException("O login só pode estar vinculado ou a um médico ou a um funcionário");
        }

        Login login = new Login(null, dto.getEmail(), passwordEncoder.encode(dto.getPassword()),
                null, null);

        if (Objects.nonNull(dto.getMedicoId())) {
            Optional<Medico> medicoOpt = medicoRepository.findById(dto.getMedicoId());

            if (medicoOpt.isEmpty()) {
                throw new NotFoundException("Medico de id=[" + dto.getMedicoId() + "] não encontrado");
            }

            login.setMedico(medicoOpt.get());
        } else {
            Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(dto.getFuncionarioId());

            if (funcionarioOpt.isEmpty()) {
                throw new NotFoundException("Funcionário de id=[" + dto.getFuncionarioId() + "] não encontrado");
            }

            login.setFuncionario(funcionarioOpt.get());
        }

        return mapper.map(loginRepository.save(login), LoginCreateResponseDto.class);
    }

    public List<LoginCreateResponseDto> findAll() {
        return loginRepository.findAll().stream()
                .map(login -> mapper.map(login, LoginCreateResponseDto.class))
                .collect(Collectors.toList());
    }
}
