package br.com.serratec.serramed.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.serramed.domain.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> { 
}
