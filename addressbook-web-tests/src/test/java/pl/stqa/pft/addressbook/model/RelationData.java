package pl.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "address_in_groups")

public class RelationData implements Serializable {

    @Id
    @Column(name = "id")
    private int contactId;

    @Id
    @Column(name = "group_id")
    private int groupId;

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }
}
