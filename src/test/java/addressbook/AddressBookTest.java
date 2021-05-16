package addressbook;

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    //UC-18
    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookMain addressBook = new AddressBookMain();
        addressBook.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2019, 01, 1);
        LocalDate endDate = LocalDate.now();
        List<Contacts> addressBookDataList = addressBook.readContactDataForGivenDateRange(startDate, endDate);
        Assert.assertEquals(4, addressBookDataList.size());
    }
    //uc-19
    @Test
    public void givenContacts_RetrieveNumberOfContacts_ByCityOrState() {
        AddressBookMain addressBook = new AddressBookMain();
        addressBook.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        Map<String, Integer> contactByCityOrStateMap = addressBook.readContactByCityOrState();
        Assert.assertEquals((int) contactByCityOrStateMap.get("Parbhani"), 1);
        Assert.assertEquals((int) contactByCityOrStateMap.get("Maharashtra"), 2);
    }
    //uc-20
    @Test
    public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
        AddressBookMain addressBook = new AddressBookMain();
        addressBook.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        addressBook.addContactToDB(1,"Akshay Kumar","Akshay", "Kumar", "Hadapsar", "Pune", "Maharashtra", 411056,
                "7714771122", "Akhi@gmail.com", Date.valueOf("2021-05-15"));
        boolean result = addressBook.checkAddressBookInSyncWithDB("Akshay Kumar");
        Assert.assertTrue(result);
    }
    //UC-21
    @Test
    public void givenContacts_WhenAddedToDB_ShouldMatchEmployeeEntries() {
        Contacts[] addressBookArray = {
                new Contacts(2,"Disha Patani","Disha", "Patani", "Om Nagar",
                        "Ahmedabad", "Lahor", 451238, "8745962473",
                        "disha@gmail.com", Date.valueOf("2021-06-16")),
                new Contacts(3,"Mahadev Sharma","Mahadev", "Sharma",
                        "jalna", "Aurangabad", "Maharashtra", 430005, "7546231547",
                        "mahadev@gmail.com", Date.valueOf("2019-04-01"))};
        AddressBookMain addressBook = new AddressBookMain();
        addressBook.readAddressBookDataDB(AddressBookMain.IOService.DB_IO);
        Instant start = Instant.now();
        addressBook.addDetails(Arrays.asList(addressBookArray));
        Instant end = Instant.now();
        System.out.println("Duration without thread : " + Duration.between(start, end));
        Instant threadStart = Instant.now();
        addressBook.addDetailsWithThreads(Arrays.asList(addressBookArray));
        Instant threadEnd = Instant.now();
        System.out.println("Duration with Thread : " + Duration.between(threadStart, threadEnd));
        Assert.assertEquals(10, addressBook.countEntries(AddressBookMain.IOService.DB_IO));
    }

}
