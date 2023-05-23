package co.edu.umanizales.leds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Led {
    private int id;
    private Boolean status;
    private LocalTime OnDate;
    private LocalTime OffDate;
    private String color;
    private Boolean ledMolten;

    public Led(int id, boolean status, String color, boolean ledMolten) {
        this.id = id;
        this.status = status;
        this.color = color;
        this.ledMolten = ledMolten;
    }


}
