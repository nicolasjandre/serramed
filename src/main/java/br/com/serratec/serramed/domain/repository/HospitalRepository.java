package br.com.serratec.serramed.domain.repository;

import br.com.serratec.serramed.domain.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
