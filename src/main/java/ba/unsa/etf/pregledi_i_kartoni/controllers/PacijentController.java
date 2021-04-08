package ba.unsa.etf.pregledi_i_kartoni.controllers;

import ba.unsa.etf.pregledi_i_kartoni.models.Korisnik;
import ba.unsa.etf.pregledi_i_kartoni.models.Pacijent;
import ba.unsa.etf.pregledi_i_kartoni.requests.DodajPacijentaRequest;
import ba.unsa.etf.pregledi_i_kartoni.requests.UrediKartonRequest;
import ba.unsa.etf.pregledi_i_kartoni.responses.KartonResponse;
import ba.unsa.etf.pregledi_i_kartoni.responses.PacijentResponse;
import ba.unsa.etf.pregledi_i_kartoni.responses.Response;
import ba.unsa.etf.pregledi_i_kartoni.services.PacijentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class PacijentController {

    private final PacijentService pacijentService;

    private final List<Pacijent> pacijenti;



    // prikaz pacijenta na osnovu id
    @GetMapping("/pacijent/{idPacijenta}")
    public ResponseEntity<PacijentResponse> dajPacijenta(@PathVariable(value = "idPacijenta") Long idPacijenta){
        PacijentResponse trazeniPacijent = pacijentService.dajPacijentaNaOsnovuId(idPacijenta);
        return ResponseEntity.ok(trazeniPacijent);
    }

    // prikaz svih pacijenata
    @GetMapping("/svi-pacijenti")
    public ResponseEntity<List<PacijentResponse>> dajSvePacijente(){
        List<PacijentResponse> pacijenti = pacijentService.dajSvePacijente();
        return ResponseEntity.ok(pacijenti);
    }


    // prikaz kartona na osnovu id pacijenta
    @GetMapping("/karton/{idPacijenta}")
    public ResponseEntity<KartonResponse> dajKarton(@PathVariable(value = "idPacijenta") Long idPacijenta){
        KartonResponse trazeniKarton = pacijentService.dajKartonNaOsnovuId(idPacijenta);
        return ResponseEntity.ok(trazeniKarton);
    }

    // prikaz svih kartona
    @GetMapping("/svi-kartoni")
    public ResponseEntity<List<KartonResponse>> dajSveKartone(){
        List<KartonResponse> kartoni = pacijentService.dajSveKartone();
        return ResponseEntity.ok(kartoni);
    }

    // filtriranje kartona
    @GetMapping("/karton")
    public ResponseEntity<List<KartonResponse>> filtrirajKartone(@RequestParam(name = "ime", required = false) String ime,
                                                     @RequestParam(name = "prezime", required = false) String prezime,
                                                     @RequestParam(name = "spol", required = false) String spol,
                                                     @RequestParam(name = "krvnaGrupa", required = false) String krvnaGrupa,
                                                     @RequestParam(name = "hronicneBolesti", required = false) String hronicneBolesti,
                                                     @RequestParam(name = "hronicnaTerapija", required = false) String hronicnaTerapija) {
        List<KartonResponse> trazeniKartoni = pacijentService.filtrirajKartone(ime, prezime, spol, krvnaGrupa, hronicneBolesti, hronicnaTerapija);
        return ResponseEntity.ok(trazeniKartoni);
    }


    // filtriranje pacijenta
    @GetMapping("/pacijent")
    public ResponseEntity<List<PacijentResponse>> filtrirajPacijente(@RequestParam(name = "ime", required = false) String ime,
                                                                 @RequestParam(name = "prezime", required = false) String prezime) {
        List<PacijentResponse> trazeniPacijenti = pacijentService.filtrirajPacijente(ime, prezime);
        return ResponseEntity.ok(trazeniPacijenti);
    }



    // pohrana pacijenta
    @PostMapping("/dodaj-pacijenta")
    public ResponseEntity<Response> dodajPacijenta(@RequestBody DodajPacijentaRequest dodajPacijentaRequest) {
        Response response = pacijentService.dodajPacijenta(dodajPacijentaRequest);
        return ResponseEntity.ok(response);
    }

    // uredjivanje kartona
    @PutMapping("/karton/{id}")
    public  ResponseEntity<Response> urediKarton(@RequestBody UrediKartonRequest urediKartonRequest, @PathVariable Long id){
        Response response = pacijentService.urediKarton(id, urediKartonRequest);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleNoSuchElementFoundException(
            ConstraintViolationException exception
    ) {
        String message="";
        List<String> messages = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        for (int i =0; i<messages.size();i++)
            if(i<messages.size()-1) message += messages.get(i)+ "; ";
            else message += messages.get(i);
        return new Response(message,400);
    }




}

