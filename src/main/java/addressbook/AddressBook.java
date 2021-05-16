package addressbook;

import java.util.*;
import java.util.stream.Collectors;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
 * zip,mobile number.*/
public class AddressBook {

    private static List<Contacts> employeeContactsList;
    public AddressBook(List<Contacts> employeeContactsList) {
        this.employeeContactsList = employeeContactsList;
    }
    /* @Description - to write the employee contacts details */
    private static void writeEmployeeContactsData(Scanner consoleInputReader) {
        System.out.println("Enter your first name");
        String firstName = consoleInputReader.nextLine();
        System.out.println("Enter your last name");
        String lastName = consoleInputReader.nextLine();
        System.out.println("Enter your address name");
        String address = consoleInputReader.nextLine();
        System.out.println("Enter your city name");
        String city = consoleInputReader.nextLine();
        System.out.println("Enter your state name");
        String state = consoleInputReader.nextLine();
        System.out.println("Enter your zip  code ");
        int  zip = consoleInputReader.nextInt();
        System.out.println("Enter your zip  code ");
        long  mobileNumber = consoleInputReader.nextLong();
        System.out.println("Enter your email Id");
        String emailId = consoleInputReader.nextLine();

    }
    /* @Description - to read  the employee contacts details */
    private static void readEmployeeContactsData() {
        System.out.println("Write employee contacts details " + employeeContactsList);
    }
    /*Main Method*/
    public static void main(String[] args) {
        ArrayList<Contacts> employeePayrollList = new ArrayList<>();
        AddressBook addressBook = new AddressBook(employeeContactsList);
        Scanner consoleInputReader = new Scanner(System.in);
        AddressBook.writeEmployeeContactsData(consoleInputReader);
        AddressBook.readEmployeeContactsData();

    }


}
