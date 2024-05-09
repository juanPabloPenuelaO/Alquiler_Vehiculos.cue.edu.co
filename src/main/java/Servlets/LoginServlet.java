package Servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.impl.LoginSession.LoginServiceSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})

public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    @Inject
    LoginServiceSession auth;




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> usernameOptional = auth.getUserName(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Hola" + usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola" + usernameOptional.get() + "has iniciado sesion exitosamente! </h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>Cerrar sesion</a></p>");
                out.println("</body>");
                out.println("</html>");

            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp); //Aqui carga el JSP
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession(); //Lo que hace es obtener la sesion actual del cliente. Que ya esta creada cuando el cliente se conecta a la aplicacion y realiza alguna peticion
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html"); //Con el redirect, evitamos que vuelva a cargar la pagina de login
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado" +
                    " para ingresar a esta página");
        }


    }


}
