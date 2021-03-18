package ba.unsa.etf.termini.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date datum;

    private String vrijeme;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacijent_karton_doktor_id", nullable = false)
    private PacijentKartonDoktor pacijentKartonDoktor;

    public Termin(Date datumPregleda, String vrijemePregleda, PacijentKartonDoktor pacijentKartonDoktor) {
        this.datum = datumPregleda;
        this.vrijeme = vrijemePregleda;
        this.pacijentKartonDoktor = pacijentKartonDoktor;
    }

    public Termin() {

    }
}