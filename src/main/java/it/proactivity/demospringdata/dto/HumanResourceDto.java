package it.proactivity.demospringdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HumanResourceDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String vatCode;
    private Boolean isCeo;
    private Boolean isCda;

    @Override
    public String toString() {
        return "HumanResourceDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vatCode='" + vatCode + '\'' +
                ", isCeo=" + isCeo +
                ", isCda=" + isCda +
                '}';
    }
}
