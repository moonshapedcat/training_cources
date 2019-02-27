package ru.stqa1.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names= "-c", description = "Group count")
  public int count;

  @Parameter(names= "-f", description = "target file")
  public String file;

  /*@Parameter(names= "-d", description = "data format")
  public String format;*/

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
    jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }

    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(), contact.getMiddlename(), contact.getTitle(), contact.getCompanyName(),
              contact.getHomePhone(), contact.getMobilePhone()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0; i<count; i++){
      contacts.add(new ContactData().withName(String.format("name %s", i))
              .withLastname(String.format("lastname %s", i)).withMiddlename(String.format("midllename %s", i))
              .withTitle(String.format("title %s", i)).withCompanyName(String.format("company %s", i)).withHomePhone(String.format("homephone %s", i))
              .withMobilePhone(String.format("mobilephone %s", i)));
    }
    return contacts;
  }
}
