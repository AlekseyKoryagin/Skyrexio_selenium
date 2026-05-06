package users;

import utils.PropertyReader;

public class UserFactory {
    public static String setProperty(String property) {
        return PropertyReader.getProperty(property);
    }

    public static User withStandardPermission() {
        return new User(setProperty("Skyrexio_selenium.email"), setProperty("Skyrexio_selenium.password"));
    }
}
