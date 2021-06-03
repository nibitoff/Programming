import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for checking file's collection
 */
public class CollectionChecker {

    /**
     * Field for saving collection into csv file
     */
    public static File collectionCsv;
    /**
     * Field for saving date of initialization of the collection
     */
    public static Date dateInitial;

    /**
     * LinkedList collection for keeping a collection as java-object
     */
    public static LinkedList<Organization> organizations = new LinkedList<>();

    /**
     * HashMap collection for making an instruction
     */
    public CollectionChecker(String filePath) throws IOException {
        checkFile(filePath);
        Integer fileId = null;
        String fileName = null;
        Long fileAnnnualTurnover = null;
        String fileCreationDate = null;
        String fileFullName = null;
        OrganizationType fileType = null;
        Location fileTown;
        Address fileAddress = null;
        Coordinates fileCoordinates = null;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            String[] nextLine;
            int goodElements = 0;
            int badElements = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                boolean ok = true;
                try {
                    fileId = Integer.valueOf(nextLine[0]);
                } catch (NumberFormatException e) {
                    ok = false;
                }
                try {
                    fileName = nextLine[1];
                } catch (Exception e) {
                    ok = false;
                }
                try {
                    fileAnnnualTurnover = Long.valueOf(nextLine[2]);
                } catch (NumberFormatException e) {
                    ok = false;
                }
                try {
                    fileCreationDate = nextLine[3];
                    if (DataChecker.isValidDate(fileCreationDate)) {
                        fileCreationDate = nextLine[3];
                    }
                } catch (Exception e) {
                    ok = false;
                }
                try {
                    fileFullName = nextLine[4];
                } catch (Exception e) {
                    ok = false;
                }
                try {
                    fileType = OrganizationType.valueOf(nextLine[5]);
                } catch (IllegalArgumentException e) {
                    ok = false;
                }
                try {
                    String[] t1 = nextLine[6].split(" ");
                    fileTown = new Location(Integer.parseInt(t1[1]), Long.parseLong(t1[2]), t1[3]);
                    fileAddress = new Address(t1[0], fileTown);
                } catch (Exception e) {
                    ok = false;
                }
                try {
                    String[] t2 = nextLine[7].split(" ");
                    fileCoordinates = new Coordinates(Double.parseDouble(t2[0]), Float.parseFloat(t2[1]));
                } catch (NumberFormatException e) {
                    ok = false;
                }
                if (ok) {
                    Organization organizationFile = new Organization(fileId, fileName, fileAnnnualTurnover, fileCreationDate, fileFullName, fileType,
                            fileAddress, fileCoordinates);
                    organizations.addFirst(organizationFile);
                    goodElements++;
                } else {
                    badElements++;
                }

            }

            Server.myResult = ("Collection was loaded succesfully! " + "\n" +
                    "Number of correct elements: " + goodElements + "\n" +
                    "Number of corrupted elements: " + badElements);

            System.out.println("Collection was loaded succesfully! " + "\n" +
                    "Number of correct elements: " + goodElements);
            System.out.println("Number of corrupted elements: " + badElements);
        } catch (IOException e) {
            System.out.println("Syntax error! Try again!");
        }
        this.collectionCsv = new File(filePath);
        this.dateInitial = new Date();
    }

    /**
     * method that checks file and path to it
     */
    public void checkFile(String pathToFile) throws IOException {
        String filePath;

        Scanner newFile = new Scanner(pathToFile);
        filePath = newFile.nextLine();

        try {
            if (filePath == null) throw new FileNotFoundException();
        } catch (FileNotFoundException exception) {
            System.out.println("Error! You did not specify the path to the file!");
            System.exit(1);
        }

        File file = new File(filePath);

        try {
            if (file.isDirectory()) throw new FileNotFoundException();
        } catch (FileNotFoundException exception) {
            System.out.println("Error! You did not specify the path to the file!");
            System.exit(1);
        }

        try {
            if (file.exists()) {
                this.collectionCsv = new File(filePath);
                System.out.println("Path to file was checked successfully!");
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File is not found! Check path to file!");
            System.exit(1);
        }
        try {
            if (!collectionCsv.canRead() || !collectionCsv.canWrite()) throw new SecurityException();
        } catch (SecurityException securityException) {
            System.out.println("Error! This file is protected from writing or reading" + '\n' + "Check both permissions! ");
            System.exit(1);
        }
    }


}

