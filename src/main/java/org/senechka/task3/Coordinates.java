package org.senechka.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Coordinates {
    private int x;
    private int y;

    public static boolean validate(int x, int y){
        return (x >= -100 && x <= 100) && (y >= -100 && y <= 100);
    }
}
