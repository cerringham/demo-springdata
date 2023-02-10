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

    public CustomerDto (Long id, String name, String email, String phoneNumber, String detail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.detail = detail;
    }
}
