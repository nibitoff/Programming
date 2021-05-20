package data;

import java.util.Collections;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for describing field Coordinates of element
 */

public class Coordinates {
    private Double x; //Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }


    /** method that prints coordinates in a string representation */
    @Override
    public String toString() {
        return
                x + " " + y ;
}
}
