package ba.unsa.etf.chatmicroservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Data
@AllArgsConstructor
@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class PorukaResponse {
    private Long id;
    private String sadrzaj;
    private Integer procitana;
    private Date datum;
    private String vrijeme;
    private Long posiljalacId;
    private Long primalacId;
}