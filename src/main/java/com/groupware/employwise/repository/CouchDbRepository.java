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

import java.util.List;

public class CouchDbRepository  {
    private static CouchDbRepository instance;
    private final CouchDbConnector db;

    private CouchDbRepository() {
        HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
                .port(5984)
                .username("admin")
                .password("adminroot")
                .build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        db = new StdCouchDbConnector("employees", dbInstance);
        db.createDatabaseIfNotExists();
    }

    public static synchronized CouchDbRepository getInstance() {
        if (instance == null) {
            instance = new CouchDbRepository();
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
        db.update(u);
    }

    public List<User> getAllUser(){
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        return db.queryView(q, User.class);
    }

    public void deleteUser(User u) {
        db.delete(u);
    }

}

