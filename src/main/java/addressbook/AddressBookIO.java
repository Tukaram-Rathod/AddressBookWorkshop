package addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookIO {

    public static String ADDRESS_BOOK_FILE = "address_book_file.txt";

    public void writeData(List<Contacts> addressbooklist){
        StringBuffer empBuffer = new StringBuffer();
        addressbooklist.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(ADDRESS_BOOK_FILE), empBuffer.toString().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(ADDRESS_BOOK_FILE).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
    public List<Contacts> readData() {
        List<Contacts> addressbooklist = new ArrayList<>();
        try {
            Files.lines(new File(ADDRESS_BOOK_FILE).toPath())
                    .map(String::trim)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressbooklist;
    }
    public void printData(){
        try {
            Files.lines(new File(ADDRESS_BOOK_FILE).toPath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
