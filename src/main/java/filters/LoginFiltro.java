package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.loginImpl.LoginServiceSessionImpl;
import LoginService.LoginSessionServlet;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/users", "/*"})//urls que deseamos aplique el filtro
//si queremos implementar varias pondriamos:
//@WebFilter({"/user", “/vehicles”,”reservations”})


public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LoginSessionServlet service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "No estás autorizado para ingresar a esta página!");
        }
    }
}
