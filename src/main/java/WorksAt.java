import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "WorksAt")
public class WorksAt {
    @StartNode
    private Person person;
    @EndNode
    private Department department;

    @Override
    public String toString() {
        return "WorksAt{" +
                "person=" + person.getName() +
                ", department=" + department.getName() +
                '}';
    }

    @Property
    private String role;
}
