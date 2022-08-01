package ru.otus.spring06books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Класс BookRepositoryJpa
 */
@Repository
public class BookRepositoryJpa implements BookRepository {

    /**
     * Внедрение зависимости EntityManager (отвечает за все сущности)
     */
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    /**
     * Конструктор класса
     *
     * @param entityManager
     * @param genreRepositoryJpa
     * @param authorRepositoryJpa
     */
    @Autowired
    public BookRepositoryJpa(EntityManager entityManager, AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa) {
        this.entityManager = entityManager;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
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
        long idAuthor = authorRepositoryJpa.getIdByAuthor(book.getAuthor());
        if (idAuthor != 0) {
            book.setAuthor(entityManager.find(Author.class, idAuthor));
        }
        long idGenre = genreRepositoryJpa.getIdByGenre(book.getGenre());
        if (idGenre != 0) {
            book.setGenre(entityManager.find(Genre.class, idGenre));
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
        entityManager.find(Book.class, id);
        long authorId = authorRepositoryJpa.getIdByAuthor(book.getAuthor());
        if (authorId == 0) {
            entityManager.persist(book.getAuthor());
        } else {
            book.setAuthor(authorRepositoryJpa.getAuthorById(authorId));
        }
        long genreId = genreRepositoryJpa.getIdByGenre(book.getGenre());
        if (genreId == 0) {
            entityManager.persist(book.getGenre());
        } else {
            book.setGenre(genreRepositoryJpa.getGenreById(genreId));
        }
        Query query = entityManager.createQuery("update Book b " +
                "set b.title = :title, b.author = :author, b.genre = :genre " +
                "where b.id = :id");
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
        Query query = entityManager.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    /**
     * Метод getBookById возвращает сведения о книге по ее id
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
        TypedQuery<Book> query = entityManager.createQuery("select b " +
                        "from Book b, Author a, Genre g " +
                        "where (b.title = :title) and (b.author.fullName = :fullName) and (b.genre.name = :name)",
                Book.class);
        query.setParameter("title", book.getTitle());
        query.setParameter("fullName", book.getAuthor().getFullName());
        query.setParameter("name", book.getGenre().getName());
        List<Book> bookList = query.getResultList();
        return bookList.size() == 0 ? 0 : bookList.get(0).getId();
    }

    /**
     * Метод getAllBooks возвращает коллекцию из всех книг, имеющиеся в библиотеке
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        return null;
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
}
