import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity(label = "Department")
public class Department {
    @Property
    private String name;
    @Relationship(type = "WorksAt", direction = INCOMING)
    private Set<Person> peopleWorking;

    public String getName() {
        return name;
    }
    @GraphId
    private int id;

    @Relationship(type = "LeadBy", direction = OUTGOING)

    private Set<Person> peopleLeading;

    @Relationship(type = "BelongsTo", direction = INCOMING)
    private Set<Person> projects;

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", peopleWorking=" + peopleWorking +
                ", peopleLeading=" + peopleLeading +
                ", projects=" + projects +
                '}';
    }
}
