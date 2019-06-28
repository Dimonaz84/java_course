package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DBConnectionTest {

@Test
    public void testDBConnection() {

    Connection conn = null;

    try {
        conn =
                DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                        "user=root&password=&serverTimezone=UTC");

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select group_id,group_name,group_footer,group_header from group_list");
        Groups groups = new Groups();
        while (rs.next()) {
            groups.add(new GroupData().withId(rs.getInt("group_id"))
                    .withName(rs.getString("group_name"))
                    .withFooter(rs.getString("group_footer"))
                    .withHeader(rs.getString("group_header")));
        }
        rs.close();
        statement.close();
        conn.close();

        System.out.println(groups);

    } catch (SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
}


}
