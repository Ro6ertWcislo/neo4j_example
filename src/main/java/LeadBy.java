import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "LeadBy")
public class LeadBy {
    @StartNode
    private Department department;

    @Override
    public String toString() {
        return "LeadBy{" +
                "department=" + department.getName() +
                ", person=" + person.getName() +
                '}';
    }

    @EndNode
    private Person person;
}
