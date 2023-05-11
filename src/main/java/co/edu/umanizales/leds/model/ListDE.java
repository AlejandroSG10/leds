package co.edu.umanizales.leds.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ListDE {

        private NodeDE head;
        private int size;
        private NodeDE tail;
        private List<Led> leds = new ArrayList<>();


    public void addLed(Led led){
        if (head == null){
            this.head = new NodeDE(led);
        }else {
            NodeDE newNode = new NodeDE(led);
            NodeDE temp = head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            temp.setPrevious(temp);
        }
    }

    public List<Led> print() {
        leds.clear();
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                leds.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return leds;
    }

    public void addToStart(Led led)  {
        NodeDE newNode = new NodeDE(led);
        if (head != null) {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    public void addToEnd(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    /*
    Método para que las luces se prendan desde la mitad
    preguntamos si hay datos
    si hay datos, pregunto si hay más de un dato, si no hay más de un dato, enciendo la cabeza
    sino
    si hay datos preguntamos que si es par
        si es par, cogemos ese led y el siguiente y los encendemos poniéndoles la hora y que se apaguen al pasar 1 segundo,
        llamamos dos ayudantes los cuales nos ayuden a tomar el nodo anterior y el siguiente y los prenda con la fecha actual
         y que al pasar 1 segundo se apaguen
    después decimos que mientras el temp.getNext sea diferente de nulo
    que el nodo temporal obtenga los datos del led y coloque el estado del led en falso, cuando haga esto debemos decirle al localtime que
    guarde la hora
    y le decimos al temporal que coge al nodo anterior que que obtenga los datos del led y que coloque el estado del led en falso tambien,
    debemos tambien guardar la hora a la que se hizo esto

    despues le decimos al temporal que se pase al siguiente y lo encienda, cuando el estado pase a true, el local time debe guardar
    la hora a la que lo hizo y pasado un segundo apagarlo
    tambien le decimos al temporal que va a tomar el nodo anterior que haga lo mismo.

    si no es un numero par
    creamos un temporal que se va a parar en la mitad de la lista
    hacemos que mientras haya datos se haga lo que vamos a pedir a continuacion

        el tamaño de la lista de los leds se divide entre dos para saber la mitad,
        como la mitad de una lista impar es solo un numero, ejemplo de 15 la mitad en una lista seria el numero 8
        tomamos ese nodo y lo encendemos y tomamos la hora en la que se encendio, al pasar un segundo se apaga el led
         y se guarda la hora a la que se hizo
    preguntamos si el temp.getNext es diferente de nulo
    si es diferente a nulo, creamos un temporal el cual tome el nodo previo
       le decimos al temporal que se pase al siguiente y que parado en el siguiente encienda el led
         y guarde la hora a la que se hizo, pasado un segundo y que se apague.
        le decimos tambien al otro temporal que tiene el nodo previo que encienda el anterior y que guarde la hora en lo que lo hizo,
        y pasado un segundo que se apague.

     */






    public void turnOnLightStartInTheMiddle() throws InterruptedException {
            NodeDE temp = head;
            int steps = 1;
        if (size == 1){
            temp.getData().setStatus(true);
            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
        }
        if (size % 2 == 0){
            int middle = (size/2);
            while (temp != null){
                if (steps == (middle + 1)){
                    temp.getData().setStatus(true);
                    temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                    NodeDE tempPrev = temp.getPrevious();
                    tempPrev.getData().setStatus(true);
                    tempPrev.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                    if (temp.getNext() != null){
                        while (temp.getNext() != null){
                            Thread.sleep(1000);
                            temp.getData().setStatus(false);
                            temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                            tempPrev.getData().setStatus(false);
                            tempPrev.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getNext();
                            temp.getData().setStatus(true);
                            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                            tempPrev = temp.getPrevious();
                            tempPrev.getData().setStatus(true);
                            tempPrev.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                        }
                    }
                }
                steps ++;
                temp = temp.getNext();
            }
        }else{
            int medium = (size/2)+1;
            int pasos = 1;
            while (temp != null){
                if (pasos == medium){
                    temp.getData().setStatus(true);
                    temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                    NodeDE tempPrev = temp.getPrevious();
                    if (temp.getNext() != null){
                        Thread.sleep(1000);
                        temp.getData().setStatus(false);
                        temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                        tempPrev.getData().setStatus(false);
                        tempPrev.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                        temp = temp.getNext();
                        temp.getData().setStatus(true);
                        temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                        tempPrev = temp.getPrevious();
                        tempPrev.getData().setStatus(true);
                        tempPrev.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                    }
                }
            }
            pasos ++;
            temp.getNext();
        }
    }

    /*
    Honestamente siento que me hacen falta unas cosas para poder mejorar el codigo, pero asi es como lo pensé
    y lo resolví, siento que hace falta en algunos aspectos de como pensé el algoritmo y como lo resolví
    a medida que lo pensaba me salían mas dudas de como resolverlo, pero pienso que esta bien asi
     */
}


