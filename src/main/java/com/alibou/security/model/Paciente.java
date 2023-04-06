package com.alibou.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="pacientes")
public class Paciente {

    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paciente_sequence")
    private Long id;
    private String nombre;
    private String apellido;
    //(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    private String dni;
    private Date fechaAlta;

    private String fotoPerfilUrl;

    private boolean masculino;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos;


}