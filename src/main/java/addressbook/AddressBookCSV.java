package addressbook;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class AddressBookCSV {
    public static String ADDRESS_BOOK_CSV_FILE = "address-book.csv";
    public static String[] HEADER = {"firstname", "lastname", "address", "city", "state", "zip", "phoneNumber", "email"};

    public void writeCSVData() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(ADDRESS_BOOK_CSV_FILE);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeNext(HEADER);

            List<String[]> list = new ArrayList<>();
            list.add(new String[] {"Ashish", "Rathod", "Gangakhed", "Parbhani", "Maharashtra", "431514", "8788594431", "Ashu02@gmail.com"});
            list.add(new String[] {"Sakha", "Rathod", "Gangakhed", "Parbhani", "Maharashtra", "431514", "9420416222", "Sakha@gmil.com"});
            list.add(new String[] {"Satya", "Jadhav", "Parbhani", "Aurangabad", "Mit ", "431010", "7721594431", "Satya09379@gmail.com"});

            csvWriter.writeAll(list);
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int readData(String filePathCSV) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            CsvToBean<ContactCSV> csvToBean = new CsvToBeanBuilder<ContactCSV>(reader)
                    .withType(ContactCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<ContactCSV> addressBookCSVIterator = csvToBean.iterator();
            Iterable<ContactCSV> contactCSVIterable = () -> addressBookCSVIterator;
            return (int) StreamSupport.stream(contactCSVIterable.spliterator(), false).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
