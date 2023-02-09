package it.proactivity.demospringdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String detail;
}
