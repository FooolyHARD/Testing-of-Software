package org.senechka.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Publics {
    String name;
    int count;
    String mood;
    String amount;

    public Publics(String name, int count, String mood){
        this.name = name;
        this.count = count;
        this.mood = mood;
        if (count <= 0){ throw new IllegalArgumentException();}
        if (count > 1){
            this.amount = "Vse";
        } else {this.amount = "Odin";}
    }

}
