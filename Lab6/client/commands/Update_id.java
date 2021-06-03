package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Class for command "update_id"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Update_id{
    /**method that gets name of collection's element
     * @return String name
     */
    public String makerName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter organization's name: ");
                String name = scanner.nextLine().trim(); // trim receive name without spaces
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty or null");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Name value must be string!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Programm was stopped!");
                System.exit(1);
            }
        }
    }

    /**method that gets X-coordinate of organization
     * @return Double x
     */
    public double makerX() {
        while (true) {
            try {
                System.out.println("Enter X coordinate. Value cannot be empty.");
                Scanner scanner = new Scanner(System.in);
                double x = scanner.nextDouble();
                String iX = Double.toString(x);
                if (iX.isEmpty()) {
                    System.out.println("Coordinate cannot be empty or null");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Coordinate value must be double number!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Programm was stopped!");
                System.exit(1);
            }
        }
    }

    /**method that gets Y-coordinate of organization
     * @return Float y
     */
    public float makerY() {
        while (true) {
            try {
                System.out.println("Enter Y coordinate. Value cannot be empty.");
                Scanner scanner = new Scanner(System.in);
                float y = scanner.nextFloat();
                String iY = Float.toString(y);
                if (iY.isEmpty()) {
                    System.out.println(" Coordinate cannot be empty or null");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Coordinate value must be float number!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /**method that makes coordinates from method makerX and makerY*/
    public Coordinates makerCoordinates() {
        return new Coordinates(makerX(), makerY());
    }

    /**method that gets organization's annual turnover
     * @return Long annualTurnover
     */
    public long makerAnnualTurnover() {
        while (true) {
            try {
                System.out.println("Enter organization's annual turnover. Value must be greater than 0.");
                Scanner scanner = new Scanner(System.in);
                long turnover = scanner.nextLong();
                String ITurnover = String.valueOf(turnover).trim();
                if (turnover <= 0) {
                    System.out.println("Annual turnover must be greater than 0. Try again!");
                    continue;
                }
                return turnover;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Annual turnover value must be long number!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets organization's full name
     * @return String fullName
     */
    public String makerFullName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter organization's FULL name: ");
                String fullname = scanner.nextLine().trim();
                return fullname;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Name value must be string!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets organization's type
     * @return OrganizationType type
     */
    public OrganizationType makerOrganizationType() {
        while (true) {
            try {
                System.out.println("Choose type of organization. Enter the number which respond for desired type.");
                System.out.println("Types: 'TRUST' - 1, 'PRIVATE_LIMITED_COMPANY' - 2, 'OPEN_JOINT_STOCK_COMPANY' - 3");
                Scanner scanner = new Scanner(System.in);
                int type = scanner.nextInt();
                switch (type) {
                    case 1:
                        return OrganizationType.TRUST;
                    case 2:
                        return OrganizationType.PRIVATE_LIMITED_COMPANY;
                    case 3:
                        return OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                    default:
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Organziation type must be a number (1, 2, 3). Try again!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets address of organization(street)
     * @return String street
     * */
    public String makerAddressStreet() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter organization's street address: ");
                String street = scanner.nextLine().trim();
                if (street.length() > 148) {
                    System.out.println("Street value cannot be greater than 148. Try again!");
                    continue;
                }
                if (street.isEmpty()) {
                    System.out.println("Name cannot be empty or null");
                    continue;
                }
                return street;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Street value must be string!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets organization's town X-coordinate
     * @return x
     */
    public int makertownX() {
        while (true) {
            try {
                System.out.println("Enter town's X coordinate. Value cannot be empty.");
                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                String iX = Integer.toString(x);
                if (iX.isEmpty()) {
                    System.out.println("Coordinate cannot be empty or null");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Coordinate value must be integer number!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets organization's town Y-coordinate
     * @return y
     */
    public long makertownY() {
        while (true) {
            try {
                System.out.println("Enter town's Y coordinate. Value cannot be empty.");
                Scanner scanner = new Scanner(System.in);
                long y = scanner.nextLong();
                String iY = Long.toString(y);
                if (iY.isEmpty()) {
                    System.out.println("Coordinate cannot be empty or null");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Coordinate value must be long number!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that gets address of organization(name of town)
     * @return town
     */
    public String makerTownName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter organization's town: ");
                String town = scanner.nextLine().trim();
                if (town.isEmpty()) {
                    System.out.println("Town value cannot be empty or null");
                    continue;
                }
                return town;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Town value must be string!");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped!");
                System.exit(1);
            }
        }
    }

    /** method that makes organization's town name and coordinates
     *from methods makertownX, makertownY,makerTownName
     */
    public Location makerLocation() {
        return new Location(makertownX(), makertownY(), makerTownName());
    }

    /** method that makes official organization's address from methods makerAddressStreet and makerLocation */
    public Address makerAddress() {
        return new Address(makerAddressStreet(), makerLocation());
    }

    /**
     * Method for realizing this command
     *  @param orgId - string representation of organization's id
     */
    public void update(String orgId) throws  InterruptedException {
        int id = Integer.parseInt(orgId);
    Organization newOrg = new Organization(id, makerName(), makerAnnualTurnover(), "0",
            makerFullName(), makerOrganizationType(), makerAddress(), makerCoordinates());
        ClientTCP sender = new ClientTCP();
        sender.setOrg(newOrg);
        sender.setCommand("update_id");
        sender.sending(sender);

}
}
