package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
         database = Nitrite.builder()
                .filePath(getPathToFile("book-shop.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String phoneNumber, String address, String name) throws UsernameAlreadyExistsException, FieldNotCompletedException {
        checkUserDoesNotAlreadyExist(username);
        checkAllFieldsAreCompleted(username, password, role, phoneNumber, address, name);
        userRepository.insert(new User(username, encodePassword(username, password), role, phoneNumber, address, name));
    }

    public static void checkAllFieldsAreCompleted(String username, String password, String role, String phoneNumber,  String address, String name) throws FieldNotCompletedException {

        if (username.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty() || phoneNumber.trim().isEmpty() ||  address.trim().isEmpty()  || name.trim().isEmpty() )
            throw new FieldNotCompletedException();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static int checkForUser(String username, String passwordb, String role){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                if (checkPassword(user.getPassword(), passwordb, user.getUsername()) && role.equals(user.getRole())) return 0; //user found
                else return 1; //invalid passwd or role
            }
        }
        return 2; // user not found
    }

    public static boolean checkPassword(String pass_from_data, String pass_from_form, String username) {
        return Objects.equals(pass_from_data, encodePassword(username, pass_from_form));
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
