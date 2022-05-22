package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.BookNameAlreadyExistsException;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.BookService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ShippingService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class BookshopDeleteBookControllerTest {

    @BeforeEach
    @DisplayName("Verify if BookServiceRepository path is correct created and database is initialized")
    public void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-books_database";
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
        UserService.initDatabase();
        BookService.initDatabase();
        ShippingService.initDatabase();
    }

    @AfterAll
    static void afterAll() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @AfterEach
    @DisplayName("Verify if User database is closed after every test")
    public void tearDown() {
        UserService.getDatabase().close();
        BookService.getBookRepository().close();
        ShippingService.getShippingRepository().close();
    }

    @Start
    public void start(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("FxRobot enters user`s data and verify delete book functionality")
    public void testDeleteBookIsSuccessfulForBookShop(@NotNull FxRobot robot) throws UsernameAlreadyExistsException, FieldNotCompletedException, BookNameAlreadyExistsException {
        UserService.addUser("username", "parola", "Bookshop", "123456779", "Brasov", "Maria");
        assertThat(UserService.getUserRepository().size()).isEqualTo(1);

        BookService.addBook("Mara", "Slavici", "Literatura", "Junimea", 123);
        BookService.addBook("Luceafarul", "Eminescu", "Literatura", "Junimea", 1234);
        assertThat(BookService.getBookRepository().size()).isEqualTo(2);

        robot.clickOn("#usernameField");
        robot.write("username");
        robot.clickOn("#passwordField");
        robot.write("parola");
        robot.clickOn("#role");
        robot.clickOn("Bookshop");

        robot.clickOn("#logInButton");
        robot.clickOn("#deleteBookButton");

        robot.clickOn("#deleteBookButton");
        org.testfx.assertions.api.Assertions.assertThat(robot.lookup("#deleteBookMessage").queryText()).hasText("Please complete all fields!");

        robot.clickOn("#bookNameField");
        robot.write("Baltagul");
        robot.clickOn("#deleteBookButton");
        org.testfx.assertions.api.Assertions.assertThat(robot.lookup("#deleteBookMessage").queryText()).hasText("Book with the name Baltagul does not exist!");

        robot.clickOn("#bookNameField");
        robot.write("Luceafarul");
        robot.clickOn("#deleteBookButton");
        org.testfx.assertions.api.Assertions.assertThat(robot.lookup("#deleteBookMessage").queryText()).hasText("Book Luceafarul successfully deleted!");
        assertThat(BookService.getBookRepository().size()).isEqualTo(1);
    }
}