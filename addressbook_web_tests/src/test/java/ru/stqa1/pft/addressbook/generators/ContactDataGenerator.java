package ru.stqa1.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names= "-c", description = "Contact count")
  public int count;

  @Parameter(names= "-f", description = "target file")
  public String file;

  @Parameter(names= "-d", description = "data format")
  public String format;

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
    if (format.equals("csv")) {
      saveAsCSV(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(contacts, new File(file));
    }
    else {
      System.out.println("Unrecognized format" + format);
    }

  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getMiddlename(), contact.getLastname(), contact.getNickname(), contact.getCompanyName(),
                contact.getTitle()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0; i<count; i++){
      contacts.add(new ContactData().withName(String.format("name %s", i)).withMiddlename(String.format("middlename %s", i)).
              withLastname(String.format("middlename %s", i)).withNickname(String.format("nickname %s", i)).
              withCompanyName(String.format("company %s", i)).withTitle(String.format("title %s", i)));
    }
    return contacts;
  }
}
