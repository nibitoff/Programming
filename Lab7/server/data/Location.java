package data;

import java.time.LocalDateTime;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for describing field Location of element
 */

public class Location {
    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private String name; //Поле не может быть null

    public Location(int x, long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public Location(){}


    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    /** method that print coordinates in a string representation */
    @Override
    public String toString() {
        return  x +
                " " + y +
                " " + name;
    }
}
