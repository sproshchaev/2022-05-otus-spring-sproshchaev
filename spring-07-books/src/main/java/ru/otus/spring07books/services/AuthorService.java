package ru.otus.spring07books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring07books.entities.Author;
import ru.otus.spring07books.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс AuthorService содержит методы сервиса работы с репозиторием Авторов библиотеки
 */
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Метод createNewAuthor (Crud)
     * Метод выполняет проверку на наличие добавляемого автора в таблице authors для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данный автор был создан, или же он уже есть
     * в справочнике авторов библиотеки
     * Аннотация @Transactional - метод изменяет данные
     *
     * @param fullName
     * @return
     */
    @Transactional
    public String createNewAuthor(String fullName) {
        List<Long> listIdAuthor = authorRepository.getAuthorIdByFullName(fullName);
        long authorId = listIdAuthor.size() == 0 ? 0 : listIdAuthor.get(0);
        if (authorId == 0) {
            authorId = authorRepository.save(new Author(fullName)).getId();
            return "New author (" + authorId + ") " + fullName + " has been successfully created!";
        } else {
            return "Author " + fullName + " is already in the library, his id =" + authorId;
        }
    }

    /**
     * Метод getIdInfoByAuthor возвращает информацию в текстовом виде о результатах поиска
     * автора с данным id в библиотеке
     * Метод не изменяет данные
     *
     * @param fullName
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByAuthor(String fullName) {
        List<Long> listIdAuthor = authorRepository.getAuthorIdByFullName(fullName);
        return listIdAuthor.size() == 0 ? "Author '" + fullName + "' not found in the library!" : "Author '" + fullName + "' has an id=" + listIdAuthor.get(0);
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.isEmpty() ? "Author not found!" : author.get().getFullName();
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
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
    @Transactional
    public String updateAuthor(long id, String fullName) {
        if (authorRepository.findById(id).isPresent()) {
            int countAuthorsUpdated = authorRepository.updateAuthor(id, fullName);
            return "Information about the author (" + "id=" + id + " " + fullName + ") has been updated for " + countAuthorsUpdated + " records!";
        } else {
            return "Update author error: author id=" + id + " not found!";
        }
    }

    /**
     * Метод deleteAuthorById удаляет данные об авторе в библиотеке (cruD)
     * Перед удалением выполняется проверка существования автора с таким id в таблице author
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional
    public String deleteAuthorById(long id) {
        Optional<Author> authorForDelete = authorRepository.findById(id);
        if (authorForDelete.isPresent()) {
            authorRepository.deleteById(id);
            return "Author (id=" + id + " " + authorForDelete.get().getFullName() + ") removed from the library";
        } else {
            return "Delete error: author id=" + id + " not found!";
        }
    }
}
