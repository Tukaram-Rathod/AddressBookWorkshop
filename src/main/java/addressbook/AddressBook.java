package addressbook;
/* @Description - To create a contacts in address book with first name, last name, address, city, state,
 * zip,mobile number.*/
public class AddressBook {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");
        Contacts contact = new Contacts("Ashish","Rathod","Gangakhed","Parbhani","Maharashtra",431514,805925703,"Ashu03.mit@gmail.com");
        System.out.println(contact);
    }
}
