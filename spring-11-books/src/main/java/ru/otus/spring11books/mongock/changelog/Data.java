package ru.otus.spring11books.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Comment;
import ru.otus.spring08books.repositories.AuthorRepository;
import ru.otus.spring08books.repositories.BookRepository;
import ru.otus.spring08books.repositories.CommentRepository;
import ru.otus.spring08books.repositories.GenreRepository;
import ru.otus.spring11books.domain.Comment;

/**
 * Запись данных в коллекции:
 * в случае, если в сущности у поля указана аннотация @Field(name = "Имя_поля"), то при вызове
 * метода append необходимо использовать "Имя_поля", пример: new Document().append("fullname", "John Bunyan")
 * @see ru.otus.spring08books.entities.Author
 */
@ChangeLog(order = "002")
public class Data {

    @ChangeSet(order = "001", id = "insertAuthorJohnBunyan", author = "sproshchaev")
    public void insertAuthorJohnBunyan(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("author");
        Document doc = new Document().append("fullname", "John Bunyan");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "002", id = "insertAuthorDanielDefoe", author = "sproshchaev")
    public void insertAuthorDanielDefoe(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("author");
        Document doc = new Document().append("fullname", "Daniel Defoe");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertAuthorGianniRodari", author = "sproshchaev")
    public void insertAuthorGianniRodari(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("author");
        Document doc = new Document().append("fullname", "Gianni Rodari");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "004", id = "insertGenreHistory", author = "sproshchaev")
    public void insertGenreHistory(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("genre");
        Document doc = new Document().append("name", "History");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "005", id = "insertGenreClassic", author = "sproshchaev")
    public void insertGenreClassic(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("genre");
        Document doc = new Document().append("name", "Classic");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "006", id = "insertGenreFantasy", author = "sproshchaev")
    public void insertGenreFantasy(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("genre");
        Document doc = new Document().append("name", "Fantasy");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "007", id = "insertGenreAutobiography", author = "sproshchaev")
    public void insertGenreAutobiography(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("genre");
        Document doc = new Document().append("name", "Autobiography");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "008", id = "insertGenreFiction", author = "sproshchaev")
    public void insertGenreFiction(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("genre");
        Document doc = new Document().append("name", "Fiction");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "009", id = "insertBook1", author = "sproshchaev")
    public void insertBook1(MongoDatabase db, BookRepository bookRepository,
                            AuthorRepository authorRepository,
                            GenreRepository genreRepository) {
        Book book = new Book("The Pilgrim’s Progress",
                authorRepository.findAllByFullName("John Bunyan").get(0),
                genreRepository.findAllGenreByName("History").get(0));
        bookRepository.save(book);
    }

    @ChangeSet(order = "010", id = "insertBook2", author = "sproshchaev")
    public void insertBook2(MongoDatabase db, BookRepository bookRepository,
                            AuthorRepository authorRepository,
                            GenreRepository genreRepository) {
        Book book = new Book("Robinson Crusoe",
                authorRepository.findAllByFullName("Daniel Defoe").get(0),
                genreRepository.findAllGenreByName("Classic").get(0));
        bookRepository.save(book);
    }

    @ChangeSet(order = "011", id = "insertBook3", author = "sproshchaev")
    public void insertBook3(MongoDatabase db, BookRepository bookRepository,
                            AuthorRepository authorRepository,
                            GenreRepository genreRepository) {
        Book book = new Book("The Holy War",
                authorRepository.findAllByFullName("John Bunyan").get(0),
                genreRepository.findAllGenreByName("History").get(0));
        bookRepository.save(book);
    }

    @ChangeSet(order = "012", id = "insertBook4", author = "sproshchaev")
    public void insertBook4(MongoDatabase db, BookRepository bookRepository,
                            AuthorRepository authorRepository,
                            GenreRepository genreRepository) {
        Book book = new Book("The Farther Adventures of Robinson Crusoe",
                authorRepository.findAllByFullName("Daniel Defoe").get(0),
                genreRepository.findAllGenreByName("Classic").get(0));
        bookRepository.save(book);
    }

    @ChangeSet(order = "013", id = "insertComment1", author = "sproshchaev")
    public void insertComment1(MongoDatabase db, BookRepository bookRepository,
                               AuthorRepository authorRepository,
                               GenreRepository genreRepository,
                               CommentRepository commentRepository) {
        String bookTitle = "The Pilgrim’s Progress";
        String authorFullName = "John Bunyan";
        String genreName = "History";
        String commentText = "The Pilgrims Progress — is a very interesting book!";
        Comment comment = new Comment(commentText, bookRepository.findAllByTitleAndAuthorAndGenre(bookTitle,
                authorRepository.findAllByFullName(authorFullName).get(0),
                genreRepository.findAllGenreByName(genreName).get(0)).get(0));
        commentRepository.save(comment);
    }

    @ChangeSet(order = "014", id = "insertComment2", author = "sproshchaev")
    public void insertComment2(MongoDatabase db, BookRepository bookRepository,
                               AuthorRepository authorRepository,
                               GenreRepository genreRepository,
                               CommentRepository commentRepository) {
        String bookTitle = "Robinson Crusoe";
        String authorFullName = "Daniel Defoe";
        String genreName = "Classic";
        String commentText = "Robinson Crusoe — is a very interesting book!";
        Comment comment = new Comment(commentText, bookRepository.findAllByTitleAndAuthorAndGenre(bookTitle,
                authorRepository.findAllByFullName(authorFullName).get(0),
                genreRepository.findAllGenreByName(genreName).get(0)).get(0));
        commentRepository.save(comment);
    }

    @ChangeSet(order = "015", id = "insertComment3", author = "sproshchaev")
    public void insertComment3(MongoDatabase db, BookRepository bookRepository,
                               AuthorRepository authorRepository,
                               GenreRepository genreRepository,
                               CommentRepository commentRepository) {
        String bookTitle = "The Holy War";
        String authorFullName = "John Bunyan";
        String genreName = "History";
        String commentText = "The Holy War — is a very interesting book!";
        Comment comment = new Comment(commentText, bookRepository.findAllByTitleAndAuthorAndGenre(bookTitle,
                authorRepository.findAllByFullName(authorFullName).get(0),
                genreRepository.findAllGenreByName(genreName).get(0)).get(0));
        commentRepository.save(comment);
    }

    @ChangeSet(order = "016", id = "insertComment4", author = "sproshchaev")
    public void insertComment4(MongoDatabase db, BookRepository bookRepository,
                               AuthorRepository authorRepository,
                               GenreRepository genreRepository,
                               CommentRepository commentRepository) {
        String bookTitle = "The Farther Adventures of Robinson Crusoe";
        String authorFullName = "Daniel Defoe";
        String genreName = "Classic";
        String commentText = "The Farther Adventures of Robinson Crusoe — is a very interesting book!";
        Comment comment = new Comment(commentText, bookRepository.findAllByTitleAndAuthorAndGenre(bookTitle,
                authorRepository.findAllByFullName(authorFullName).get(0),
                genreRepository.findAllGenreByName(genreName).get(0)).get(0));
        commentRepository.save(comment);
    }
}