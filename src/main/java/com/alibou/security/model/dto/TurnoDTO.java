package com.alibou.security.model.dto;

import com.alibou.security.model.Odontologo;
import com.alibou.security.model.Paciente;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TurnoDTO {
    private Long id;
    private Odontologo odontologo;
    private Paciente paciente;

    private Date fechayhora;


}
