import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

@NodeEntity(label = "Project")
public class Project {


    @GraphId
    private int id;

    @Property
    private String title;
    @Property
    private Date startDate;

    @Property
    private Date endDate;

    @Relationship(type = "BelongsTo")
    private Set<Department> departments;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", departments=" + departments +
                '}';
    }

}
