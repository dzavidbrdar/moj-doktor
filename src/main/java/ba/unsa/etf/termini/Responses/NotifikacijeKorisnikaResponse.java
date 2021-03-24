package ba.unsa.etf.termini.Responses;

import ba.unsa.etf.termini.models.Notifikacija;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Data
@AllArgsConstructor
@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class NotifikacijeKorisnikaResponse {
    private List<Notifikacija> notifikacije;
}
