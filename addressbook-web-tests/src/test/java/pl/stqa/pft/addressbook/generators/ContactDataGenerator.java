package pl.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.stqa.pft.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
          if (format.equals("json")){
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format. " + format);
        }

    }

    private List<ContactData> generateContacts(int count) {
        File photo = new File("src/test/resources/mypic.jpg");
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withId(i).withFirstName(String.format("firstName %s", i))
                    .withLastName(String.format("lastName %s", i)).withHomePhone(String.format("123456789"))
                    .withMobilePhone(String.format("987654321")).withWorkPhone(String.format("587429321"))
                    .withTitle(String.format("Mr")).withAddress(String.format("Address"))
                    .withAddress2(String.format("Address2")).withEmail(String.format("email@email.com"))
                    .withEmail2(String.format("email2@email.com")).withEmail3(String.format("email@email3.com"))
                    .withCompany(String.format("TestCompany")).withBirth_day(String.format("6"))
                    .withBirth_month(String.format("September")).withBirth_year(String.format("1984"))
                    .withPhoto(photo));
        }
        return contacts;
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }
}
