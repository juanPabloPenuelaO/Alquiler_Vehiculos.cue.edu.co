package Servlets;

import Service.userService;
import Service.userServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import Service.LoginCookie.LoginServiceCookieImpl;
import Service.LoginSession.LoginServiceSession;
import Service.LoginSession.LoginServiceSessionImpl;
import model.user;
import repository.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject LoginServiceSession auth;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        userService service = new userServiceImpl((Repository<user>) conn);

        Optional<String> username = auth.getUserName(req);
        if(username.isPresent()) {

            HttpSession session = req.getSession();
            session.invalidate();
            Cookie usernameCookie = new Cookie("username", "");
            usernameCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
