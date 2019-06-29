package pl.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {

    @XStreamAlias("contact")
    @Id
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "title")
    private String title;
    @Expose
    @Column(name = "company")
    private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String address2;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmails;
    @Expose
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bday", columnDefinition = "TINYINT")
    @Type(type = "text")
    private String birth_day;
    @Expose
    @Column(name = "bmonth")
    private String birth_month;
    @Expose
    @Column(name = "byear")
    private String birth_year;
    @Column(name = "photo")
    @Type(type = "text")
    @Expose
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

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

    public String getPhoto() {
        return photo;
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

    public ContactData withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(birth_day, that.birth_day) &&
                Objects.equals(birth_month, that.birth_month) &&
                Objects.equals(birth_year, that.birth_year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, title, company, address, address2, homePhone, mobilePhone, workPhone, email, email2, email3, birth_day, birth_month, birth_year);
    }
}
