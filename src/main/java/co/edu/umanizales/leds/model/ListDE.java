package co.edu.umanizales.leds.model;

import co.edu.umanizales.leds.exception.ListDEException;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListDE {

    private NodeDE head;
    private int size;
    private NodeDE tail;
    private List<Led> leds = new ArrayList<>();

    public void addLed(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (head == null) {
            head = newNode;
            newNode.setNext(head);
            newNode.setPrevious(head);
        }
        if (size == 1) {
            head.setPrevious(newNode);
            head.setNext(newNode);
            newNode.setNext(head);
            newNode.setPrevious(head);
        }
        NodeDE laterNode = head.getPrevious();
        if (size > 1) {
            head.setPrevious(newNode);
            newNode.setNext(head);
            newNode.setPrevious(laterNode);
            laterNode.setNext(newNode);
        }
        size++;
    }

    public Led[] print() throws ListDEException {
        Led[] ledList = new Led[size];
        if (size == 0) {
            return ledList;
        }
        int num = 0;
        NodeDE temp = head;

        if (temp == null) {
            throw new ListDEException("La lista está vacía");
        }

        do {
            ledList[num] = temp.getData();
            temp = temp.getNext();
            num++;
        } while (temp != head);

        return ledList;
    }


    public void addToStart(Led led) {
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
    que se pase al siguiente sea nula
    y hasta ahí va mi codigo
     */

    public void turnOnLightStartInTheMiddle() {
        if (head != null) {
            NodeDE temp = head;
            int pasos = 1;
            int medium;
            if ((size % 2) != 0) {
                medium = (size / 2) + 1;
                while (temp != null) {

                    if (pasos == medium) {
                        NodeDE next = temp;
                        if (temp.getData().getLedMolten() != true) {
                            temp.getData().setStatus(true);
                            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                        } else {
                            temp.getData().setStatus(false);
                            temp.getData().setOnDate(null);
                            temp.getData().setOffDate(null);
                        }


                        while (next.getNext() != null) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (temp.getData().getLedMolten() != true) {
                                temp.getData().setStatus(false);
                                temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            }
                            if (temp.getData().getLedMolten() != true) {
                                next.getData().setStatus(false);
                                next.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            }

                            temp = temp.getPrevious();
                            next = next.getNext();
                            if (temp.getData().getLedMolten() != true) {
                                temp.getData().setStatus(true);
                                temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            } else {
                                temp.getData().setStatus(false);
                                temp.getData().setOnDate(null);
                                temp.getData().setOffDate(null);
                            }
                            if (temp.getData().getLedMolten() != true) {
                                next.getData().setStatus(true);
                                next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            } else {
                                temp.getData().setStatus(false);
                                temp.getData().setOnDate(null);
                                temp.getData().setOffDate(null);
                            }
                        }
                    }
                    pasos++;
                    temp = temp.getNext();
                }


            } else {
                medium = size / 2;

                while (temp != null) {
                    if (pasos == medium) {

                        NodeDE Next = temp.getNext();
                        if (temp.getData().getLedMolten() != true) {
                            temp.getData().setStatus(true);
                            temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                        } else {
                            temp.getData().setStatus(false);
                            temp.getData().setOnDate(null);
                            temp.getData().setOffDate(null);
                        }
                        if (temp.getData().getLedMolten() != true) {
                            Next.getData().setStatus(true);
                            Next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                        } else {
                            temp.getData().setStatus(false);
                            temp.getData().setOnDate(null);
                            temp.getData().setOffDate(null);
                        }

                        while (Next.getNext() != null) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (temp.getData().getLedMolten() != true) {
                                temp.getData().setStatus(false);
                                temp.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            }
                            if (temp.getData().getLedMolten() != true) {
                                Next.getData().setStatus(false);
                                Next.getData().setOffDate(LocalTime.from(LocalDateTime.now()));
                            }
                            temp = temp.getPrevious();
                            Next = Next.getNext();
                            if (temp.getData().getLedMolten() != true) {
                                temp.getData().setStatus(true);
                                temp.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            } else {
                                temp.getData().setStatus(false);
                                temp.getData().setOnDate(null);
                                temp.getData().setOffDate(null);
                            }
                            if (temp.getData().getLedMolten() != true) {
                                Next.getData().setStatus(true);
                                Next.getData().setOnDate(LocalTime.from(LocalDateTime.now()));
                            } else {
                                temp.getData().setStatus(false);
                                temp.getData().setOnDate(null);
                                temp.getData().setOffDate(null);
                            }
                        }
                    }
                    pasos++;
                    temp = temp.getNext();
                }
            }
        }
    }


    public void restart() {
        NodeDE temp = head;
        while (temp != null) {
            temp.getData().setStatus(false);
            temp.getData().setOffDate(null);
            temp.getData().setOnDate(null);

            temp = temp.getNext();
        }
    }

    // comienzo de la sustentacion

    /*
    metodo para fundir led
    este metodo tiene que retornar un led que es el que va a estar fundido
    el metodo va a recibir un numero aleatorio y va a fundir un led en la lista
    creamos una variable la cual sea un temporal que me recorra la lista
    y creamos un numero random que sea mayor que el tamaño
    creo un ciclo for el cual agregue un contador y por cada vez que el contador sea menor que el numero random
    se pase al siguiente
    despues creo una variable de tipo led el cual sea el temporal que obtenga los datos y que funda el bombillo
    puedo preguntar que si el estado del bombillo es diferente a true que lo cambie, sino que no retorne nada

     */

    public Led meltLed() {
        NodeDE temp = head;
        Random random = new Random();
        int numRandom = random.nextInt(size + 1);
        for (int counter = 1; counter < numRandom; counter++) {
            temp = temp.getNext();
        }
        Led led = temp.getData();
        if (led.getLedMolten() != true) {
            temp.getData().setLedMolten(true);
            return led;
        } else {
            return null;
        }
    }

    /*
    metodo para cambiar el led fundido
    este me tiene que recibir 2 parametros, un led de la clase Led y el id del led fundido
    comienzo instanciando los datos en una variable que se llame nuevo led el cual me reciba los datos de un led
    pregunto si hay datos
    pregunto que si el tamaño de la lista es 1
        pregunto que si el id que me dan es igual al id del nodo que hay en la lista
            y le pregunto que si esta fundido
            si esta fundido le cambio los datos por la variable nuevo led que es la que me va a ingresar el usuario
        sino no retorno nada
    sino hago un ciclo que me recorra la lista hasta que me encuentre el id que me estan pidiendo
    pregunto que si en ese nodo el led esta fundido
        si esta fundido le cambio los datos
    sino no retorno nada

    y al final retorno el led ya que estamos recibiendo un led.

     */
    public Led changeLedMolten(Led led, int id) {
        NodeDE temp = head;
        Led newLed = new Led(led.getId(), false, led.getColor(), false);
        if (size != 0) {
            if (size == 1) {
                if (id == temp.getData().getId()) {
                    if (temp.getData().getLedMolten() == true) {
                        temp.setData(newLed);
                    } else {
                        return null;
                    }
                }
            }else {
                while (id != temp.getData().getId()) {
                    temp.getNext();
                }
                if (temp.getData().getLedMolten() == true){
                    temp.setData(newLed);
                }else {
                    return null;
                }
            }

        }
        return led;


        // fin de la sustentacion
    }

}






