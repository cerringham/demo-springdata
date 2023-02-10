package it.proactivity.demospringdata.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.codec.StringDecoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String detail;

    public CustomerDto(String name, String email, String phoneNumber, String detail) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return name + " " + email;
    }
}
