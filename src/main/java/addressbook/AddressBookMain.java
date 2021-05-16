package addressbook;

import java.util.*;
import java.time.LocalDate;
import java.sql.Date;

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
    //uc-19
    public Map<String, Integer> readContactByCityOrState() {
        return addressBookDB.getContactsByCityOrState();
    }
    //UC-20
    public void addContactToDB(int type, String name, String firstName, String lastName, String address, String city, String state,
                               int zip, String phoneNumber, String email, Date date) {
        addressBookDataList.add(addressBookDB.addContact(type, name, firstName, lastName, address, city, state, zip, phoneNumber, email, (java.sql.Date) date));
    }
    //uc-21
    public void addDetails(List<Contacts> addressBookDataList) {
        addressBookDataList.forEach(addressBookData -> {
            System.out.println("Employee being added : " + addressBookData.name);
            this.addContactToDB(addressBookData.type,addressBookData.name,addressBookData.firstName, addressBookData.lastName, addressBookData.address, addressBookData.city,
                    addressBookData.state, addressBookData.zip, addressBookData.phoneNumber, addressBookData.email,
                    addressBookData.date);
            System.out.println("Employee added : " + addressBookData.name);
        });
        System.out.println("" + this.addressBookDataList);
    }

    public void addDetailsWithThreads(List<Contacts> addressBookDataList) {
        Map<Integer, Boolean> contactAdditionStatus = new HashMap<>();
        addressBookDataList.forEach(addressBookData -> {
            Runnable task = () -> {
                contactAdditionStatus.put(addressBookData.hashCode(), false);
                System.out.println("Employee being added : " + Thread.currentThread().getName());
                this.addContactToDB(addressBookData.type,addressBookData.name,addressBookData.firstName, addressBookData.lastName,
                        addressBookData.address, addressBookData.city, addressBookData.state, addressBookData.zip, addressBookData.phoneNumber,
                        addressBookData.email, addressBookData.date);
                contactAdditionStatus.put(addressBookData.hashCode(), true);
                System.out.println("Employee added : " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(task, addressBookData.name);
            thread.start();
        });
        while (contactAdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("" + this.addressBookDataList);
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
