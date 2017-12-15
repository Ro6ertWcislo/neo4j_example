import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String graphDir = "C:\\Users\\ezwciro\\Documents\\Neo4j\\default.graphdb";
        DB db = new DB(graphDir);

        DataGenerator dg = new DataGenerator(db);

        System.out.println(db.execute("MATCH () RETURN COUNT(*) AS node_count;")); // nodes before
        System.out.println(db.execute("MATCH ()-->() RETURN COUNT(*) AS rel_count;")); //rels before
        dg.generate(10,10);
        System.out.println(db.execute("MATCH ()-->() RETURN COUNT(*) AS rel_count;")); // rels after
        System.out.println(db.execute("MATCH () RETURN COUNT(*) AS node_count;")); // nodes after


    }

    public static void zad4example(DB db) {
        String str1 = "2017/10/22 12:12:12";
        String str2 = "2017/07/05 12:12:12";
        String str3 = "2017/08/22 12:12:12";

        Node worker = db.createPerson("Przodowik pracy11", str1);
        Node worker1 = db.createPerson("Len paskudny11", str1);
        Node worker2 = db.createPerson("taki zwykly pracownik11", str1);
        Node project = db.createProject("projekt ze hoho11", str2, str3);
        Node departmenet = db.createDepartment("InfoPolBudEx11");
        db.createRelationship(worker, departmenet, "WorksAt");
        db.createRelationship(worker1, departmenet, "WorksAt");
        db.createRelationship(worker2, departmenet, "WorksAt");
        db.createRelationship(worker, departmenet, "LeadBy");
        db.createRelationship(project, departmenet, "BelongsTo");

        Iterable<Relationship> x = db.getNodeRelations(departmenet);
        Transaction tx = db.graphDb.beginTx();
        x.forEach(System.out::println);
        tx.success();
        tx.close();
    }

    public static void zad4example2(DB db) {
        String str1 = "2017/10/02 12:12:12";
        String str2 = "2017/01/15 12:12:12";
        String str3 = "2017/04/20 12:12:12";

        Node worker = db.createPerson("Przodowik pracy111", str1);
        Node worker1 = db.createPerson("Len paskudny111", str2);
        Node worker2 = db.createPerson("taki zwykly pracownik111", str3);
        Node project = db.createProject("projekt ze hoho111", str2, str3);
        Node departmenet = db.createDepartment("InfoPolBudEx111");
        db.createRelationship(worker, departmenet, "WorksAt");
        db.createRelationship(worker1, departmenet, "WorksAt");
        db.createRelationship(worker2, departmenet, "WorksAt");
        db.createRelationship(worker, departmenet, "LeadBy");
        db.createRelationship(project, departmenet, "BelongsTo");

        String x = db.getNodeRelations("Department","InfoPolBudEx111");
        System.out.println(x);
    }


    public static void zad5example2(DB db) {
        String str1 = "2017/10/02 12:12:12";
        String str2 = "2017/01/15 12:12:12";
        String str3 = "2017/04/20 12:12:12";

        Node worker = db.createPerson("owni3k11", str1);
        Node project = db.createProject("ho1311", str2, str3);
        Node departmenet = db.createDepartment("BudE3x111");
        db.createRelationship(worker, departmenet, "WorksAt");
        db.createRelationship(worker, departmenet, "LeadBy");
        db.createRelationship(project, departmenet, "BelongsTo");

        String x = db.getShortestPath2(project.getId(),worker.getId());
        System.out.println(x);
    }
}
