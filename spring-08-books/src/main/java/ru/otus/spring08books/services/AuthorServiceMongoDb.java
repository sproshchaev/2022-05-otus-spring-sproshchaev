package ru.otus.spring08books.services;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.repositories.AuthorRepositoryMongoDb;

import java.util.List;
import java.util.Optional;

/**
 * Класс AuthorServiceMongoDb содержит методы для работы с репозиторием авторов библиотеки
 *
 * @see AuthorRepositoryMongoDb
 */
@Service
public class AuthorServiceMongoDb implements AuthorService {
    private final AuthorRepositoryMongoDb authorRepositoryMongoDb;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AuthorServiceMongoDb(AuthorRepositoryMongoDb authorRepositoryMongoDb, MongoTemplate mongoTemplate) {
        this.authorRepositoryMongoDb = authorRepositoryMongoDb;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Метод createNewAuthor создает нового автора в библиотеке (Crud)
     * Метод выполняет проверку на наличие добавляемого автора в таблице author для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данный автор был создан, или же он уже есть
     * в справочнике авторов библиотеки
     * Метод изменяет данные
     *
     * @param fullName
     * @return
     */
    @Override
    public String createNewAuthor(String fullName) {
        List<Author> authorList = authorRepositoryMongoDb.findAllByFullName(fullName);
        if (authorList.size() == 0) {
            Author author = authorRepositoryMongoDb.save(new Author(fullName));
            return "New author (" + author.getId() + ") '" + author.getFullName() + "' has been successfully created!";
        } else {
            return "Author '" + fullName + "' is already in the library, his id =" + authorList.get(0).getId();
        }
    }

    /**
     * Метод getIdByAuthor возвращает информацию в текстовом виде о результатах поиска
     * автора с данным id в библиотеке
     * Метод не изменяет данные
     *
     * @param fullName
     * @return
     */
    @Override
    public String getIdByAuthor(String fullName) {
        List<Author> authorList = authorRepositoryMongoDb.findAllByFullName(fullName);
        return authorList.size() == 0
                ? "Author '" + fullName + "' not found in the library!"
                : "Author '" + fullName + "' has an id=" + authorList.get(0).getId();
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String getAuthorById(String id) {
        Optional<Author> author = authorRepositoryMongoDb.findById(id);
        return author.isEmpty() ? "Author not found!" : author.get().getFullName();
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllAuthors() {
        List<Author> authorList = authorRepositoryMongoDb.findAll();
        String authorsString = "Authors in the library: ";
        for (int i = 0; i < authorList.size(); i++) {
            authorsString = authorsString + " " + authorList.get(i).getFullName() + (i < (authorList.size() - 1) ? ", " : ".");
        }
        return authorList.size() == 0 ? "Authors not found!" : authorsString;
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Обновлению подлежит поле fullName для передаваемого в качестве аргумента id.
     * Перед выполнением запроса на изменение данных об авторе проверяется наличие автора с таким id,
     * если id был найден в таблице author, то изменяется поле fullName, иначе возвращается сообщение,
     * что автор не был обновлен
     * Метод изменяет данные
     *
     * @param id
     * @param fullName
     * @return
     */
    @Override
    public String updateAuthor(String id, String fullName) {
        if (authorRepositoryMongoDb.findById(id).isPresent()) {
            Author newAuthor = authorRepositoryMongoDb.save(new Author(id, fullName));
            return "Information about the author (" + "id=" + newAuthor.getId() + " " + newAuthor.getFullName()
                    + ") has been updated!";
        } else {
            return "Update author error: author id=" + id + " not found!";
        }
    }

    /**
     * Метод deleteAuthorById удаляет данные об авторе в библиотеке (cruD)
     * Перед удалением выполняется проверка существования автора с таким id в таблице author
     * Метод изменяет данные
     * Для реализации метода используется MongoTemplate
     *
     * @param id
     * @return
     */
    @Override
    public String deleteAuthorById(String id) {
        Author authorForDelete = mongoTemplate.findById(id, Author.class);
        if (authorForDelete != null) {
            DeleteResult deleteResult = mongoTemplate.remove(authorForDelete);
            return "Author (id=" + id + " " + authorForDelete.getFullName() + ") removed from the library (deleted "
                    + deleteResult.getDeletedCount() + " entries)";
        } else {
            return "Delete error: author id=" + id + " not found!";
        }
    }

    /**
     * Метод getFirstAuthorByFullName возвращает первого автора из списка
     * авторов с одинаковым значением поля fullName (cRud)
     *
     * @param authorFullName
     * @return
     */
    @Override
    public Author getFirstAuthorByFullName(String authorFullName) {
        List<Author> authorList = authorRepositoryMongoDb.findAllByFullName(authorFullName);
        return (authorList.size() == 0) ? authorRepositoryMongoDb.save(new Author(authorFullName)) : authorList.get(0);
    }
}
