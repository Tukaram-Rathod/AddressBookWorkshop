package addressbook;


import java.util.Objects;

public class Contacts {
    public String firstName;
    public String name;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String phoneNumber;
    public String email;
    public int type;

    public Contacts(int type,String name,String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.type=type;
        this.name=name;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    public Contacts(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts addressBookData = (Contacts) o;
        return zip == addressBookData.zip && Objects.equals(name, addressBookData.name) &&
                Objects.equals(firstName, addressBookData.firstName) &&
                Objects.equals(lastName, addressBookData.lastName) &&
                Objects.equals(address, addressBookData.address) &&
                Objects.equals(city, addressBookData.city) &&
                Objects.equals(state, addressBookData.state) &&
                Objects.equals(phoneNumber, addressBookData.phoneNumber) &&
                Objects.equals(email, addressBookData.email) &&
                Objects.equals(type, addressBookData.type);
    }
}

