package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PagesUrl {
    LOGIN_PAGE("login"),
    HOME_PAGE("home"),
    TERMINAL_DIARY_PAGE("manual-trading/trading-diary");

    private final String loginPage;
}
