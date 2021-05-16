package addressbook;

import java.util.*;
import java.time.LocalDate;

public class AddressBookMain {
    public static final String CSV_FILE_PATH = "address-book-csv.csv";
    public static final String JSON_FILE_PATH = "address-book-json.json";
    public enum IOService {
        CONSOLE_IO, FILE_IO, CSV_FILE, JSON_FILE,DB_IO;
    }
    private List<Contacts> addressBookDataList;
    private AddressBookDB addressBookDB;

    public AddressBookMain() {
        addressBookDB = AddressBookDB.getInstance();
    }

    public AddressBookMain(List<Contacts> addressBookDataList) {
        this.addressBookDataList = addressBookDataList;
    }

    public void readAddressBookData(Scanner consoleInputReader) {
        System.out.println("Enter firstname");
        String fisrtName = consoleInputReader.next();
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
        addressBookDataList.add(new Contacts(fisrtName,lastName,address,city,state,zip,phoneNumber,email));
    }
    //uc-13
    public void writeAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Address Book Roaster to Console\n" + addressBookDataList);
        else
            new AddressBookIO().writeData(addressBookDataList);
    }
    //uc-14
    public void writeCSVAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Address Book Roaster to Console\n" + addressBookDataList);
        else
            new AddressBookCSV().writeCSVData();
    }

    //uc-13
    public long readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            this.addressBookDataList = new AddressBookIO().readData();
        return addressBookDataList.size();
    }
    //uc-14
    public long readCSVAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            new AddressBookCSV().readData(CSV_FILE_PATH);
        return AddressBookCSV.readData(CSV_FILE_PATH);
    }
    //UC-16
    public List<Contacts> readAddressBookDataDB(IOService ioService){
        if(ioService.equals(IOService.DB_IO))
            this.addressBookDataList = new AddressBookDB().readData();
        return this.addressBookDataList;
    }
    //uc-17
    public void updateAddressBook(String name, String phoneNumber) {
        int result = addressBookDB.updateAddressBookData(name,phoneNumber);
        if(result == 0)
            return;
        Contacts addressBookData = this.getAddressBookData(name);
        if(addressBookData != null)
            addressBookData.phoneNumber = phoneNumber;
    }

    private Contacts getAddressBookData(String name) {
        return this.addressBookDataList.stream()
                .filter(addressBookDataListItem -> addressBookDataListItem.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean checkAddressBookInSyncWithDB(String name) {
        List<Contacts> addressBookDataList = addressBookDB.getAddressBookData(name);
        return addressBookDataList.get(0).equals(getAddressBookData(name));
    }
    //uc-18
    public List<Contacts> readContactDataForGivenDateRange(LocalDate startDate, LocalDate endDate) {
        this.addressBookDataList = addressBookDB.getContactForGivenDateRange(startDate, endDate);
        return addressBookDataList;
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
        ArrayList<Contacts> addressBookDataList = new ArrayList<>();
        AddressBookMain addressBook = new AddressBookMain(addressBookDataList);
        Scanner consoleInputReader = new Scanner(System.in);
        addressBook.readAddressBookData(consoleInputReader);
        addressBook.writeAddressBookData(IOService.CONSOLE_IO);
    }
}
