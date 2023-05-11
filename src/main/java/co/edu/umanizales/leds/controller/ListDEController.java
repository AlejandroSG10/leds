package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.dto.LedDTO;
import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/leds")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds().print(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) {
        Led led = new Led(ledDTO.getId(), false);
        listDEService.getLeds().addLed(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "el led fue añadido", null), HttpStatus.OK);

    }

    @GetMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Led led){

        listDEService.getLeds().addToStart(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "la bombilla fue añadida al inicio", null), HttpStatus.OK);

    }

    @GetMapping(path = "/addtoend")
    public ResponseEntity<ResponseDTO> addToEnd(@RequestBody Led led){

        listDEService.getLeds().addToEnd(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "la bombilla fue añadida al final", null), HttpStatus.OK);

    }
    @GetMapping(path = "/turnonlightstartinthemiddle")
    public ResponseEntity<ResponseDTO> turnOnLightStartInTheMiddle(){
        try{
            listDEService.getLeds().turnOnLightStartInTheMiddle();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se encendieron los leds", null), HttpStatus.OK);

    }

}


