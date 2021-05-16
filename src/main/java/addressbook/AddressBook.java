package addressbook;

import java.util.Scanner;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
 * zip,mobile number.*/
public class AddressBook {

    Contacts contacts;
    /* @Description- Add new contacts in address book  */
    private void addContacts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter The Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter The Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter The City: ");
        String city = scanner.nextLine();
        System.out.println("Enter The State: ");
        String state = scanner.nextLine();
        System.out.println("Enter The Zip Code: ");
        int zipCode = scanner.nextInt();
        System.out.println("Enter The Mobile Number: ");
        long number = scanner.nextLong();
        System.out.println("Enter The Email-id: ");
        String email = scanner.next();
        contacts = new Contacts(firstName, lastName, address, state, city, zipCode, number, email);
        System.out.println("Contact Successfully Added");
    }
    @Override
    public String toString(){
        return contacts.toString();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");
        AddressBook addressBook = new AddressBook();
        addressBook.addContacts();
        System.out.println(addressBook.toString());
    }
}
