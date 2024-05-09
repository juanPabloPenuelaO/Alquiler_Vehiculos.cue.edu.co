package Service.LoginSession;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginServiceSession {
    Optional<String> getUserName(HttpServletRequest request);
}
