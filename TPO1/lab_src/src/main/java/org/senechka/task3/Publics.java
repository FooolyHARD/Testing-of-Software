package org.senechka.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class Publics {
    String name;
    int count;
    Moods mood;
    String amount;
    int shizofreniaLvl;
    String message;

    @SneakyThrows
    public void shizanutsya() {
        if (shizofreniaLvl <= 10) {
            shizofreniaLvl += 2;
        } else {throw new Exception("Reached max shiza lvl");}
    }

    public Publics(String name, int count, Moods mood){
        this.name = name;
        this.count = count;
        this.mood = mood;
        if (count <= 0){ throw new IllegalArgumentException();}
        if (count > 1){
            this.amount = "Mnogo";
        } else {this.amount = "Odin";}
    }

    public void setCount(int count) {
        this.count = count;
        if (count > 1){
            this.amount = "Mnogo";
        } else {this.amount = "Odin";}
    }
}
