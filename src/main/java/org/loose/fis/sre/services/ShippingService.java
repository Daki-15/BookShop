package org.loose.fis.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.model.Shipping;

import java.util.ArrayList;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ShippingService {
    private static ObjectRepository<Shipping> shippingRepository;
    private static Nitrite database;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("shipping_database.db").toFile())
                .openOrCreate("test3", "test3");

        shippingRepository = database.getRepository(Shipping.class);
    }

    public static void addShipingInfo(String firstname, String lastname, String adress, String postalcode) throws FieldNotCompletedException{
        checkAllFieldsAreCompleted(firstname, lastname, adress, postalcode);
        shippingRepository.insert(new Shipping(firstname, lastname, adress, postalcode));
    }

    private static void checkAllFieldsAreCompleted(String firstnameField, String lastnameField, String adressField, String postalcodeField) throws FieldNotCompletedException {
        if (firstnameField.trim().isEmpty() || lastnameField.trim().isEmpty() || adressField.trim().isEmpty() || postalcodeField.trim().isEmpty())
            throw new FieldNotCompletedException();
    }

    public static ObservableList<Shipping> getShippingList() {
        ObservableList<Shipping> offerObservableArrayList = FXCollections.observableArrayList();
        ArrayList<Shipping> shippingArrayList = new ArrayList<>();

        for (Shipping shipping : shippingRepository.find())
            shippingArrayList.add(shipping);

        offerObservableArrayList.addAll(shippingArrayList);
        return offerObservableArrayList;
    }

    public static ObjectRepository<Shipping> getShippingRepository() {
        return shippingRepository;
    }

    public static Nitrite getDatabase() { return database; }
}
