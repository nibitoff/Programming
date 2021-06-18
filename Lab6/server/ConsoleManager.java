import com.opencsv.CSVWriter;
import com.sun.org.apache.xpath.internal.operations.Or;
import data.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static data.OrganizationType.*;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class with user`s commands
 */
public class ConsoleManager {

    /** Field for saving collection into csv file */
    File collectionCsv = CollectionChecker.collectionCsv;
    /** Field for saving date of initialization of the collection */
    Date dateInitial = CollectionChecker.dateInitial;
    /** LinkedList collection for keeping a collection as java-object */
    LinkedList<Organization> organizations = CollectionChecker.organizations;
    /** HashMap collection for making an instruction */
    private final HashMap<String, String> consoleInfo;
    /** Java Util Logging */
    public static Logger LOGGER;



    /**show, print all collection's elements in string representation
     * @return result
     * */
    public String show(){
        String result = "";
        if (organizations.size() != 0) {
            organizations.sort(Comparator.comparingInt(Organization::getId));
            for (Organization org : organizations) {
                System.out.println(org.toString() + "\n");
                result += org.toString() + "\n";
            }
        } else {
            System.out.println("Collection is empty!");
            result = "Collection is empty!";
        }
        return result;
    }

    {
        //creating an instruction
        consoleInfo = new HashMap<>();
        consoleInfo.put("help", " Show available commands");
        consoleInfo.put("info", " Print collection's info to standard output");
        consoleInfo.put("show", " Print all collection's elements in string representation");
        consoleInfo.put("add {element}", " Add a new element to collection");
        consoleInfo.put("update_id {element}", " Update current element's value, which ID is equal to the given");
        consoleInfo.put("remove_by_id {id}", " Delete the element from the collection using its ID");
        consoleInfo.put("clear", " Purify the collection!");
        consoleInfo.put("save", " Save collection to the file");
        consoleInfo.put("execute_script {file_name}", " Read and execute a script from specified file");
        consoleInfo.put("exit", " End the program (without saving)");
        consoleInfo.put("remove_first", " Delete a first element from the collection");
        consoleInfo.put("add_if_min {element}", " If an element's value is less than the smallest element value, add a new element to the collection");
        consoleInfo.put("remove_greater {element}", "Delete all collection's elements which are bigger than current element");
        consoleInfo.put("count_by_full_name {fullName}", " Print the amount of elements which fullName field is equal to given");
        consoleInfo.put("filter_greater_than_annual_turnover {annualTurnover}", " Print elements which value annualTurnover field is bigger than given");
        consoleInfo.put("print_unique_official_address", " Print unique values of officialAddress fields from all collection's elements");
    }
        /** help, show available commands"
         * @return result
         * */
        public String help () {
            String result = "";
            for (Map.Entry<String, String> entry : consoleInfo.entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
                result += (entry.getKey() + entry.getValue() +"\n") ;
            }
            return result;
        }


        /**info, print collection's info to standard output
         * @return*/
        public String info () {
            System.out.println("Type of collection: java.util.LinkedList");
            System.out.println("Initialization date: " +dateInitial);
            System.out.println("Amount of elements in the collection: " + organizations.size());
            return ("Type of collection: java.util.LinkedList" + "\n"+
                    "Initialization date: " +dateInitial + "\n"+
                    "Amount of elements in the collection: " + organizations.size());
        }


    /**method that gets ID of collection's element
     * @return Integer id
     */
    public int makerID() {
        int maxID = 0;
        for (Organization org : organizations) {
            if (org.getId() > maxID) {
                maxID = org.getId();
            }
        }
        return maxID + 1;
    }

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
                        return PRIVATE_LIMITED_COMPANY;
                    case 3:
                        return OPEN_JOINT_STOCK_COMPANY;
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
    public static String makerTownName() {
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
    public  Location makerLocation() {
        return new Location(makertownX(), makertownY(), makerTownName());
    }

    /** method that makes official organization's address from methods makerAddressStreet and makerLocation */
    public  Address makerAddress() {
        return new Address(makerAddressStreet(), makerLocation());
    }


    /** add {element}, adding a new element to collection using all maker-methods */
        public void add (Organization org) {
            int id = makerID();
            org.setId(id);
            org.setCreationDate(getDate());
            organizations.add(org);
        }

        /** update id {element}, method that updates element by it's ID */
        public String update_id (Organization org) {
            String result = "";
            //boolean check = true;
            Organization org1 = organizations.stream()
                    .filter(p -> (p.getId().equals(org.getId())))
                    .findAny()
                    .orElse(null);
            if (org1 != null) {
                organizations.remove(org1);
                org.setCreationDate(getDate());
                organizations.add(org);
                organizations.sort(Comparator.comparingInt(Organization::getId));
                System.out.println("Element was updated!");
                result = "Element was updated!";
                return result;
            }
            else{
                System.out.println("Element with this ID is not found. Try again!");
                result = "Element with this ID is not found. Try again!";
            }
            return result;
        }


        /** remove_by_id, method that removes element by it's id */
        public String remove_by_id (Organization org) {
            String result = "";
            try {
                Organization org1 = organizations.stream()
                        .filter(p -> (p.getId().equals(org.getId())))
                        .findAny()
                        .orElse(null);
                if (org1 != null) {
                    organizations.remove(org1);
                    organizations.sort(Comparator.comparingInt(Organization::getId));
                    System.out.println("Element was removed!");
                    result = "Element was removed successfully!";
                }else {
                    System.out.println("Element with this ID is not found. Try again!");
                    result = "Element with this ID is not found. Try again!";

                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("An argument must be a number! Try again!");
            }
            return result;
        }


        /** clear, method that removes all elements from collection */
        public String clear () {
            organizations.clear();
            String result = "All elements from collection were removed!";
            return result;
        }

        /** save, method that saves collection to CSV file */
        public void save () {
            try (
                Writer writer = Files.newBufferedWriter(Paths.get(String.valueOf(collectionCsv)));

                CSVWriter csvWriter = new CSVWriter(
                        writer, CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)
                ) {
                String[] header = {"id", "name", "annualTurnover", "creationDate", "fullName",
                        "organizationType", "officialAddress", "coordinates"};
                csvWriter.writeNext(header);
                System.out.println(collectionCsv);
                for (Organization organization : organizations) {
                    csvWriter.writeNext(new String[]{String.valueOf(organization.getId()), organization.getName(),
                            organization.getAnnualTurnover().toString(), organization.getCreationDate(), organization.getFullName(),
                            organization.getType().toString(), organization.getOfficialAddress().toString(), organization.getCoordinates().toString()});
                }
                System.out.println(collectionCsv.getAbsolutePath());
                System.out.println("Organizations in save method = " + organizations.size());
                System.out.println("Collection was saved successfully!");
            }catch (IOException e) {
                System.out.println("Collection wasn't saved! Try again!");
            }
            }

    /** add_if_min {element}, Method that adds a new element to the collection
     *if it's annual turnover is less than the smallest collection's turnover
     */
        public String add_if_min (Organization org) {
            String result = "";
            long minAnnualTurnover = Long.MAX_VALUE;
            for (Organization organization : organizations) {
                minAnnualTurnover = organization.getAnnualTurnover();
            }
            if (org.getAnnualTurnover() < minAnnualTurnover) {
                result = "The minimal element was found! Enter element's values.";
                System.out.println("The minimal element was found! Enter element's values." + minAnnualTurnover);
            } else {
                System.out.println("The element's annual turnover is bigger than the collection's minimal element " +
                        "element was not added. Try another value!");
                result = "The element's annual turnover is bigger than the collection's minimal element." + "\n" +
                        "Element was not added. Try another value!";
            }
            return result;
        }

        /** remove_greater {element}, method that remove collection's elements
        if it's annual turnover is more than entered value
         */
        public String remove_greater (Organization org){
            int count = 0;
            String result = "";
            try {
                List<Organization> org1 = organizations
                        .stream()
                        .filter(p -> (p.getAnnualTurnover() > (org.getAnnualTurnover())))
                        .collect(Collectors.toList());
                if (org1.size() > 0) {
                    count = org1.size();
                    for (Organization organization : org1) {
                        organizations.remove(organization);
                    }
                        organizations.sort(Comparator.comparingInt(Organization::getId));
                        System.out.println(count + " elements with annual turnover greater than " + org.getAnnualTurnover() + " were removed successfully!");
                        result = count + " elements with annual turnover greater than" + org.getAnnualTurnover() + " were removed successfully!";

                } else {
                    System.out.println("Elements with annual turnover greater than entered are not found. Try again!");
                    result = "Elements with annual turnover greater than entered are not found. Try again!";

                }
            }catch (NumberFormatException numberFormatException){
                System.out.println("An argument must be a number! Try again!");
            }
            return result;
        }

        /** remove_first, method that removes first element of collection */
        public String remove_first () {
            String result = "";
            if (organizations.size() > 0) {
                organizations.remove();
                System.out.println("The first element was removed!");
                result = "The first element was removed!";
            }else {
                System.out.println("The collection is empty!");
                result = "The collection is empty!";
            }
            return result;
        }

        /** count_by_full_name, method that prints number of elements which full name is equal to entered */
        public String count_by_full_name (Organization org){
            String result = "";
            int count = 0;
            for (Organization organization : organizations) {
                if (organization.getFullName().equals(org.getFullName())) {
                    count += 1;
                }
            }
            result = count + " elements equal to entered value!";
            System.out.println(count + " elements equal to entered value!");
            return result;
        }

        /** filter_greater_than_annual_turnover,
         * method that prints elements which are greater than entered value
         */
        public String filter_greater_than_annual_turnover (Organization org) {
            int count = 0;
            String result = "";
            try {
                List<Organization> org1 = organizations
                        .stream()
                        .filter(p -> (p.getAnnualTurnover() > (org.getAnnualTurnover())))
                        .collect(Collectors.toList());
                if (org1.size() > 0) {
                    count = org1.size();
                    for (Organization organization : org1) {
                        result += "\n" +organization;
                    }
                    System.out.println(result);
                    organizations.sort(Comparator.comparingInt(Organization::getId));
                    result = count + " elements with annual turnover greater than " + org.getAnnualTurnover() + " were printed successfully!" +result;
                    System.out.println(count + " elements with annual turnover greater than " + org.getAnnualTurnover() + " were printed successfully! " + result);
                } else {
                    System.out.println("Elements with annual turnover greater than entered are not found. Try again!");
                    result = "Elements with annual turnover greater than entered are not found. Try again!";

                }
            }catch (NumberFormatException numberFormatException){
                System.out.println("An argument must be a number! Try again!");
            }
            return result;
        }

        /** print_unique_official_address,
         *  method that prints all collection's unique officialAddress values
         */
        public String print_unique_official_address() {
            int count = 0;
            String result = "";
            List <Organization> orgss = organizations.stream().filter(p -> organizations.stream().filter
                    (p1 -> p1.getOfficialAddress().getStreet().equals(p.getOfficialAddress().getStreet())).count() == 1)
                    .collect(Collectors.toList());
            //List<String> addresses = new LinkedList<>();
            for (Organization org : orgss) {
                //count++;
                result += "\n" + org.getOfficialAddress();
            }
            /*
            Collections.sort(addresses);
            String temp = "";
            List<String> addressesUnique = new LinkedList<>();
            for (String address: addresses){
                if(addressesUnique.contains(address)){
                    addressesUnique.removeAll(Collections.singleton(address));
                    addresses.removeAll(Collections.singleton(address));
                }

                if(temp == ""){
                    temp = address;
                }
                else {
                    if(!temp.equals(address) && !addressesNO.contains(address)){
                        count++;
                        result += "\n" + address;
                        //System.out.println(result += "\n" + address);
                        temp = "";
                    }
                    else {
                        temp = "";
                        addressesNO.add(address);
                    }
                }
            }

             */
            count = orgss.size();
            if (count > 0) {
                result = count + " unique elements were found: " + result;

            }else {
                result = "No unique elements were found!";
                System.out.println("No unique elements were found!");
            }
            return result;
        }


        /** exit, method that finishes the program */
        public void exit () {
            System.out.println("Thank you for using my program! The program will be finished now!");
            System.exit(0);
        }

        /** execute_script file_name, method that read and execute script from needed file */
        public String execute_script (String filepath){
            String result = "";
            try{
                filepath = filepath.trim().replaceAll("[ ]{2,}", " ");
                System.out.println("Recursion warning! To avoid it your file can't contain execute_script command!");
                result = "Starting script file! Recursion warning! To avoid it your file can't contain execute_script command!" + "\n";
                BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
                StringBuilder cmd = new StringBuilder();
                String[] commandUser;
                String command;
                while ((command = reader.readLine()) != null){
                    commandUser = command.trim().toLowerCase().split(" ", 3);
                    //Sender sender = new Sender();
                    //sender.getCommand() = commandUser[1];
                    switch (commandUser[0]){
                        case "":
                            break;
                        case "help":
                           result += help();
                            break;
                        case "info":
                            result += info();
                            break;
                        case "show":
                            result += show();
                            break;
                        case "add":
                            Organization org = new Organization();
                            Address address = new Address();
                            Location location = new Location();
                            OrganizationType organizationType = null;
                            Coordinates coordinates = new Coordinates();
                            for( int i = 0; i<10; i++) {
                                String line = reader.readLine();
                                String val = line.split(":")[1].trim();
                                switch (line.split(":")[0].trim()) {
                                    case "name":
                                        org.setName(val);
                                        break;
                                    case "turnover":
                                        org.setAnnualTurnover(Long.parseLong(val));
                                        break;
                                    case "fullname":
                                        org.setFullName(val);
                                        break;
                                    case "type":
                                        int v1 = Integer.parseInt(val);
                                        switch (v1) {
                                            case 1:
                                                organizationType = TRUST;
                                                break;
                                            case 2:
                                                organizationType = PRIVATE_LIMITED_COMPANY;
                                                break;
                                            case 3:
                                                organizationType = OPEN_JOINT_STOCK_COMPANY;
                                                break;
                                        }
                                            org.setType(organizationType);
                                            break;

                                            case "street address":
                                                address.setStreet(val);
                                                break;

                                            case "town's X coordinate":
                                                location.setX(Integer.parseInt(val));
                                                break;

                                                case "town's Y coordinate":
                                                    location.setY(Long.parseLong(val));
                                                    break;

                                    case "town":
                                        location.setName(val);
                                        break;

                                    case "X coordinate":
                                        coordinates.setX(Double.parseDouble(val));
                                        break;
                                    case "Y coordinate":
                                        coordinates.setY(Float.parseFloat(val));
                                        break;
                                }
                            }
                            org.setId(makerID());
                            org.setCreationDate(getDate());
                            org.setCoordinates(coordinates);
                            address.setTown(location);
                            org.setOfficialAddress(address);
                            add(org);
                            result += "Organization was added successfully!";
                            break;
                        case "update_id":
                            Organization org1 = new Organization();
                            Address address1 = new Address();
                            Location location1 = new Location();
                            OrganizationType organizationType1 = null;
                            Coordinates coordinates1 = new Coordinates();
                            for( int i = 0; i<10; i++) {
                                String line = reader.readLine();
                                String val = line.split(":")[1].trim();
                                switch (line.split(":")[0].trim()) {
                                    case "name":
                                        org1.setName(val);
                                        break;
                                    case "turnover":
                                        org1.setAnnualTurnover(Long.parseLong(val));
                                        break;
                                    case "fullname":
                                        org1.setFullName(val);
                                        break;
                                    case "type":
                                        int v1 = Integer.parseInt(val);
                                        switch (v1) {
                                            case 1:
                                                organizationType1 = TRUST;
                                                break;
                                            case 2:
                                                organizationType1 = PRIVATE_LIMITED_COMPANY;
                                                break;
                                            case 3:
                                                organizationType1 = OPEN_JOINT_STOCK_COMPANY;
                                                break;
                                        }
                                        org1.setType(organizationType1);
                                        break;

                                    case "street address":
                                        address1.setStreet(val);
                                        break;

                                    case "town's X coordinate":
                                        location1.setX(Integer.parseInt(val));
                                        break;

                                    case "town's Y coordinate":
                                        location1.setY(Long.parseLong(val));
                                        break;

                                    case "town":
                                        location1.setName(val);
                                        break;

                                    case "X coordinate":
                                        coordinates1.setX(Double.parseDouble(val));
                                        break;
                                    case "Y coordinate":
                                        coordinates1.setY(Float.parseFloat(val));
                                        break;
                                }
                            }
                            String line = reader.readLine();
                            String val1 = line.split(",")[3].trim();
                            org1.setId(Integer.valueOf(val1));
                            org1.setCreationDate(getDate());
                            org1.setCoordinates(coordinates1);
                            address1.setTown(location1);
                            org1.setOfficialAddress(address1);
                            add(org1);
                            result += " \n" + "Organization was updated successfully!" + "\n";
                                update_id(org1);
                            break;
                        case "remove_by_id":
                            Organization org2 = new Organization();
                            String line2 = reader.readLine();
                            String val2 = line2.split(",")[3].trim();
                            org2.setId(Integer.valueOf(val2));
                            remove_by_id(org2);
                            result += " \n" + "Organization was removed successfully!" + "\n";
                            break;
                        case "clear":
                            result += clear();
                            break;
                        case "exit":
                            exit();
                            break;
                        case "remove_first":
                            remove_first();
                            break;
                        case "add_if_min":
                            Organization org3 = new Organization();
                            String line3 = reader.readLine();
                            String val3 = line3.split(",")[3].trim();
                            org3.setAnnualTurnover(Long.parseLong(val3));
                            add_if_min(org3);
                            result += " \n" + "Organization was added successfully!" + "\n";
                            break;
                        case "remove_greater":
                            Organization org4 = new Organization();
                            String line4 = reader.readLine();
                            String val4 = line4.split(",")[3].trim();
                            org4.setAnnualTurnover(Long.parseLong(val4));
                            remove_greater(org4);
                            result += " \n" + "Organization was removed successfully!" + "\n";
                            break;
                        case "execute_script":
                            System.out.println("Using execute_script is prohibited!");
                            result +="\n" + "Using execute_script is prohibited!" + "\n";
                            break;
                        case "count_by_full_name":
                            Organization org5 = new Organization();
                            String line5 = reader.readLine();
                            String val5 = line5.split(",")[3].trim();
                            org5.setAnnualTurnover(Long.parseLong(val5));
                            count_by_full_name(org5);
                            result += " \n" + "Elements were counted successfully!" + "\n";
                            break;
                        case  "filter_greater_than_annual_turnover":
                            Organization org6 = new Organization();
                            String line6 = reader.readLine();
                            String val6 = line6.split(",")[3].trim();
                            org6.setAnnualTurnover(Long.parseLong(val6));
                            filter_greater_than_annual_turnover(org6);
                            result += " \n" + "Elements were filtered successfully!" + "\n";                              break;
                        case "print_unique_official_address":
                            print_unique_official_address();
                            break;
                        default:
                            reader.readLine();
                            break;
                    }
                    System.out.println("The end of the command");
                }
                System.out.println("The end of the commands");
                reader.close();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("File not found. Try again.");
            } catch (IOException ioException) {
                System.out.println("File reading exception. Try again.");
            }
            return result;
            }

    /**
     * @return modificationDate
     */
    static {
        try(FileInputStream ins = new FileInputStream("C:\\Users\\spide\\OneDrive\\Desktop\\STUDIES\\PROGRAMMING\\Lab6\\log.config")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(ConsoleManager.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }


        /** method that prints current date in string representation
         * @return modificationDate
         */
        public String getDate(){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return formatter.format(calendar.getTime());
        }


}

