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

    public Led(int id, boolean status) {
        this.id = id;
        this.status = status;
    }


}
