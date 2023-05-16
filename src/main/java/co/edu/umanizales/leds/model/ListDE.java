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

    public void addLed(Led led) {
        if (head != null) {
            NodeDE newNode = new NodeDE(led);
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(led);
        }
        size++;
    }
    public void addLedToEnd(Led led) {
        if (head == null) {
            head = new NodeDE(led);
        } else {
            NodeDE newNode = new NodeDE(led);
            NodeDE current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrevious(current);
        }
        size++;
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
    /*
    Algoritmo para encender los leds empezando por la mitad
    preguntamos si hay datos
    si hay, creo 3 variables, un temporal que me recorra la lista
    una que se llame pasos
    y una que se llame medium
    hacemos una condicion que me pregunte que si la lista es impar
    si es impar tomo el tamaño de la lista y lo divido en 2 y le aumento 1 para que se pare en el nodo del medio
    hacemos que mientras el temporal sea diferente de nulo que me haga otras condiciones
    decimos que si los pasos son igual a la mitad de la lista que prenda el led y diga la hora en que se prendió
    y creamos una variable que va a tomar el nodo siguiente mientras que temporal va a tomar el nodo anterior
    adentro de ese condicional decimo que mientras en el siguiente nodo haya datos
    le agrego una excepcion llamada sleep que haga que espere 1 segundo despues de prender un bombillo
    despues le digo al estado del led que se apague y que se guarde la hora en la que se apagó
    despues le digo al temporal que se pase al nodo previo y a la variable que cree que se pase al siguiente
    y digo que el estado de los leds se enciendan y que me diga la hora en que lo hizo,
    con la excepcion que le hice me debe apagar los leds al pasar 1 segundo y guardarme la hora en que lo hizo
    el temporal y la variable deben hacer esto hasta que la variable que se pasa al siguiente sea igual a nulo
    aumento los pasos en 1 y le digo al temporal que se pase al siguiente cada vez que se haga este ciclo
    por otro lado si la lista no es impar
    divido la lista entre 2 para saber la mitad
    y hago un ciclo el cual se haga mientras el temporal sea diferente de nulo
    si pasos es igual a la mitad de la lista
    creo una variable la cual sea el nodo siguiente en los cuales estan los leds
    hago que el estado de los leds de la mitad se enciendan y me guarden la hora en lo que lo hicieron
    despues haría un ciclo el cual me diga que mientras la variable que se pasa al siguiente sea diferente de nulo
    me apague los nodos de la mitad y me guarde la hora y se pase al siguiente y al anterior de esos nodos de la mitad
    cuando se pase, cambia el estado de los leds para que se enciendan y guarde la hora
    al pasar un segundo y con la excepcion ya hecha me debe apagar y decirme la hora en lo que lo hizo
    y asi sería el ciclo hasta que llegue a los extremos o como es decir que hasta que la variable que cree para
    que se pase al siguiente sea nula y hasta ahí va mi codigo
     */

    public  void turnOnLightStartInTheMiddle(){
        if (head != null) {
            NodeDE temp = head;
            int pasos = 1;
            int medium;
            if ((size%2)!=0){
                medium = (size / 2) + 1;
                while (temp!= null){

                    if (pasos == medium){
                        NodeDE next = temp;
                        temp.getData().setStatus(true);
                        temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                        while (next.getNext() != null){

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setStatus(false);
                            temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            next.getData().setStatus(false);
                            next.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrevious();
                            next= next.getNext();

                            temp.getData().setStatus(true);
                            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            next.getData().setStatus(true);
                            next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));



                        }
                    }
                    pasos++;
                    temp= temp.getNext();


                }




            } else{
                medium = size/2;

                while (temp!= null){
                    if (pasos == medium){
                        NodeDE Next = temp.getNext();
                        temp.getData().setStatus(true);
                        temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                        Next.getData().setStatus(true);
                        Next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));

                        while (Next.getNext() != null) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setStatus(false);
                            temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            Next.getData().setStatus(false);
                            Next.getData().setOffDate(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrevious();
                            Next = Next.getNext();

                            temp.getData().setStatus(true);
                            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            Next.getData().setStatus(true);
                            Next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));


                        }
                    }
                    pasos++;
                    temp= temp.getNext();

                }

            }

        }

    }
    public void restart() {
        NodeDE temp = head;
        while (temp != null){
            temp.getData().setStatus(false);
            temp.getData().setOffDate(null);
            temp.getData().setOnDate(null);

            temp = temp.getNext();
        }
    }
}


