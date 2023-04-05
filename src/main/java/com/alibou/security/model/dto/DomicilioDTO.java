package com.alibou.security.model.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DomicilioDTO {
    private Long id;

    private String calle;

    private String numero;

}
