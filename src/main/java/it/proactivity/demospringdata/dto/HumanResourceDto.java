package it.proactivity.demospringdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HumanResourceDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String vatCode;
    private Boolean isCeo;
    private Boolean isCda;
}
