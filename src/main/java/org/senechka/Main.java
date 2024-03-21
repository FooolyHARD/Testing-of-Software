package org.senechka;

import org.senechka.task1.Arcsin;
import org.senechka.task2.MergeSort;
import org.senechka.task3.*;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Publics publics = new Publics("v komante",10, "Plevat'");
        Place room = new Place(new Coordinates(1,1), PlaceType.ROOM);
        Place konura = new Place(new Coordinates(20,20), PlaceType.KONURA);
        Someone pidor = new Someone(Sex.MAN, 12, "chelik", konura);
        pidor.moveTo(room);
        System.out.println(publics.getAmount()+" "+ publics.getName() +" "+"feels "+publics.getMood());
        pidor.setMood("Kapec :(");
        System.out.println(pidor.getName()+ " feels " + pidor.getMood());
    }
}
