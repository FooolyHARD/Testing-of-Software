package org.senechka.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Setter
@Getter
public class Someone extends Human{

    Place state;
    Moods mood;
    String message;

    public Someone(Sex sex, int age, String name, Place state) {
        super(sex, age, name);
        this.state = state;
    }

    public void moveTo(Place place) throws Exception {
        if (place.getPlaceType() == PlaceType.POMOIKA) throw new Exception("You can't move to this place");
        setState(place);
        message =  (name+" goes to "+ place.placeType);
    }
    @SneakyThrows
    public void grow(int ageg){
        age += ageg;
        if (age > 100) {
            setMood(Moods.DEAD);
            throw new Exception(name+" dead...");}
    }


}
