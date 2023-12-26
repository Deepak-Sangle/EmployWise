package com.groupware.employwise.repository;

import com.groupware.employwise.model.User;
import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import org.ektorp.support.Views;

import java.util.ArrayList;
import java.util.List;

@Views({
        @View(name = "all_documents", map = "function(doc) { emit(doc, null); }"),
        @View(name = "all_documents_by_name", map = "function(doc) { emit(doc.name, null); }"),
        @View(name = "all_documents_by_email", map = "function(doc) { emit(doc.email, null); }")
})
public class EmployeeRepository extends CouchDbRepositorySupport<User> {
    private static EmployeeRepository instance;
    private static CouchDbConnector db;

    private EmployeeRepository() {
        super(User.class, db);
        initStandardDesignDocument();
    }

    private static void connectCouchDb(){
        HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
                .port(5984)
                .username("admin")
                .password("adminroot")
                .build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        db = new StdCouchDbConnector("employees", dbInstance);
        db.createDatabaseIfNotExists();
    }

    public static synchronized EmployeeRepository getInstance() {
        if (instance == null) {
            connectCouchDb();
            instance = new EmployeeRepository();
        }
        return instance;
    }

    public User getUser(String id) {
        try {
            return db.get(User.class, id);
        } catch (DocumentNotFoundException e) {
            return null;
        }
    }

    public void saveUser(User u) {
        db.create(u);
    }
    public void updateUser(User u) {
        try {
            db.update(u);
        }
        catch (DocumentNotFoundException e) {
            return;
        }
    }

    public List<User> getAllUser(int page, int size, String sortBy){
        if(page < 0) {
            return new ArrayList<>();
        }
        return switch (sortBy.toLowerCase()) {
            case "name" -> findByAllDocumentsByName(page, size);
            case "email" -> findByAllDocumentsByEmail(page, size);
            default -> findByAllDocuments(page, size);
        };

    }

    public void deleteUser(User u) {
        try {
            db.delete(u);
        }
        catch (DocumentNotFoundException e) {
            return;
        }
    }

    List<User> findByAllDocuments(int page, int size) {
        ViewQuery query = createQuery("all_documents").includeDocs(true).skip(page * size).limit(size);
        return db.queryView(query, User.class);
    }

    List<User> findByAllDocumentsByName(int page, int size){
        ViewQuery query = createQuery("all_documents_by_name").includeDocs(true).skip(page * size).limit(size);
        return db.queryView(query, User.class);
    }

    List<User> findByAllDocumentsByEmail(int page, int size){
        ViewQuery query = createQuery("all_documents_by_email").includeDocs(true).skip(page * size).limit(size);
        return db.queryView(query, User.class);
    }

}

