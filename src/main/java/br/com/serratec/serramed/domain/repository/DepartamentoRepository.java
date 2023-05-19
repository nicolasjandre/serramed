package br.com.serratec.serramed.domain.repository;

import br.com.serratec.serramed.domain.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
