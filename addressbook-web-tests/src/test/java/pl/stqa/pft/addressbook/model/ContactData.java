package pl.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;
import java.util.Objects;

public class ContactData {

    @XStreamAlias("contact")

    private int id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String title;
    @Expose
    private String company;
    @Expose
    private String address;
    @Expose
    private String address2;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    private String allPhones;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    private String allEmails;
    @Expose
    private String birth_day;
    @Expose
    private String birth_month;
    @Expose
    private String birth_year;
    private File photo;
    @Expose
    private String filePath;

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

    public String getWorkPhone() { return workPhone; }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
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

    public File getPhoto() {
        return photo;
    }

    public String getFilePath() {
        return filePath;
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

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
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

    public ContactData withAllEmails(String allEmails){
        this.allEmails = allEmails;
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

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withFilePath(File photo) {
        this.filePath = photo.getAbsolutePath();
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
