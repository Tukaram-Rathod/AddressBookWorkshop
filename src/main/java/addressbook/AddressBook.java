package addressbook;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
 * zip,mobile number.*/
public class AddressBook {
    private static AddressBook addressBook;

    public static AddressBook getInstance() {
        if (addressBook == null)
            addressBook = new AddressBook();
        return addressBook;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book_services?useSSL=false";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!" + connection);
        return connection;
    }

    public List<Contacts> getAddressBookDataUsingDB() {
        String sql = "SELECT * FROM address_book_table";
        List<Contacts> contacts = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contacts = getAddressBookDataList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private List<Contacts> getAddressBookDataList(ResultSet resultSet) {
        List<Contacts> contacts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("fisrtname");
                String lastName = resultSet.getString("lastname");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                long phoneNumber = resultSet.getLong("phoneNumber");
                String email = resultSet.getString("email");
                contacts.add(new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

}
