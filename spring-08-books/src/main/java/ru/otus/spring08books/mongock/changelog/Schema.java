package ru.otus.spring08books.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class Schema {

    @ChangeSet(order = "001", id = "dropDb", author = "sproshchaev", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "createAuthorCollection", author = "sproshchaev")
    public void createAuthorCollection(MongoDatabase db) {
        db.createCollection("author");
    }

    @ChangeSet(order = "003", id = "createGenreCollection", author = "sproshchaev")
    public void createGenreCollection(MongoDatabase db) {
        db.createCollection("genre");
    }

    @ChangeSet(order = "004", id = "createBookCollection", author = "sproshchaev")
    public void createBookCollection(MongoDatabase db) {
        db.createCollection("book");
    }

    @ChangeSet(order = "005", id = "createCommentCollection", author = "sproshchaev")
    public void createCommentCollection(MongoDatabase db) {
        db.createCollection("comment");
    }

}
