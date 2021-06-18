package data;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for describing field Address of an element
 */

public class Address {
    private String street; //Длина строки не должна быть больше 148, Поле не может быть null
    private Location town; //Поле не может быть null

    public Address(String street, Location town) {
        this.street = street;
        this.town = town;
    }
    public Address(){}

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    public Location getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    /** method that print coordinates in a string representation */
    @Override
    public String toString() {
        return
                 street + " " + town;
    }
}
