package org.senechka.task3;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Someone extends Human{

    Place state;
    String mood;

    public Someone(Sex sex, int age, String name, Place state) {
        super(sex, age, name);
        this.state = state;
    }

    public void moveTo(Place place){
        setState(place);
        System.out.println(name+" goes to "+ place.placeType);
    }
}
