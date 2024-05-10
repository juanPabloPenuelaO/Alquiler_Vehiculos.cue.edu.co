package Servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import LoginService.LoginSessionServlet;
import Service.userService;
import Service.userServiceImpl;
import model.user;
import repository.Repository;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/user", "/user.html"})
public class UserServlet extends HttpServlet {

    @Inject
    LoginSessionServlet auth;

    @Inject
    userService users;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Connection conn = (Connection) req.getAttribute("conn");
        userService service = new userServiceImpl((Repository<user>) conn);

        Optional<String> usernameOptional = auth.getUsername(req);





        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Listado de Vehiculos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de Vehiculos!</h1>");
            if (usernameOptional.isPresent()) {
                out.println("<p> Hola " + usernameOptional.get() + " Bienvenido! </p>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            if (usernameOptional.isPresent()) {
                out.println("<th>precio</th>");
                out.println("<th>Agregar</th>");
            }
            out.println("</tr>");
/*
                users.list(v -> {
                out.println("<tr>");
                out.println("<td>" + v.getId() + "</td>");
                out.println("<td>" + v.getName() + "</td>");
                out.println("<td>" + v.getType() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + v.getPrice() + "</td>");
                    out.println("<td><a href=\""
                            + req.getContextPath()
                            + "/agregar-carro?id=" + v.getId()
                            + "\"> agregar al carro </a></td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");
*/
        }
    }
}
