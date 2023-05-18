package br.com.serratec.serramed.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medico_id")
    private Long id;

    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medico_departamento", 
        joinColumns = @JoinColumn(name = "medico_id"), 
        inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private List<Departamento> departamentos;
}
