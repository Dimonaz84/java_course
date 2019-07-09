package pl.stqa.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
      driver.get(app.properties.getProperty("web.baseUrl") + "/manage_user_page.php");
    }
}
