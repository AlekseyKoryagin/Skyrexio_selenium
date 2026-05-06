package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PagesUrl {
    LOGIN_PAGE("login");

    private final String loginPage;
}
