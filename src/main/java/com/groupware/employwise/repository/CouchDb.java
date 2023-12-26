package com.groupware.employwise.repository;

import com.groupware.employwise.model.User;
import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDb {
    private static CouchDb instance;
    private final CouchDbConnector db;

    private CouchDb() {
        HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
                .port(5984)
                .username("admin")
                .password("adminroot")
                .build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        db = new StdCouchDbConnector("employees", dbInstance);
        db.createDatabaseIfNotExists();
    }

    public static synchronized CouchDb getInstance() {
        if (instance == null) {
            instance = new CouchDb();
        }
        return instance;
    }

    public User getUser(String id) {
        return db.get(User.class, id);
    }

    public void saveUser(User u) {
        db.create(u);
    }
    public void updateUser(User u) {
        db.update(u);
    }

    public void deleteUser(User u) {
        db.delete(u);
    }

}

