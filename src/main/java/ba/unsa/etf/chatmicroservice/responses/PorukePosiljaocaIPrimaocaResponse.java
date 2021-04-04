package ba.unsa.etf.chatmicroservice.responses;

import ba.unsa.etf.chatmicroservice.models.Poruka;
import ba.unsa.etf.chatmicroservice.projections.PorukaProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Data
@AllArgsConstructor
@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class PorukePosiljaocaIPrimaocaResponse {

    private List<PorukaProjection> poruke;
}