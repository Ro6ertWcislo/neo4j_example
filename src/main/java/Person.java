import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

import java.util.Date;
import java.util.Set;

@NodeEntity(label = "Person")
public class Person {

    @Property
    private String name;

    public String getName() {
        return name;
    }
    @GraphId
    private int id;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", startedWork=" + startedWork +
                ", departamentsWork=" + departamentsWork +
                ", departamentsLead=" + departamentsLead +
                '}';
    }

    @Property
    private Date startedWork;

    @Relationship(type = "WorksAt",direction = OUTGOING)
    private  Set<Department> departamentsWork;

    @Relationship(type = "LeadBy",direction = INCOMING)
    private Set<Department> departamentsLead;


}
