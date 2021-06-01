import com.opencsv.CSVWriter;import data.*;import java.io.*;import java.nio.file.Files;import java.nio.file.Paths;import java.text.SimpleDateFormat;import java.util.*;import java.util.stream.Collectors;/** * @author Sabitov Danil * @version 1.0 * Class with user`s commands */public class ConsoleManager {    /** Field for saving collection into csv file */    File collectionCsv = CollectionChecker.collectionCsv;    /** Field for saving date of initialization of the collection */    Date dateInitial = CollectionChecker.dateInitial;    /** LinkedList collection for keeping a collection as java-object */    LinkedList<Organization> organizations = CollectionChecker.organizations;    /** HashMap collection for making an instruction */    private final HashMap<String, String> consoleInfo;    /**show, print all collection's elements in string representation     * @return null     * */    public String show(){        String result = "";        if (organizations.size() != 0) {            organizations.sort(Comparator.comparingInt(Organization::getId));            for (Organization org : organizations) {                System.out.println(org.toString() + "\n");                result += org.toString() + "\n";            }        } else {            System.out.println("Collection is empty!");            result = "Collection is empty!";        }        return result;    }    {        //creating an instruction        consoleInfo = new HashMap<>();        consoleInfo.put("help", " Show available commands");        consoleInfo.put("info", " Print collection's info to standard output");        consoleInfo.put("show", " Print all collection's elements in string representation");        consoleInfo.put("add {element}", " Add a new element to collection");        consoleInfo.put("update_id {element}", " Update current element's value, which ID is equal to the given");        consoleInfo.put("remove_by_id {id}", " Delete the element from the collection using its ID");        consoleInfo.put("clear", " Purify the collection!");        consoleInfo.put("save", " Save collection to the file");        consoleInfo.put("execute_script {file_name}", " Read and execute a script from specified file");        consoleInfo.put("exit", " End the program (without saving)");        consoleInfo.put("remove_first", " Delete a first element from the collection");        consoleInfo.put("add_if_min {element}", " If an element's value is less than the smallest element value, add a new element to the collection");        consoleInfo.put("remove_greater {element}", "Delete all collection's elements which are bigger than current element");        consoleInfo.put("count_by_full_name {fullName}", " Print the amount of elements which fullName field is equal to given");        consoleInfo.put("filter_greater_than_annual_turnover {annualTurnover}", " Print elements which value annualTurnover field is bigger than given");        consoleInfo.put("print_unique_official_address", " Print unique values of officialAddress fields from all collection's elements");    }        /** help, show available commands"         * @return result         * */        public String help () {            String result = "";            for (Map.Entry<String, String> entry : consoleInfo.entrySet()) {                System.out.println(entry.getKey() + entry.getValue());                result += (entry.getKey() + entry.getValue() +"\n") ;            }            return result;        }        /**info, print collection's info to standard output         * @return*/        public String info () {            System.out.println("Type of collection: java.util.LinkedList");            System.out.println("Initialization date: " +dateInitial);            System.out.println("Amount of elements in the collection: " + organizations.size());            return ("Type of collection: java.util.LinkedList" + "\n"+                    "Initialization date: " +dateInitial + "\n"+                    "Amount of elements in the collection: " + organizations.size());        }    /**method that gets ID of collection's element     * @return Integer id     */    public int makerID() {        int maxID = 0;        for (Organization org : organizations) {            if (org.getId() > maxID) {                maxID = org.getId();            }        }        return maxID + 1;    }    /**method that gets name of collection's element     * @return String name     */    public String makerName() {        while (true) {            try {                Scanner scanner = new Scanner(System.in);                System.out.println("Enter organization's name: ");                String name = scanner.nextLine().trim(); // trim receive name without spaces                if (name.isEmpty()) {                    System.out.println("Name cannot be empty or null");                    continue;                }                return name;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Name value must be string!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Programm was stopped!");                System.exit(1);            }        }    }    /**method that gets X-coordinate of organization     * @return Double x     */    public double makerX() {        while (true) {            try {                System.out.println("Enter X coordinate. Value cannot be empty.");                Scanner scanner = new Scanner(System.in);                double x = scanner.nextDouble();                String iX = Double.toString(x);                if (iX.isEmpty()) {                    System.out.println("Coordinate cannot be empty or null");                    continue;                }                return x;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Coordinate value must be double number!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Programm was stopped!");                System.exit(1);            }        }    }    /**method that gets Y-coordinate of organization     * @return Float y     */    public float makerY() {        while (true) {            try {                System.out.println("Enter Y coordinate. Value cannot be empty.");                Scanner scanner = new Scanner(System.in);                float y = scanner.nextFloat();                String iY = Float.toString(y);                if (iY.isEmpty()) {                    System.out.println(" Coordinate cannot be empty or null");                    continue;                }                return y;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Coordinate value must be float number!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /**method that makes coordinates from method makerX and makerY*/    public Coordinates makerCoordinates() {        return new Coordinates(makerX(), makerY());    }    /**method that gets organization's annual turnover     * @return Long annualTurnover     */    public long makerAnnualTurnover() {        while (true) {            try {                System.out.println("Enter organization's annual turnover. Value must be greater than 0.");                Scanner scanner = new Scanner(System.in);                long turnover = scanner.nextLong();                String ITurnover = String.valueOf(turnover).trim();                if (turnover <= 0) {                    System.out.println("Annual turnover must be greater than 0. Try again!");                    continue;                }                return turnover;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Annual turnover value must be long number!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets organization's full name     * @return String fullName     */    public String makerFullName() {        while (true) {            try {                Scanner scanner = new Scanner(System.in);                System.out.println("Enter organization's FULL name: ");                String fullname = scanner.nextLine().trim();                return fullname;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Name value must be string!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets organization's type     * @return OrganizationType type     */    public OrganizationType makerOrganizationType() {        while (true) {            try {                System.out.println("Choose type of organization. Enter the number which respond for desired type.");                System.out.println("Types: 'TRUST' - 1, 'PRIVATE_LIMITED_COMPANY' - 2, 'OPEN_JOINT_STOCK_COMPANY' - 3");                Scanner scanner = new Scanner(System.in);                int type = scanner.nextInt();                switch (type) {                    case 1:                        return OrganizationType.TRUST;                    case 2:                        return OrganizationType.PRIVATE_LIMITED_COMPANY;                    case 3:                        return OrganizationType.OPEN_JOINT_STOCK_COMPANY;                    default:                        break;                }            } catch (InputMismatchException inputMismatchException) {                System.out.println("Organziation type must be a number (1, 2, 3). Try again!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets address of organization(street)     * @return String street     * */    public String makerAddressStreet() {        while (true) {            try {                Scanner scanner = new Scanner(System.in);                System.out.println("Enter organization's street address: ");                String street = scanner.nextLine().trim();                if (street.length() > 148) {                    System.out.println("Street value cannot be greater than 148. Try again!");                    continue;                }                if (street.isEmpty()) {                    System.out.println("Name cannot be empty or null");                    continue;                }                return street;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Street value must be string!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets organization's town X-coordinate     * @return x     */    public int makertownX() {        while (true) {            try {                System.out.println("Enter town's X coordinate. Value cannot be empty.");                Scanner scanner = new Scanner(System.in);                int x = scanner.nextInt();                String iX = Integer.toString(x);                if (iX.isEmpty()) {                    System.out.println("Coordinate cannot be empty or null");                    continue;                }                return x;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Coordinate value must be integer number!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets organization's town Y-coordinate     * @return y     */    public long makertownY() {        while (true) {            try {                System.out.println("Enter town's Y coordinate. Value cannot be empty.");                Scanner scanner = new Scanner(System.in);                long y = scanner.nextLong();                String iY = Long.toString(y);                if (iY.isEmpty()) {                    System.out.println("Coordinate cannot be empty or null");                    continue;                }                return y;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Coordinate value must be long number!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that gets address of organization(name of town)     * @return town     */    public static String makerTownName() {        while (true) {            try {                Scanner scanner = new Scanner(System.in);                System.out.println("Enter organization's town: ");                String town = scanner.nextLine().trim();                if (town.isEmpty()) {                    System.out.println("Town value cannot be empty or null");                    continue;                }                return town;            } catch (InputMismatchException inputMismatchException) {                System.out.println("Town value must be string!");            } catch (NoSuchElementException noSuchElementException) {                System.out.println("Program was stopped!");                System.exit(1);            }        }    }    /** method that makes organization's town name and coordinates     *from methods makertownX, makertownY,makerTownName     */    public  Location makerLocation() {        return new Location(makertownX(), makertownY(), makerTownName());    }    /** method that makes official organization's address from methods makerAddressStreet and makerLocation */    public  Address makerAddress() {        return new Address(makerAddressStreet(), makerLocation());    }    /** add {element}, adding a new element to collection using all maker-methods */        public void add (Organization org) {            int id = makerID();            org.setId(id);            org.setCreationDate(getDate());            organizations.add(org);        }        /** update id {element}, method that updates element by it's ID */        public String update_id (Organization org) {            String result = "";            // org = id.trim().replaceAll("[ ]{2,}", " ");            //ListIterator<Organization> iterator = organizations.listIterator();            boolean check = true;            Organization org1 = organizations.stream()                    .filter(p -> (p.getId().equals(org.getId())))                    .findAny()                    .orElse(null);            if (org1 != null) {                organizations.remove(org1);                organizations.add(org);                //organizations.stream().s(p -> p.getId());                organizations.sort(Comparator.comparingInt(Organization::getId));                System.out.println("Element was updated!");                result = "Element was updated!";                return result;            }               /* while (iterator.hasNext()) {                    Organization s = iterator.next();                    int intID = s.getId();                    String stringID = String.valueOf(intID);                    if (stringID.equals(org.getId())) {                        check = true;                        iterator.remove();                        Organization organizationUpdated = new Organization(intID, makerName(), makerAnnualTurnover(), getDate(),                                makerFullName(), makerOrganizationType(), makerAddress(), makerCoordinates());                        iterator.add(organizationUpdated);                        System.out.println("Element was updated!");                    }                }                */            if (!check) {                System.out.println("Element with this ID is not found. Try again!");                result = "Element with this ID is not found. Try again!";            }            return result;        }        /** remove_by_id, method that removes element by it's id */        public String remove_by_id (Organization org) {            String result = "";            try {                Organization org1 = organizations.stream()                        .filter(p -> (p.getId().equals(org.getId())))                        .findAny()                        .orElse(null);                if (org1 != null) {                    organizations.remove(org1);                    organizations.sort(Comparator.comparingInt(Organization::getId));                    System.out.println("Element was removed!");                    result = "Element was removed successfully!";                }else {                    System.out.println("Element with this ID is not found. Try again!");                    result = "Element with this ID is not found. Try again!";                }            } catch (NumberFormatException numberFormatException) {                System.out.println("An argument must be a number! Try again!");            }            return result;        }        /** clear, method that removes all elements from collection */        public String clear () {            organizations.clear();            String result = "All elements from collection were removed!";            return result;        }        /** save, method that saves collection to CSV file */        public void save () {            try (                Writer writer = Files.newBufferedWriter(Paths.get(String.valueOf(collectionCsv)));                CSVWriter csvWriter = new CSVWriter(                        writer, CSVWriter.DEFAULT_SEPARATOR,                        CSVWriter.NO_QUOTE_CHARACTER,                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,                        CSVWriter.DEFAULT_LINE_END)                ) {                String[] header = {"id", "name", "annualTurnover", "creationDate", "fullName",                        "organizationType", "officialAddress", "coordinates"};                csvWriter.writeNext(header);                System.out.println(collectionCsv);                for (Organization organization : organizations) {                    csvWriter.writeNext(new String[]{String.valueOf(organization.getId()), organization.getName(),                            organization.getAnnualTurnover().toString(), organization.getCreationDate(), organization.getFullName(),                            organization.getType().toString(), organization.getOfficialAddress().toString(), organization.getCoordinates().toString()});                }                System.out.println(collectionCsv.getAbsolutePath());                System.out.println("Organizations in save method = " + organizations.size());                System.out.println("Collection was saved successfully!");            }catch (IOException e) {                System.out.println("Collection wasn't saved! Try again!");            }            }    /** add_if_min {element}, Method that adds a new element to the collection     *if it's annual turnover is less than the smallest collection's turnover     */        public void add_if_min (Organization org){            long minAnnualTurnover = Long.MAX_VALUE;            for (Organization organization : organizations) {                minAnnualTurnover = organization.getAnnualTurnover();            }            if (org.getAnnualTurnover() < minAnnualTurnover) {                organizations.add(org);                System.out.println("The minimal element was added.");            } else {                System.out.println("The element's annual turnover is bigger than the collection's minimal element " +                        "element was not added. Try another value!");            }        }        /** remove_greater {element}, method that remove collection's elements        if it's annual turnover is more than entered value         */        public String remove_greater (Organization org){            int count = 0;            String result = "";            try {                List<Organization> org1 = organizations                        .stream()                        .filter(p -> (p.getAnnualTurnover() > (org.getAnnualTurnover())))                        .collect(Collectors.toList());                if (org1.size() > 0) {                    count = org1.size();                    for (Organization organization : org1) {                        organizations.remove(organization);                    }                        organizations.sort(Comparator.comparingInt(Organization::getId));                        System.out.println(count + " elements with annual turnover greater than " + org.getAnnualTurnover() + " were removed successfully!");                        result = count + " elements with annual turnover greater than" + org.getAnnualTurnover() + " were removed successfully!";                } else {                    System.out.println("Elements with annual turnover greater than entered are not found. Try again!");                    result = "Elements with annual turnover greater than entered are not found. Try again!";                }            }catch (NumberFormatException numberFormatException){                System.out.println("An argument must be a number! Try again!");            }            return result;        }        /** remove_first, method that removes first element of collection */        public String remove_first () {            String result = "";            if (organizations.size() > 0) {                organizations.remove();                System.out.println("The first element was removed!");                result = "The first element was removed!";            }else {                System.out.println("The collection is empty!");                result = "The collection is empty!";            }            return result;        }        /** count_by_full_name, method that prints number of elements which full name is equal to entered */        public void count_by_full_name (String fullName){            int count = 0;            for (Organization organization : organizations) {                if (organization.getFullName().equals(fullName)) {                    count += 1;                }            }            System.out.println(count + " elements equal to entered value!");        }        /** filter_greater_than_annual_turnover,         * method that prints elements which are greater than entered value         */        public void filter_greater_than_annual_turnover ( long annualTurnover){            int count = 0;            System.out.println("Elements which annual turnover is greater than entered value: ");            for (Organization organization : organizations) {                if (organization.getAnnualTurnover() > annualTurnover) {                    System.out.println(organization);                    count += 1;                }            }            System.out.println(count + " elements were printed!");        }        /** print_unique_official_address, method that prints all collection's unique officialAddress values */        public String print_unique_official_address () {            int count = 0;            String result = "";            String[] strings ;            for (Organization org : organizations) {                List<Organization> org1 = organizations                        .stream()                        .filter(p -> (p.getOfficialAddress() == org.getOfficialAddress()))                        .collect(Collectors.toList());                if(org1.size() == 1){                    result += "\n" + org.getOfficialAddress();                    System.out.println(result);                    count++;                }            }            if (count > 0) {                result = count + " unique elements were found!" + result;            }else {                result = "No unique elements were found!";                System.out.println("No unique elements were found!");            }            return result;        }        /** exit, method that finishes the program */        public void exit () {            System.out.println("Thank you for using my program! The program will be finished now!");            System.exit(0);        }        public void executer(String savePath, Sender sender){            switch (sender.getCommand()){                case "add":                    break;            }        }        /** execute_script file_name, method that read and execute script from needed file */        public void execute_script (String filepath){            try{                filepath = filepath.trim().replaceAll("[ ]{2,}", " ");                System.out.println("Recursion warning! To avoid it your file can't contain execute_script command!");                BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));                String[] commandUser;                String command;                while ((command = reader.readLine()) != null){                    commandUser = command.trim().toLowerCase().split(" ", 2);                    switch (commandUser[0]){                        case "":                            break;                        case "help":                           help();                            break;                        case "info":                            info();                            break;                        case "show":                            show();                            break;                        case "add":                         //   add();                            break;                        case "update id":                         //   update_id(commandUser[1]);                            break;                        case "remove_by_id":                          //  remove_by_id();                            break;                        case "clear":                            clear();                            break;                        case "save":                            save();                            break;                        case "exit":                            exit();                            break;                        case "remove_first":                            remove_first();                            break;                        case "add_if_min":                            System.out.println("Enter an element, which will be compared with other elements in collection.");                            add_if_min(new Organization(makerID(), makerName(),                                    makerAnnualTurnover(),getDate(),                                    makerFullName(), makerOrganizationType(),                                    makerAddress(), makerCoordinates()));                            break;                        case "remove_greater":                            System.out.println("Enter an element, which will be compared with other elements in collection.");                            //remove_greater(makerAnnualTurnover());                            break;                        case "execute_script":                            System.out.println("Using execute_script is prohibited!");                            break;                        case "count_by_full_name":                            System.out.println("Enter organization's full name, which will be compared with element`s full name.");                            count_by_full_name(makerFullName());                            break;                        case  "filter_greater_than_annual_turnover":                            System.out.println("Enter organization's annual turnover, which will be compared with element's annual turnover");                            filter_greater_than_annual_turnover(makerAnnualTurnover());                            break;                        case "print_unique_official_address":                            print_unique_official_address();                            break;                        default:                            reader.readLine();                            break;                    }                    System.out.println("The end of the command");                }                System.out.println("The end of the commands");                reader.close();            } catch (FileNotFoundException fileNotFoundException) {                System.out.println("File not found. Try again.");            } catch (IOException ioException) {                System.out.println("File reading exception. Try again.");            }            }        /** method that prints current date in string representation         * @return modificationDate*/        public String getDate(){            Calendar calendar = Calendar.getInstance();            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");            return formatter.format(calendar.getTime());        }    }