import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "BelongsTo")
public class BelongsTo {
    @StartNode
    private Project project;
    @EndNode
    private Department department;

    @Override
    public String toString() {
        return "BelongsTo{" +
                "project=" + project.getTitle() +
                ", department=" + department.getName() +
                '}';
    }

    @Property
    private String status;

}
