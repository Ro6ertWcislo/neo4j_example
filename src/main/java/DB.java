import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.File;
import java.util.Date;

public class DB {
    GraphDatabaseService graphDb;

    public DB(String graphDir) {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        graphDb = graphDbFactory.newEmbeddedDatabaseBuilder(new File(graphDir))
                .setConfig(GraphDatabaseSettings.allow_upgrade, "true")
                .newGraphDatabase();

        Runtime.getRuntime().addShutdownHook(new Thread(()->graphDb.shutdown()));
    }

    public Node createPerson(String name, String startedWork) {
        try (Transaction transaction = graphDb.beginTx()) {
            Node person = graphDb.createNode(Label.label("Person"));
            person.setProperty("name", name);
            person.setProperty("startedWork", startedWork);
            transaction.success();
            transaction.close();
            return person;
        }

    }

    public Node createDepartment(String name) {
        try (Transaction transaction = graphDb.beginTx()) {
            Node department = graphDb.createNode(Label.label("Department"));
            department.setProperty("name", name);
            transaction.success();
            transaction.close();
            return department;
        }

    }

    public Node createProject(String title,String startDate, String endDate) {
        try (Transaction transaction = graphDb.beginTx()) {
            Node project = graphDb.createNode(Label.label("Project"));
            project.setProperty("title", title);
            project.setProperty("startDate",startDate);
            project.setProperty("endDate", endDate);
            transaction.success();
            transaction.close();
            return project;
        }
    }

    public Relationship createRelationship(Node start, Node end, RelationshipType relationship){
        try (Transaction transaction = graphDb.beginTx()) {
            Relationship rel = start.createRelationshipTo(end,relationship);
            transaction.success();
            transaction.close();
            return rel;
        }
    }

    public Relationship createRelationship(Node start, Node end, String relationship){
        try (Transaction transaction = graphDb.beginTx()) {
            Relationship rel = start.createRelationshipTo(end,RelationshipType.withName(relationship));
            transaction.success();
            transaction.close();
            return rel;
        }
    }

    public String execute(final String cypher) {
        try (Transaction transaction = graphDb.beginTx()) {
            final Result result = graphDb.execute(cypher);
            transaction.success();
            return result.resultAsString();
        }
    }

    public Iterable<Relationship> getNodeRelations(Node node){
        try (Transaction transaction = graphDb.beginTx()) {
            Iterable<Relationship> rels = node.getRelationships();
            transaction.success();
            transaction.close();
            return rels;
        }
    }

    public String getNodeRelations(String type,String name){
        try (Transaction transaction = graphDb.beginTx()) {
        String rels = execute("Match (d:"+type+"{name:\""+name+"\"})-[r]-() Return r");
            transaction.success();
            transaction.close();
            return rels;
        }
    }

    public String getShortestPath(String type1, String name1, String type2, String name2){
        try (Transaction transaction = graphDb.beginTx()) {
            String rels = execute("MATCH p=shortestPath((pp:"+type1+" {name:\""+name1+"\"})-[*]-(dd:" +type2 +
                    "{name:\""+name2+"\"})) RETURN p");
            transaction.success();
            transaction.close();
            return rels;
        }
    }

    public String getShortestPath2(long id1, long id2){
        try (Transaction transaction = graphDb.beginTx()) {
            String rels = execute("START s = NODE("+id1+"), r = NODE("+id2+") MATCH p=shortestPath((s)-[*]-(r)) RETURN p");
            transaction.success();
            transaction.close();
            return rels;
        }
    }


}
