package org.senechka.task3;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Place {
    Coordinates coordinates;
    final PlaceType placeType;


    public void setCoordinates(Coordinates coordinates) throws Exception {
        if (Coordinates.validate(coordinates.getX(), coordinates.getY())){
            this.coordinates = coordinates;} else {throw new Exception("Unreachable coordinates");}
    }
    public Place(Coordinates coordinates, PlaceType placeType) throws Exception {
        if (Coordinates.validate(coordinates.getX(), coordinates.getY())){
            this.coordinates = coordinates;} else {throw new Exception("Unreachable coordinates");}
        this.placeType = placeType;
    }


}
