package Service.LoginSession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@ApplicationScoped
public class LoginServiceSessionImpl implements LoginServiceSession {
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username!=null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
