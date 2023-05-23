package co.edu.umanizales.leds.model;

import lombok.Data;

@Data
public class NodeDE {
    private Led data;
    private NodeDE previous;
    private NodeDE next;

    public NodeDE (Led data){
        this.data = data;
    }
}
