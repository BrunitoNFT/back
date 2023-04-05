package com.alibou.security.model;



import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "domicilios")
@Getter
@Setter
@ToString
public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilio_sequence",sequenceName = "domicilio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "domicilio_id")
    private Long id;

    private String calle;

    private String numero;



}
