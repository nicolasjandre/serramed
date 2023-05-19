package br.com.serratec.serramed.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String nome;

    @Column(unique = true)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "hospital")
    @JsonBackReference
    private List<Departamento> departamentos;
}
