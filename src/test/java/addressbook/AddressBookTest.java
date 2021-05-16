package addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookTest {
    @Test
    public void givenObject_WhenStoredInTheJson_ShouldReturn_TRUE() {
        Contacts contact = new Contacts("Ashish",
                "Rathod",
                "Gangakhed",
                "Parbhani",
                "Maharashtra",
                431514,
                880592570,
                "Ashu03.mit@gmail.com");

        AddressBook addressBook = new AddressBook();
        boolean isAdded = addressBook.jsonWrite(contact);
        Assertions.assertTrue(isAdded);
    }

    @Test
    public void givenJsonFile_WhenRead_ShouldReturn_TRUE() {
        AddressBook addressBook = new AddressBook();
        boolean isRead = addressBook.jsonRead();
        Assertions.assertTrue(isRead);
    }
}
