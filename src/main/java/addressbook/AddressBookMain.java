package addressbook;

import java.util.*;

public class AddressBookMain {
    public static final String CSV_FILE_PATH = "address-book-csv.csv";
    public static final String JSON_FILE_PATH = "address-book-json.json";
    public enum IOService {
        CONSOLE_IO, FILE_IO, CSV_FILE, JSON_FILE
    }
    private List<Contacts> addressbooklist;

    public AddressBookMain(List<Contacts> addressbooklist) {
        this.addressbooklist = addressbooklist;
    }

    public AddressBookMain() {
    }

    public void readAddressBookData(Scanner consoleInputReader) {
        System.out.println("Enter firstname");
        String firstName = consoleInputReader.next();
        System.out.println("Enter lastname");
        String lastName = consoleInputReader.next();
        System.out.println("Enter address");
        String address = consoleInputReader.next();
        System.out.println("Enter city");
        String city = consoleInputReader.next();
        System.out.println("Enter state");
        String state = consoleInputReader.next();
        System.out.println("Enter zip");
        String zip = consoleInputReader.next();
        System.out.println("Enter phone number");
        String phoneNumber = consoleInputReader.next();
        System.out.println("Enter email");
        String email = consoleInputReader.next();
        addressbooklist.add(new Contacts(firstName,lastName,address,city,state,zip,phoneNumber,email));
    }
    //uc-13
    public void writeAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Address Book Roaster to Console\n" + addressbooklist);
        else
            new AddressBookIO().writeData(addressbooklist);
    }
    //uc-14
    public void writeCSVAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Address Book Roaster to Console\n" + addressbooklist);
        else
            new AddressBookCSV().writeCSVData();
    }
    //uc-15
    public boolean writeJSONAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Address Book Roaster to Console\n" + addressbooklist);
        else {
            new AddressBookJSON();
            new AddressBookJSON().writeData(addressbooklist);
        }
        return true;
    }
    //uc-13
    public long readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            this.addressbooklist = new AddressBookIO().readData();
        return addressbooklist.size();
    }
    //uc-14
    public long readCSVAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            new AddressBookCSV().readData(CSV_FILE_PATH);
        return AddressBookCSV.readData(CSV_FILE_PATH);
    }
    //uc-15
    public void readJSONAddressBookData(IOService ioService) throws JsonMappingException {
        if (ioService.equals(IOService.CONSOLE_IO))
            new AddressBookJSON().readData(JSON_FILE_PATH);
        AddressBookJSON.readData(JSON_FILE_PATH);
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new AddressBookIO().countEntries();
        return 0;
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new AddressBookIO().printData();
    }
    public static void main(String[] args) {
        ArrayList<Contacts> addressbooklist = new ArrayList<>();
        AddressBookMain addressBook = new AddressBookMain(addressbooklist);
        Scanner consoleInputReader = new Scanner(System.in);
        addressBook.readAddressBookData(consoleInputReader);
        addressBook.writeAddressBookData(IOService.CONSOLE_IO);
    }
}
