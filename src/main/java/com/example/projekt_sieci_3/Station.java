package com.example.projekt_sieci_3;

public class Station {
    private int id;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String wwwSite;
    private String blood0plus;
    private String blood0minus;
    private String bloodABplus;
    private String bloodABminus;
    private String bloodAplus;
    private String bloodAminus;
    private String bloodBplus;
    private String bloodBminus;

    /**
     * Parametrowy konstruktor.
     *
     * @param id id stacji
     * @param name nazwa
     * @param address adres
     * @param city miasto
     * @param phoneNumber numer telefonu
     * @param wwwSite strona internetowa
     */
    public Station(int id, String name, String address, String city, String phoneNumber, String wwwSite) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.wwwSite = wwwSite;
    }

    /**
     * Parametrowy konstruktor.
     *
     * @param id id stacji
     * @param blood0plus stan krwi 0+
     * @param blood0minus stan krwi 0-
     * @param bloodABplus stan krwi AB+
     * @param bloodABminus stan krwi AB-
     * @param bloodAplus stan krwi A+
     * @param bloodAminus stan krwi A-
     * @param bloodBplus stan krwi B+
     * @param bloodBminus stan krwi B-
     */
    public Station(int id, String blood0plus, String blood0minus, String bloodABplus, String bloodABminus, String bloodAplus, String bloodAminus, String bloodBplus, String bloodBminus) {
        this.id = id;
        this.blood0plus = blood0plus;
        this.blood0minus = blood0minus;
        this.bloodABplus = bloodABplus;
        this.bloodABminus = bloodABminus;
        this.bloodAplus = bloodAplus;
        this.bloodAminus = bloodAminus;
        this.bloodBplus = bloodBplus;
        this.bloodBminus = bloodBminus;
    }

    /**
     * Parametrowy konstruktor.
     *
     * @param name nazwa
     * @param address adres
     * @param city miasto
     * @param phoneNumber numer telefonu
     * @param wwwSite strona internetowa
     * @param blood0plus stan krwi 0+
     * @param blood0minus stan krwi 0-
     * @param bloodABplus stan krwi AB+
     * @param bloodABminus stan krwi AB-
     * @param bloodAplus stan krwi A+
     * @param bloodAminus stan krwi A-
     * @param bloodBplus stan krwi B+
     * @param bloodBminus stan krwi B-
     */
    public Station(String name, String address, String city, String phoneNumber, String wwwSite, String blood0plus, String blood0minus, String bloodABplus, String bloodABminus, String bloodAplus, String bloodAminus, String bloodBplus, String bloodBminus) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.wwwSite = wwwSite;
        this.blood0plus = blood0plus;
        this.blood0minus = blood0minus;
        this.bloodABplus = bloodABplus;
        this.bloodABminus = bloodABminus;
        this.bloodAplus = bloodAplus;
        this.bloodAminus = bloodAminus;
        this.bloodBplus = bloodBplus;
        this.bloodBminus = bloodBminus;
    }

    /**
     * Parametrowy konstruktor.
     *
     * @param id id stacji
     * @param name nazwa
     * @param address adres
     * @param city miasto
     * @param phoneNumber numer telefonu
     * @param wwwSite strona internetowa
     * @param blood0plus stan krwi 0+
     * @param blood0minus stan krwi 0-
     * @param bloodABplus stan krwi AB+
     * @param bloodABminus stan krwi AB-
     * @param bloodAplus stan krwi A+
     * @param bloodAminus stan krwi A-
     * @param bloodBplus stan krwi B+
     * @param bloodBminus stan krwi B-
     */
    public Station(int id, String name, String address, String city, String phoneNumber, String wwwSite, String blood0plus, String blood0minus, String bloodABplus, String bloodABminus, String bloodAplus, String bloodAminus, String bloodBplus, String bloodBminus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.wwwSite = wwwSite;
        this.blood0plus = blood0plus;
        this.blood0minus = blood0minus;
        this.bloodABplus = bloodABplus;
        this.bloodABminus = bloodABminus;
        this.bloodAplus = bloodAplus;
        this.bloodAminus = bloodAminus;
        this.bloodBplus = bloodBplus;
        this.bloodBminus = bloodBminus;
    }

    /**
     * Getter dla id stacji.
     *
     * @return id stacji
     */
    public int getId() {
        return id;
    }

    /**
     * Getter dla nazwy stacji.
     *
     * @return nazwa stacji
     */
    public String getName() {
        return name;
    }

    /**
     * Getter dla adresu stacji.
     *
     * @return adres stacji
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter dla miasta.
     *
     * @return miasto
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter dla numeru telefonu.
     *
     * @return numer telefonu
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter dla strony internetowej.
     *
     * @return strona internetowa
     */
    public String getWwwSite() {
        return wwwSite;
    }

    /**
     * Getter dla stanu krwi 0+.
     *
     * @return stan krwi 0+
     */
    public String getBlood0plus() {
        return blood0plus;
    }

    /**
     * Getter dla stanu krwi 0-.
     *
     * @return stan krwi 0-
     */
    public String getBlood0minus() {
        return blood0minus;
    }

    /**
     * Getter dla stanu krwi AB+.
     *
     * @return stan krwi AB+
     */
    public String getBloodABplus() {
        return bloodABplus;
    }

    /**
     * Getter dla stanu krwi AB-.
     *
     * @return stan krwi AB-
     */
    public String getBloodABminus() {
        return bloodABminus;
    }

    /**
     * Getter dla stanu krwi A+.
     *
     * @return stan krwi A+
     */
    public String getBloodAplus() {
        return bloodAplus;
    }

    /**
     * Getter dla stanu krwi A-.
     *
     * @return stan krwi A-
     */
    public String getBloodAminus() {
        return bloodAminus;
    }

    /**
     * Getter dla stanu krwi B+.
     *
     * @return stan krwi B+
     */
    public String getBloodBplus() {
        return bloodBplus;
    }

    /**
     * Getter dla stanu krwi B-.
     *
     * @return stan krwi B-
     */
    public String getBloodBminus() {
        return bloodBminus;
    }
}
