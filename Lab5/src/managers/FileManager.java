package managers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class FileManager extends ConsoleManager {
    /** Field for saving collection into csv file */
    private File collectionCsv;
    /** Field for saving date of initialization of the collection */
    private Date dateInitial;

    public FileManager(String pathToFile) throws IOException {
        super(pathToFile);
    checkFile(pathToFile);
    Integer fileId = null;
    String fileName = null;
    Long fileAnnnualTurnover = null;
    String fileCreationDate = null;
    String fileFullName = null;
    OrganizationType fileType = null;
    Location fileTown = null;
    Address fileAddress = null;
    Coordinates fileCoordinates = null;
        try (
    Reader reader = Files.newBufferedReader(Paths.get(pathToFile));
    CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            try {
                fileId = Integer.valueOf(nextLine[0]);
            } catch (NumberFormatException e) {
                System.out.println("Value must be an Integer number! Check file!");
                System.exit(1);
            }
            try {
                fileName = nextLine[1];
            } catch (Exception e) {
                System.out.println("Value must be a String! Check file!");
            }
            try {
                fileAnnnualTurnover = Long.valueOf(nextLine[2]);
            } catch (NumberFormatException e) {
                System.out.println("Value must be a Long number! Check file!");
                System.exit(1);
            }
            try {
                fileCreationDate = nextLine[3];
            } catch (Exception e) {
                System.out.println("Value must be String! Check file!");
                System.exit(1);
            }
            try {
                fileFullName = nextLine[4];
            } catch (Exception e) {
                System.out.println("Value must be String! Check file!");
                System.exit(1);
            }
            try {
                fileType = OrganizationType.valueOf(nextLine[5]);
            } catch (IllegalArgumentException e) {
                System.out.println("Value must be one of enums! Check file!");
                System.exit(1);
            }
            try {
                String[] t1 = nextLine[6].split(" ");

                fileTown = new Location(Integer.parseInt(t1[1]), Long.parseLong(t1[2]), t1[3]);
                fileAddress = new Address(t1[0], fileTown);
            } catch (Exception e) {
                System.out.println("Error with reading this value! Check file!");
                System.exit(1);
            }
            try {
                String[] t2 = nextLine[7].split(" ");
                fileCoordinates = new Coordinates(Double.parseDouble(t2[0]), Float.parseFloat(t2[1]));
            } catch (NumberFormatException e) {
                System.out.println("Value must contain numbers! Check file!");
                System.exit(1);
            }

            Organization organizationFile = new Organization(fileId, fileName, fileAnnnualTurnover, fileCreationDate, fileFullName, fileType,
                    fileAddress, fileCoordinates);
            organizations.addFirst(organizationFile);

        }
    } catch (IOException | CsvValidationException e) {
        System.out.println("Syntax error! Try again!");
    }
        }



    /**method that checks file and path to it*/
    public void checkFile(String pathToFile) throws IOException {
        String filePath = null;

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
            if (!file.getName().endsWith(".csv")) throw new FileNotFoundException();
        } catch (FileNotFoundException exception) {
            System.out.println("Error! The file must be 'csv' extension!");
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
        this.collectionCsv = new File(filePath);
        this.dateInitial = new Date();
    }

}
