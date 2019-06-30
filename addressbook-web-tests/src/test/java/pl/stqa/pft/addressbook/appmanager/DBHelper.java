package pl.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.pft.addressbook.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }

     public Groups groups() {
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         List<GroupData> result = session.createQuery( "from GroupData" ).list();
         for (GroupData group : result) {
             System.out.println(group);
         }

         session.getTransaction().commit();
         session.close();
         return new Groups(result);
     }

     public int getNewGroupId() {
         List<Integer> ids = new ArrayList<>();
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         List<GroupData> result = session.createQuery( "from GroupData" ).list();
         session.getTransaction().commit();
         session.close();

         for (GroupData group : result) {
             ids.add(group.getId());
         }
         return Collections.max(ids);
     }

    public GroupData findFreeGroup(Groups groups, int contactId) {

        List<Integer> usedGroupIds = new ArrayList<>();
        List allGroupIds = new ArrayList();

        for (GroupData group : groups){
            allGroupIds.add(group.getId());
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<RelationData> query = session.createQuery("from RelationData").list();

        session.getTransaction().commit();
        session.close();

        for (RelationData entry : query){
            if (entry.getContactId() == contactId){
                usedGroupIds.add(entry.getGroupId());
            }
        }

        List<Integer> freeGroupsIds = new ArrayList<>(allGroupIds);
        freeGroupsIds.removeAll(usedGroupIds);

        if (freeGroupsIds.size() != 0){
            for (GroupData group : groups) {
                if (group.getId() == freeGroupsIds.iterator().next()){
                    return group;
                }
            }
        }
        return null;
    }

     public Contacts contacts() {
         Session session = sessionFactory.openSession();
         session.beginTransaction();

         List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00 00:00:00'").list();

         session.getTransaction().commit();
         session.close();
         return new Contacts(result);
     }

     public boolean isContactAdded (ContactData contact, GroupData group){
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         List<RelationData> query = session.createQuery("from RelationData").list();
         session.getTransaction().commit();
         session.close();
         for (RelationData entry : query) {
             if (entry.getContactId() == contact.getId() && entry.getGroupId() == group.getId()) {
                return true;
             }
         } return false;
     }
}

