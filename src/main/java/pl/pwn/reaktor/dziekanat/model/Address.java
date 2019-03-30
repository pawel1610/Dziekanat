package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable mozemy tego uzyc aby adres mogl byc podlaczany do kilku lub  klas, bez tego mzomey podpiac tylko do jedenj
public class Address {

    private String street;

    private String city;
}
