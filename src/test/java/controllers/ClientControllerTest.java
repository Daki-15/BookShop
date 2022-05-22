package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.*;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class ClientControllerTest {

    @BeforeEach
    @DisplayName("Verify if BookServiceRepository path is correct created and database is initialized")
    public void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-books_database";
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
        UserService.initDatabase();
        BookService.initDatabase();
        ShippingService.initDatabase();
        HistoryOfOrderService.initDatabase();
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
        HistoryOfOrderService.getBookRepository().close();
    }

    @Start
    public void start(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("FxRobot enters user`s data and the login is successfully for client")
    public void testLoginIsSuccessfulForBookshop(@NotNull FxRobot robot) throws UsernameAlreadyExistsException, FieldNotCompletedException {
        UserService.addUser("username", "parola", "Client", "123456779", "Brasov", "Maria");
        assertThat(UserService.getUserRepository().size()).isEqualTo(1);

        robot.clickOn("#usernameField");
        robot.write("username");
        robot.clickOn("#passwordField");
        robot.write("parola");
        robot.clickOn("#role");
        robot.clickOn("Bookshop");

        robot.clickOn("#logInButton");
    }
}
