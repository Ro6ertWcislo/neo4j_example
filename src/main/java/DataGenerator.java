import org.neo4j.graphdb.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private DB db;

    public DataGenerator(DB db) {
        this.db = db;
    }
    private int min(int x, int y){
        if (x<y) return x;
        else return y;
    }

    public void generate(int nodes, int relationships){
        List<Node> people = new LinkedList<>();
        List<Node> deps= new LinkedList<>();
        List<Node> projects= new LinkedList<>();
        Random rn = new Random();

        for(int i=0; i<nodes;i++){
            people.add(db.createPerson("person"+rn.nextInt(),"2017/12/"+i+" 12:00:00"));
            deps.add(db.createDepartment("dep"+rn.nextInt()));
            projects.add(db.createProject("person"+rn.nextInt(),"2017/12/"+(i+2*nodes)+" 12:00:00","2017/12/"+(i+2*nodes)+" 12:00:00"));
        }

        for(int i =0;i<min(relationships,nodes);i++){
            db.createRelationship(people.get(i),deps.get(rn.nextInt(deps.size())),"WorksAt");
            db.createRelationship(deps.get(i),people.get(rn.nextInt(people.size())),"LeadBy");
            db.createRelationship(projects.get(i),deps.get(rn.nextInt(deps.size())),"BelongsTo");
        }
    }


}
