package ba.unsa.etf.doktordetalji.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "korisnik", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="uloga",
        discriminatorType = DiscriminatorType.STRING)
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String ime;

    @NotNull
    private String prezime;

    @NotNull
    @Past
    private Date datumRodjenja;

    @NotNull
    private String adresa;

    @NotNull
    private String brojTelefona;

    @NotNull
    @Email(message = "Email mora biti validan")
    private String email;

    public Korisnik(String ime, String prezime, Date datumRodjenja, String adresa, String brojTelefona,  String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }
}