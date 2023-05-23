package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.dto.LedDTO;
import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.exception.ListDEException;
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
    public ResponseEntity<ResponseDTO> getLeds() throws ListDEException {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds().print(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody LedDTO ledDTO) {
        Led led = new Led(ledDTO.getId(), false, ledDTO.getColor(), false);
        listDEService.getLeds().addLed(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el led",
                null), HttpStatus.OK);

    }


    @GetMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Led led){

        listDEService.getLeds().addToStart(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "la bombilla fue añadida al inicio", null), HttpStatus.OK);

    }


    @GetMapping(path = "/turnonlightstartinthemiddle")
    public ResponseEntity<ResponseDTO> turnOnLightStartInTheMiddle() {

        listDEService.getLeds().turnOnLightStartInTheMiddle();

        return new ResponseEntity<>(new ResponseDTO(
                200, "se encendieron las luces empezando por la mitad", null), HttpStatus.OK);
    }
    @GetMapping(path = "/restart")
    public ResponseEntity<ResponseDTO> restartLed(){
        listDEService.getLeds().restart();
        return new ResponseEntity<>(new ResponseDTO(200, "la bombillas reiniciaron su estado", null), HttpStatus.OK);
    }

    @GetMapping(path = "/meltled")
    public ResponseEntity<ResponseDTO> getBathedPet() {
        Led led = listDEService.getLeds().meltLed();
        if (led == null){
            return new ResponseEntity<>(new ResponseDTO(200,
                    "El led ya está fundido", null), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResponseDTO(200,
                    "El led "+led.getId() + " se fundió", null), HttpStatus.OK);
        }
    }
    @PostMapping(path = "/replaceledmolten/{id}")
    public ResponseEntity<ResponseDTO> changeLed(@RequestBody LedDTO ledDTO, @PathVariable int id){

           Led led = new Led(ledDTO.getId(), false, ledDTO.getColor(), false);
           listDEService.getLeds().changeLedMolten(led, id);

        return new ResponseEntity<>(new ResponseDTO(200, "Se ha cambiado el led fundido", null), HttpStatus.OK);
    }
}




