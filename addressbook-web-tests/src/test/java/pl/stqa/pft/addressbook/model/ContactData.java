package pl.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String company;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String email;
    private String email2;
    private String email3;
    private String address2;
    private String birth_day;
    private String birth_month;
    private String birth_year;

//    public ContactData(String firstName, String lastName, String title, String company, String address, String homePhone, String mobilePhone, String email, String email2, String email3, String address2, String birth_day, String birth_month, String birth_year) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.mobilePhone = mobilePhone;
//        this.email = email;
//        this.email2 = email2;
//        this.email3 = email3;
//        this.address2 = address2;
//        this.birth_day = birth_day;
//        this.birth_month = birth_month;
//        this.birth_year = birth_year;
//    }

//    public ContactData(int id, String firstName, String lastName, String title, String company, String address, String homePhone, String mobilePhone, String email, String email2, String email3, String address2, String birth_day, String birth_month, String birth_year) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.mobilePhone = mobilePhone;
//        this.email = email;
//        this.email2 = email2;
//        this.email3 = email3;
//        this.address2 = address2;
//        this.birth_day = birth_day;
//        this.birth_month = birth_month;
//        this.birth_year = birth_year;
//    }

//   public ContactData(int id, String firstName, String lastName) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = null;
//        this.company = null;
//        this.address = null;
//        this.homePhone = null;
//        this.mobilePhone = null;
//        this.email = null;
//        this.email2 = null;
//        this.email3 = null;
//        this.address2 = null;
//        this.birth_day = null;
//        this.birth_month = null;
//        this.birth_year = null;
    //}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAddress2() {
        return address2;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public String getBirth_month() {
        return birth_month;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withBirth_day(String birth_day) {
        this.birth_day = birth_day;
        return this;
    }

    public ContactData withBirth_month(String birth_month) {
        this.birth_month = birth_month;
        return this;
    }

    public ContactData withBirth_year(String birth_year) {
        this.birth_year = birth_year;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}
