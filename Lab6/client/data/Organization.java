package data;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for describing an element of collection - organization
 */

public class Organization {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private String fullName; //Поле может быть null
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null
    private Coordinates coordinates; //Поле не может быть null

    // constructor for organization
    public Organization(Integer id, String name, Long annualTurnover, String creationDate,  String fullName, OrganizationType type, Address officialAddress, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.creationDate = creationDate;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = officialAddress;
        this.coordinates = coordinates;
    }

    public Organization(){}



    /** method that print Organization in a string representation */
   @Override
    public String toString() {
        return "Organization{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", annualTurnover = " + annualTurnover +
                ", creationDate = " + creationDate +
                ", coordinates = " + coordinates +
                ", fullName = " + fullName +
                ", organizationType = " + type +
                ", address = " + officialAddress +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String makerCreationDate(){
            this.creationDate = String.valueOf(new Date());
            return creationDate;
        }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }
}

