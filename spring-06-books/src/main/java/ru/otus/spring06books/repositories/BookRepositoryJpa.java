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

    /**
     * Метод createBook создает новую книгу в библиотеке
     * <p>
     * Метод find осуществляет поиск и загрузку сущности по id
     * Метод persist кладет сущность в БД, при этом эта сущность должна быть без id
     * Поля сущности Book должны иметь в аннотации @ManyToOne значение cascade: @ManyToOne(cascade = CascadeType.ALL)
     * Метод выполняет поиск автора и жанра - если они уже есть, то добавляются в поля книги,
     * что обеспечивает исключение дублирования авторов и жанров в справочниках библиотеки
     *
     * @param book
     * @return
     */
    @Override
    public long createBook(Book book) {
        List<Author> authorList = getAuthorList(book);
        if (authorList.isEmpty()) {
            entityManager.persist(book.getAuthor());
        } else {
            book.setAuthor(authorList.get(0));
        }
        List<Genre> genreList = getGenreList(book);
        if (genreList.isEmpty()) {
            entityManager.persist(book.getGenre());
        } else {
            book.setGenre(genreList.get(0));
        }
        if (getIdByBook(book) == 0) {
            entityManager.persist(book);
        } else {
            book = entityManager.find(Book.class, getIdByBook(book));
        }
        return book.getId();
    }

    /**
     * Метод updateBookById обновляет сведения о книге в библиотеке
     * Первоначально проверяется автор книги по полному имени - есть ли такой в справочнике авторов,
     * если есть то получаем его id, если нет - то добавляем.
     * Аналогично производится по жанру.
     * Это исключает дублирование в справочниках авторов и жанров
     * После чего производится update книги
     *
     * @param id
     * @param book
     * @return
     */
    @Override
    public boolean updateBookById(long id, Book book) {
        List<Author> authorList = getAuthorList(book);
        if (authorList.isEmpty()) {
            entityManager.persist(book.getAuthor());
        } else {
            book.setAuthor(authorList.get(0));
        }
        List<Genre> genreList = getGenreList(book);
        if (genreList.isEmpty()) {
            entityManager.persist(book.getGenre());
        } else {
            book.setGenre(genreList.get(0));
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

    /**
     * Метод deleteBookById удаляет сведения о книге из библиотеки
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBookById(long id) {
        Query query = entityManager.createQuery("delete " + "from Book b " + "where b.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод getBookById возвращает сведения о книге по ее id
     * Здесь не применяется оптимизация для N+1
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    /**
     * Метод getIdByBook возвращает id переданной ему книги
     *
     * @param book
     * @return
     */
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

    /**
     * Метод getAllBooks возвращает коллекцию из всех книг, имеющиеся в библиотеке
     * Проблема N+1 решается с использованием @NamedEntityGraph для комментария, автора и жанра.
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        EntityGraph<?> bookEntityGraph = entityManager.getEntityGraph("book-author-genre-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", bookEntityGraph);
        return query.getResultList();
    }

    /**
     * Метод getCountOfBooks возвращает число всех книг, имеющихся в библиотеке
     *
     * @return
     */
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
