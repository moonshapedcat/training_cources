package ru.stqa.pft.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {


  private Properties properties;
  private RestHelper restHelper;

  public void init() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
  }


  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RestHelper RestHelper() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }
}
