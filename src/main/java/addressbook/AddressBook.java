package addressbook;

import java.util.*;
import java.util.stream.Collectors;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
 * zip,mobile number.*/
public class AddressBook {

    public int indexValue = 1;
    public HashMap<Integer,Contacts> contacts = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    static AddressBook addressbook = new AddressBook();

    public void selectOption() {
        int choice = 1;
        do {
            System.out.println("Enter your choice" +
                    "\n1. Add Contact" +
                    "\n2. Edit Contact" +
                    "\n3. Delete Contact" +
                    "\n4. Create New AddressBook" +
                    "\n5. Search Person By City" +
                    "\n6. Search Person By state" +
                    "\n7. Count Person By state" +
                    "\n8. Count Person By City" +
                    "\n9. Sort Person By First Name" +
                    "\n10. Sort Person By City" +
                    "\n11. Sort Person By State" +
                    "\n12. Sort Person By Zip Code" +
                    "\n0. Exit");
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    addressbook.addContacts();
                    break;
                case 2:
                    addressbook.editContact();
                    break;
                case 3:
                    addressbook.deleteContact();
                    break;
                case 4:
                    addressbook.createNewAddressBook();
                    break;
                case 5:
                    addressbook.searchPersonByCity();
                    break;
                case 6:
                    addressbook.searchPersonByState();
                    break;
                case 7:
                    addressbook.countPersonByCity();
                    break;
                case 8:
                    addressbook.countPersonByState();
                    break;
                case 9:
                    addressbook.sortPersonByFirstname();
                    break;
                case 10:
                    addressbook.sortPersonByCity();
                    break;
                case 11:
                    addressbook.sortPersonByState();
                    break;
                case 12:
                    addressbook.sortPersonByZipCode();
                    break;
                default:
                    System.out.println("You press exit.\nThank You!");
                    choice = 0;
                    break;
            }
        }
        while (choice != 0);
    }

    /* @Description- Add new contacts in address book  */
    private void addContacts() {
        System.out.println("Enter Number of Person you want to add");
        int numOfPerson = sc.nextInt();
        for (int add = 1; add <= numOfPerson; add++){
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
                if (addressbook.check(firstName)) {
                    add--;
                    continue;
                }
                Contacts contacts = new Contacts(firstName, lastName, address, city, state, zipCode, number, email);
                contacts.put(indexValue, contacts);
                indexValue++;
        }
        System.out.println("\nContacts added Successfully");
    }
    /* @Description- Checking Duplicate Contacts Is Exist Or Not In AddressBook  */
    private boolean check(String firstName) {
        if (contacts.isEmpty())
            return false;
        else {
            System.out.println("\nAdd contact again with different first name.");
            Iterator<Integer> itr = contacts.keySet().iterator();
            while (((Iterator<?>) itr).hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(check(firstName))) {
                    System.out.println("\nAdd contact again with different first name.");
                    return true;
                }
            }
        }
        return false;
    }
    /* Description - edit contacts address book */
    public void editContact(){
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            System.out.println("Enter the first name to edit contact.");
            String name = sc.next();
            Iterator<Integer> itr = contacts.keySet().iterator();
            while(itr.hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(name)) {
                    System.out.println("\nEnter First Name to Edit");
                    String first = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Last Name to Edit");
                    String last = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Address to Edit");
                    String address = sc.next();
                    sc.nextLine();
                    System.out.println("Enter City to Edit");
                    String city = sc.nextLine();
                    System.out.println("Enter State to Edit");
                    String state = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Zip Code to Edit");
                    int zip = sc.nextInt();
                    System.out.println("Enter Phone Number to Edit");
                    long phone = sc.nextLong();
                    System.out.println("Enter E-mail to Edit");
                    String email = sc.next();
                    Contacts contact = new Contacts(first, last, address, city, state, zip, phone, email);
                    contact.put(key, contact);
                    System.out.println("Contact edited with given first name : "+name);
                }
            }
        }
    }
    /* Description - create new address book */
    public void createNewAddressBook() {
        System.out.println("Enter The Name For Address Book:");
        String addressBookName = sc.next();
        AddressBookList addressBookListobj = new AddressBookList(addressBookName);
    }
    /* Description - to count  person by state name */
    public void countPersonByState() {
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println(conatactlist.stream().collect(Collectors.groupingBy((Contacts C) -> C.getState(),Collectors.counting())));
    }

    /* Description - to count  person by city name */
    public void countPersonByCity() {
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println(conatactlist.stream().collect(Collectors.groupingBy((Contacts C) -> C.getCity(),Collectors.counting())));
    }

    /* Description - delete contacts in address book  using their name */
    public void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            System.out.println("Enter the first name to delete contact.");
            String name = sc.next();
            Iterator<Integer> itr = contacts.keySet().iterator();
            while(itr.hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(name)) {
                    contacts.remove(key);
                    System.out.println("Contact deleted with first name : "+name);
                }
            }
        }
    }
    /* Description - to search person by city name */
    public void searchPersonByCity() {
        System.out.println("Enter the city to search person.");
        String cityName = sc.next();
        System.out.println("Person Search by " + cityName);
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        Dictionary dictWithCity = new Hashtable();
        conatactlist.stream().filter(n -> n.city.contains(cityName)).forEach(contactlist -> dictWithCity.put(contactlist.firstName, cityName));
        for (Enumeration i = dictWithCity.keys(); i.hasMoreElements(); ) {
            System.out.println(i.nextElement());
        }
    }
    /* Description - to search person by state wise */
    public void searchPersonByState() {
        System.out.println("Enter the state to search person.");
        String stateName = sc.next();
        System.out.println("Person Search by: " + stateName);
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        Dictionary dictWithState = new Hashtable();
        conatactlist.stream().filter(n -> n.state.contains(stateName)).forEach(contactlist -> dictWithState.put(contactlist.firstName, stateName));
        for (Enumeration i = dictWithState.keys(); i.hasMoreElements(); ) {
            System.out.println(i.nextElement());
        }
    }
    /* Description - sort the entries in the address book alphabetically by Person’s name */
    public void sortPersonByFirstname(){
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println("Contact list before sorting the list");
        for (Contacts cont : conatactlist){
            System.out.println(cont.getFirstName() + cont.getLastName());
        }
        System.out.println("Contact list after sorting the list");
        conatactlist.stream();
        conatactlist.sort(Comparator.comparing(Contacts::getFirstName));
        conatactlist.forEach((Contacts cont) -> System.out.println(cont.getFirstName() + " " + cont.getLastName()));
    }
    /* Description - sort the entries in the address book alphabetically by Person’s city */
    public void sortPersonByCity(){
        System.out.println("---Sort by City---");
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println("Contact list before sorting the list");
        for (Contacts cont : conatactlist){
            System.out.println(cont.getCity() + " : " + cont.getFirstName() + " " + cont.getLastName());
        }
        System.out.println("Contact list after sorting the list");
        conatactlist.stream();
        conatactlist.sort(Comparator.comparing(Contacts::getCity));
        conatactlist.forEach((Contacts cont) -> System.out.println(cont.getCity() + " : " +cont.getFirstName() + " " + cont.getLastName()));
    }
    /* Description - sort the entries in the address book alphabetically by Person’s state */
    public void sortPersonByState(){
        System.out.println("---Sort by State---");
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println("Contact list before sorting the list");
        for (Contacts cont : conatactlist){
            System.out.println(cont.getState() + " : " +cont.getFirstName() + " "+ cont.getLastName());
        }
        System.out.println("Contact list after sorting the list");
        conatactlist.stream();
        conatactlist.sort(Comparator.comparing(Contacts::getState));
        conatactlist.forEach((Contacts cont) -> System.out.println(cont.getState() + " : " + cont.getFirstName() + " " + cont.getLastName()));
    }
    /* Description - sort the entries in the address book alphabetically by Person’s zip code */
    public void sortPersonByZipCode(){
        System.out.println("---Sort by ZipCode---");
        Collection<Contacts> values = contacts.values();
        ArrayList<Contacts> conatactlist
                = new ArrayList<>(values);
        System.out.println("Contact list before sorting the list");
        for (Contacts cont : conatactlist){
            System.out.println(cont.getZip() + " : " +cont.getFirstName() + " "+ cont.getLastName());
        }
        System.out.println("Contact list after sorting the list");
        conatactlist.stream();
        conatactlist.sort(Comparator.comparing(Contacts::getZip));
        conatactlist.forEach((Contacts cont) -> System.out.println(cont.getZip() + " : " +cont.getFirstName() + " " + cont.getLastName()));
    }
    /*Main Method*/
    public static void main(String[] args) {
        AddressBook addressbook = new AddressBook();
        addressbook.selectOption();
    }
}
