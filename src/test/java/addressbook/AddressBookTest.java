package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AddressBookTest {
    //uc-13
    @Test
    public void given3ContactsWhenWrittenToIOFileShouldMatchContactEntries() {
        Contacts[] contacts = {
                new Contacts("Ashish", "Rathod", "Gangakhed", "Parbhani", "Maharashtra", "431514", "8788594431", "Ashu02@gmail.com"),
                new Contacts("Sakha", "Rathod", "Gangakhed", "Parbhani", "Maharashtra", "431514", "9420416222", "Sakha@gmil.com"),
                new Contacts("Satya", "Jadhav", "Parbhani", "Aurangabad", "Mit ", "431010", "7721594431", "Satya09379@gmail.com")
        };
        AddressBookMain addressBookMain;
        addressBookMain = new AddressBookMain(Arrays.asList(contacts));
        addressBookMain.writeAddressBookData(AddressBookMain.IOService.FILE_IO);
        System.out.println("Reading from file -");
        addressBookMain.readAddressBookData(AddressBookMain.IOService.FILE_IO);
        long entries = addressBookMain.countEntries(AddressBookMain.IOService.FILE_IO);
        addressBookMain.printData(AddressBookMain.IOService.FILE_IO);
        Assert.assertEquals(3, entries);
    }
    //uc-14
    @Test
    public void givenContactWhenReadFromCSVFile_shouldMatchWithFile() {
        AddressBookMain addressBookMain;
        addressBookMain = new AddressBookMain();
        addressBookMain.writeCSVAddressBookData(AddressBookMain.IOService.CSV_FILE);
        int entries = (int) addressBookMain.readCSVAddressBookData(AddressBookMain.IOService.CSV_FILE);
        Assert.assertEquals(3,entries);
    }
    //uc-16
    @Test
    public void contactsWhenRetrievedFromDB_ShouldMatchCount() {
        AddressBookMain addressBookMain = new AddressBookMain();
        List<Contacts> addressBookData = addressBookMain.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        Assert.assertEquals(5, addressBookData.size());
    }
    //uc-17
    @Test
    public void givenNewPhoneNumberForPerson_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() {
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        addressBookMain.updateAddressBook(" Suresh Gore", "7000541230");
        boolean result = addressBookMain.checkAddressBookInSyncWithDB(" Suresh Gore");
        Assert.assertTrue(result);
    }

}
