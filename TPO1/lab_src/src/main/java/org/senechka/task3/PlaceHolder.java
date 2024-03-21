package org.senechka.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaceHolder {
    Publics publics;
    Someone someone;

    public String checkArriving(){
        if (publics.getShizofreniaLvl() >= 5){
            publics.setMood(Moods.SPIT);
        }
        return (publics.name+" feels "+ publics.mood);
    }
}
