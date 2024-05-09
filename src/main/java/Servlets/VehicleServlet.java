package Servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.VehicleDTO;
import model.Vehicle;
import services.impl.LoginSession.LoginServiceSession;
import services.impl.LoginSession.LoginServiceSessionImpl;
import services.impl.Vehicle.VehicleService;
import services.impl.Vehicle.VehicleServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/vehicle", "/vehicle.html"})
public class VehicleServlet extends HttpServlet {

    @Inject
    LoginServiceSession auth;

    @Inject
    VehicleService vehicles;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        Optional<String> usernameOptional = auth.getUserName(req);





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

            vehicles.forEach(v -> {
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

        }
    }
}
