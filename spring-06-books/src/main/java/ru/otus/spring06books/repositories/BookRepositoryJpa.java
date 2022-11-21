package ru.otus.spring06books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Genre;

import javax.persistence.*;
import java.util.List;

/**
 * Класс BookRepositoryJpa реализует CRUD операции для класса Book
 *
 * @see ru.otus.spring06books.entities.Book
 */
@Repository
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long createBook(Book book) {
/*
        Author author = book.getAuthor();
        if (author.getId() <= 0) {
            System.out.println("author.getId()=" + author.getId());
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
        Genre genre = book.getGenre();
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
        } else {
            entityManager.merge(genre);
        }
*/
        if (book.getId() <= 0) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
        return book.getId();
    }

    @Override
    public boolean updateBookById(long id, Book book) {
        Author author = book.getAuthor();
        if (author.getId() <= 0) {
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
        Genre genre = book.getGenre();
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
        } else {
            entityManager.merge(genre);
        }
        Query query = entityManager.createQuery("update Book b " +
                "set b.title = :title, b.author = :author, b.genre = :genre where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteBookById(long id) {
        Query query = entityManager.createQuery("delete " + "from Book b " + "where b.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public long getIdByBook(Book book) {
        TypedQuery<Long> query = entityManager.createQuery("select b.id " + "from Book b, Author a, Genre g " +
                        "where (b.title = :title) and (b.author.fullName = :fullName) and (b.genre.name = :name)",
                Long.class);
        query.setParameter("title", book.getTitle());
        query.setParameter("fullName", book.getAuthor().getFullName());
        query.setParameter("name", book.getGenre().getName());
        List<Long> idBookList = query.getResultList();
        return idBookList.size() == 0 ? 0 : idBookList.get(0);
    }

    @Override
    public List<Book> getAllBooks() {
        EntityGraph<?> bookEntityGraph = entityManager.getEntityGraph("book-author-genre-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", bookEntityGraph);
        return query.getResultList();
    }

    @Override
    public int getCountOfBooks() {
        Long result = entityManager.createQuery("select count(b) from Book b", Long.class).getSingleResult();
        return Math.toIntExact(result);
    }

    private List<Author> getAuthorList(Book book) {
        TypedQuery<Author> authorTypedQuery = entityManager.createQuery("select a from Author a " +
                "where a.fullName = :fullname", Author.class);
        authorTypedQuery.setParameter("fullname", book.getAuthor().getFullName());
        List<Author> authorList = authorTypedQuery.getResultList();
        return authorList;
    }

    private List<Genre> getGenreList(Book book) {
        TypedQuery<Genre> genreTypedQuery = entityManager.createQuery("select g from Genre g where g.name = :name",
                Genre.class);
        genreTypedQuery.setParameter("name", book.getGenre().getName());
        List<Genre> genreList = genreTypedQuery.getResultList();
        return genreList;
    }

}
