package br.com.serratec.serramed.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.serramed.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
