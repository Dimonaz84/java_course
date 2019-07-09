package pl.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.pft.mantis.model.UserData;
import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;
    private ApplicationManager app;

    public DBHelper(ApplicationManager app) {
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public List<UserData> getUsers() {
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         List<UserData> result = session.createQuery( "from UserData" ).list();

        int adminIndex = 0;                             //REMOVING ADMINISTRATOR FROM USERS LIST
         for (int i = 0; i < result.size(); i++) {
             if (result.get(i).getUserName().equals("administrator"))
                 adminIndex = i;
         }
         result.remove(adminIndex);

         session.getTransaction().commit();
         session.close();
         return result;
     }
}

