package Service.LoginCookie;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;


@ApplicationScoped
public class LoginServiceCookieImpl implements LoginServiceCookie {

    @Override
    public Optional<String> getUserName(HttpServletRequest req) {

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() :new Cookie[0]; //Arreglo del tipo cookie
        return  Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
