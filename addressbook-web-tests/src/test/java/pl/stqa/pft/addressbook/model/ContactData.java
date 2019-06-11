package pl.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String email;
    private final String email2;
    private final String email3;
    private final String address2;
    private final String birth_day;
    private final String birth_month;
    private final String birth_year;

    public ContactData(String firstname, String lastname, String title, String company, String address, String homePhone, String mobilePhone, String email, String email2, String email3, String address2, String birth_day, String birth_month, String birth_year) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.address2 = address2;
        this.birth_day = birth_day;
        this.birth_month = birth_month;
        this.birth_year = birth_year;
    }

    public ContactData(int id, String firstname, String lastname, String title, String company, String address, String homePhone, String mobilePhone, String email, String email2, String email3, String address2, String birth_day, String birth_month, String birth_year) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.address2 = address2;
        this.birth_day = birth_day;
        this.birth_month = birth_month;
        this.birth_year = birth_year;
    }

   public ContactData(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = null;
        this.company = null;
        this.address = null;
        this.homePhone = null;
        this.mobilePhone = null;
        this.email = null;
        this.email2 = null;
        this.email3 = null;
        this.address2 = null;
        this.birth_day = null;
        this.birth_month = null;
        this.birth_year = null;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
