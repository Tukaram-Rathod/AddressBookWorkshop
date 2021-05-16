package addressbook;

import java.util.List;

public class AddressBookList {
    private List<Contacts> addressBookList;

    public enum IOService {
        DB_IO
    }

    private List<Contacts> contacts;
    public AddressBook addressBook;

    public AddressBookList() {
        addressBook = AddressBook.getInstance();
    }

    public AddressBookList(List<Contacts> addressBook) {
        this();
        this.addressBookList = addressBook;
    }

    public List<Contacts> readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            return this.addressBookList = addressBook.getAddressBookDataUsingDB();
        return null;
    }
}
