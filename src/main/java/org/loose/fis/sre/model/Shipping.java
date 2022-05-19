package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import java.util.Objects;

public class Shipping {
    @Id
    private String firstname;
    private String lastname;
    private String adress;
    private String postalcode;

    public Shipping(String firstname, String lastname, String adress, String postalcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.postalcode = postalcode;
    }

    public Shipping() {
    }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getAdress() { return adress; }

    public void setAdress(String adress) { this.adress = adress; }

    public String getPostalcode() { return postalcode; }

    public void setPostalcode(String postalcode) { this.postalcode = postalcode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipping shipping = (Shipping) o;
        return Objects.equals(firstname, shipping.firstname) && Objects.equals(lastname, shipping.lastname) && Objects.equals(adress, shipping.adress) && Objects.equals(postalcode, shipping.postalcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, adress, postalcode);
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                ", postalcode='" + postalcode + '\'' +
                '}';
    }
}
