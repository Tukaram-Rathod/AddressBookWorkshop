package addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class AddressBookDB {
    public static AddressBookDB addressBookDB;
    private PreparedStatement addressBookDataStatement;
    public AddressBookDB(){
    }

    public static AddressBookDB getInstance(){
        if(addressBookDB == null)
            addressBookDB = new AddressBookDB();
        return addressBookDB;
    }
    //uc-16
    public List<Contacts> readData() {
        String sql = "SELECT * from address_book_table;";
        return this.getContactDetailsUsingSqlQuery(sql);
    }
    //uc-17
    int updateAddressBookData(String name, String phoneNumber) {
        return this.updateAddressBookDataUsingPreparedStatement(name,phoneNumber);
    }
    //UC-18
    public List<Contacts> getContactForGivenDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = String.format(
                "SELECT * from address_book_table WHERE date_added BETWEEN '%s' AND '%s';",
                Date.valueOf(startDate), Date.valueOf(endDate));
        return this.getContactDetailsUsingSqlQuery(sql);
    }
    private List<Contacts> getContactDetailsUsingSqlQuery(String sql) {
        List<Contacts> addressBookDataList = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            addressBookDataList = this.getAddressBookData(String.valueOf(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookDataList;
    }

    public List<Contacts> getAddressBookData(ResultSet resultSet) {
        List<Contacts> addressBookDataList  = new ArrayList<>();
        try {
            while (resultSet.next()){
                Integer type = resultSet.getInt("type");
                String name = resultSet.getString("name");
                String firstName = resultSet.getString("fisrtName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                addressBookDataList .add(new Contacts(firstName,lastName,address,city,state,zip,phoneNumber,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookDataList ;
    }
    public List<Contacts> getAddressBookData(String name) {
        List<Contacts> addressBookDataList = null;
        if(this.addressBookDataStatement == null)
            this.prepareStatementForAddressBookData();
        try{
            addressBookDataStatement.setString(1,name);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            addressBookDataList = this.getAddressBookData(String.valueOf(resultSet));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return addressBookDataList;
    }

    private void prepareStatementForAddressBookData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM address_book_table WHERE name = ?";
            addressBookDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateAddressBookDataUsingPreparedStatement(String name, String phoneNumber) {
        try(Connection connection = this.getConnection()){
            String sql = String.format("UPDATE address_book_table SET phoneNumber = ? WHERE name = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phoneNumber);
            preparedStatement.setString(2,name);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/ address_book_services?useSSL=false";
        String userName = "root";
        String passWord = "root";
        Connection connection;
        System.out.println("Connecting to database"+jdbcURL);
        connection = DriverManager.getConnection(jdbcURL,userName,passWord);
        System.out.println("Connection is successful!!"+connection);
        return connection;
    }
}
