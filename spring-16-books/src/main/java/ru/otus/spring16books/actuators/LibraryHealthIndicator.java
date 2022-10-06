package ru.otus.spring16books.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring16books.services.LibraryService;

/**
 * Класс LibraryHealthIndicator контролирует состояние Приложения "Библиотека"
 */
@Component
public class LibraryHealthIndicator implements HealthIndicator {

    private final int BOOK_COUNT_MIN = 1;
    private final int AUTHOR_COUNT_MIN = 1;
    private final int GENRE_COUNT_MIN = 1;
    private final int COMMENT_COUNT_MIN = 1;

    private final LibraryService libraryService;

    public LibraryHealthIndicator(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    /**
     * Метод health() анализирует возвращаемую строку aboutLibrary() на предмет наличия в ней четырех целочисленных
     * значений больше 0
     * Пример: "Welcome to our library! We have more than 4 books by 3 authors and 9 genres in our library. Our readers
     * have left more than 4 comments on the books!"
     *
     * @return
     */
    @Override
    public Health health() {
        if (getHealthStatusApp(libraryService.aboutLibrary())) {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "App is working")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "App is not working")
                    .build();
        }
    }

    private boolean getHealthStatusApp(String controlString) {
        String[] arrayString = controlString.split(" ");
        int bookCount = Integer.valueOf(arrayString[8]);
        int authorCount = Integer.valueOf(arrayString[11]);
        int genreCount = Integer.valueOf(arrayString[14]);
        int commentCount = Integer.valueOf(arrayString[25]);
        if ((bookCount > BOOK_COUNT_MIN) && (authorCount > AUTHOR_COUNT_MIN) && (genreCount > GENRE_COUNT_MIN)
                && (commentCount > COMMENT_COUNT_MIN)) {
            return true;
        } else {
            return false;
        }
    }
}
