package pl.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

        @BeforeClass
        protected void setUp() throws Exception {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            try {
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy( registry );
            }
        }

    @Test
    public void testHbConnection() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for (GroupData group : result) {
            System.out.println(group);
        }

        List<ContactData> resultCont = session.createQuery("from ContactData where deprecated = '0000-00-00 00:00:00'").list();

        session.getTransaction().commit();
        session.close();

        for (ContactData contact : resultCont) {
            System.out.println(contact);
            System.out.println("Groups for contact " + contact.getGroups());
        }


    }
}
