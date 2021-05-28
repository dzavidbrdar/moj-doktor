package ba.unsa.etf.termini.controllers;

import ba.unsa.etf.termini.Requests.DodajNotifikacijuRequest;
import ba.unsa.etf.termini.Responses.NotifikacijaResponse;
import ba.unsa.etf.termini.Responses.NotifikacijeKorisnikaResponse;
import ba.unsa.etf.termini.Responses.Response;
import ba.unsa.etf.termini.models.Notifikacija;
import ba.unsa.etf.termini.models.Pacijent;
import ba.unsa.etf.termini.repositories.PacijentRepository;
import ba.unsa.etf.termini.services.NotifikacijaService;

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
public class NotifikacijaController {
    private final NotifikacijaService notifikacijaService;
    private PacijentRepository pacijentRepository;
    private final Pacijent a = new Pacijent(
            "Ana",
            "Anic",
            new Date(),
            "NekaAdresa",
            "061456321","ana@mail.com");
    private final Pacijent b = new Pacijent(
            "Nina",
            "Ninic",
            new Date(),
            "NekaAdresa",
            "061456321","nina@mail.com");

    private final Date datum = new Date(2021,3,17);
    private final String vrijeme = "9:00";

    @GetMapping("/pohraniPocetneNotifikacije")
    public
    String spasiListuNotifikacija(){
        Notifikacija n1 = new Notifikacija(
                "Otkazan pregled!",
                "Otkazan pregled 21.3.2020.",
                datum,
                vrijeme,
                b);
        Notifikacija n2 =new Notifikacija(
                "Otkazan pregled!",
                "Otkazan pregled 20.3.2020.",
                datum,
                vrijeme,
                a);
        b.getNotifikacije().add(n1);
        a.getNotifikacije().add(n2);

        pacijentRepository.save(a);
        pacijentRepository.save(b);

        return "Spremljene notifikacije!";
    }

    @DeleteMapping("/obrisi-notifikaciju/{id}")
    public ResponseEntity<Response> obrisiNotifikaciju(@PathVariable Long id) {
        Response response = notifikacijaService.obrisiNotifikaciju(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dodaj-notifikaciju")
    public ResponseEntity<Response> dodajNotifikaciju(@RequestBody DodajNotifikacijuRequest dodajNotifikacijuRequest){
        Response response = null;
        try {
            response = notifikacijaService.dodajNotifikaciju(dodajNotifikacijuRequest);
        }catch (Exception e){
            throw e;
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notifikacije-korisnika/{idKorisnika}")
    public ResponseEntity<NotifikacijeKorisnikaResponse> dajNotifikacijeKorisnika(@PathVariable Long idKorisnika){
        NotifikacijeKorisnikaResponse response = notifikacijaService.dajNotifikacijeKorisnika(idKorisnika);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dobavi-notifikaciju/{id}")
    public ResponseEntity<NotifikacijaResponse> dajNotifikaciju(@PathVariable Long id){
        NotifikacijaResponse response = null;
        try {
            response = notifikacijaService.dajNotifikaciju(id);
        } catch (Exception e){
            throw e;
        }
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