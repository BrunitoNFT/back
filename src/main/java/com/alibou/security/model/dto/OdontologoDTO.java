package com.alibou.security.model.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OdontologoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    private boolean masculino;


}
