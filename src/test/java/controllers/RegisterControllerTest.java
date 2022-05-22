package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class RegisterControllerTest {

    @BeforeEach
    @DisplayName("Verify if BookServiceRepository path is correct created and database is initialized")
    public void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-users_database";
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
        System.out.println(FileSystemService.getPathToFile().toFile());
        UserService.initDatabase();
    }

    @AfterAll
    static void afterAll() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @AfterEach
    @DisplayName("Verify if User database is closed after every test")
    public void tearDown() {UserService.getDatabase().close();}


    @Start
    public void start(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("FxRobot enters user`s data and the registration is successfully")
    public void testRegistrationIsSuccessfulForClient(@NotNull FxRobot robot) {
        User user = new User();

        robot.clickOn("#usernameField");
        robot.write("User");
        robot.clickOn("#passwordField");
        robot.write("User");
        robot.clickOn("#nameField");
        robot.write("Ion");
        robot.clickOn("#phoneField");
        robot.write("1234567890");
        robot.clickOn("#addressField");
        robot.write("Timisoara");
        robot.clickOn("#role");
        robot.clickOn("Client");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        Assertions.assertThat(UserService.getUserRepository().size()).isEqualTo(1);

        assertAll("Register fields should be empty after an account was created",
                () -> Assertions.assertThat(user.getUsername()).isEqualTo(null),
                () -> Assertions.assertThat(user.getPassword()).isEqualTo(null),
                () -> Assertions.assertThat(user.getName()).isEqualTo(null),
                () -> Assertions.assertThat(user.getPhoneNumber()).isEqualTo(null),
                () -> Assertions.assertThat(user.getAddress()).isEqualTo(null),
                () -> Assertions.assertThat(user.getRole()).isEqualTo(null));
    }

    @Test
    @DisplayName("FxRobot enters user`s data and the registration is successfully")
    public void testRegistrationIsSuccessfulForBookstore(@NotNull FxRobot robot) {
        User user = new User();

        robot.clickOn("#usernameField");
        robot.write("User");
        robot.clickOn("#passwordField");
        robot.write("User");
        robot.clickOn("#nameField");
        robot.write("Ion");
        robot.clickOn("#phoneField");
        robot.write("1234567890");
        robot.clickOn("#addressField");
        robot.write("Timisoara");
        robot.clickOn("#role");
        robot.clickOn("Bookshop");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        Assertions.assertThat(UserService.getUserRepository().size()).isEqualTo(1);

        assertAll("Register fields should be empty after an account was created",
                () -> Assertions.assertThat(user.getUsername()).isEqualTo(null),
                () -> Assertions.assertThat(user.getPassword()).isEqualTo(null),
                () -> Assertions.assertThat(user.getName()).isEqualTo(null),
                () -> Assertions.assertThat(user.getPhoneNumber()).isEqualTo(null),
                () -> Assertions.assertThat(user.getAddress()).isEqualTo(null),
                () -> Assertions.assertThat(user.getRole()).isEqualTo(null));
    }

    @Test
    @DisplayName("FxRobot enters user`s data and the registration fails because username field is not completed")
    public void testRegistrationUsernameIsEmpty(@NotNull FxRobot robot) {

        robot.clickOn("#usernameField");
        robot.clickOn("#passwordField");
        robot.write("User");
        robot.clickOn("#nameField");
        robot.write("Ion");
        robot.clickOn("#phoneField");
        robot.write("1234567890");
        robot.clickOn("#addressField");
        robot.write("Timisoara");
        robot.clickOn("#role");
        robot.clickOn("Bookshop");


        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please complete all fields!");
    }
}