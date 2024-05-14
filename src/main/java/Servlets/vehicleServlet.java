package Servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import LoginService.LoginSessionServlet;
import Service.vehicleService;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/vehicles", "/vehicle.html"})
public class vehicleServlet extends HttpServlet {

    @Inject
    LoginSessionServlet auth;

    @Inject
    vehicleService vehicles;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Listado de vehiculos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de vehiculos!</h1>");

            if (usernameOptional.isPresent()) {
                out.println("<p> Hola " + usernameOptional.get() + " Bienvenido! </p>");
            }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>tipo de Vehiculo</th>");
            out.println("<th>modelo</th>");
            out.println("<th>año del modelo</th>");
            out.println("<th>marca</th>");
            out.println("<th>precio Por día</th>");

            if (usernameOptional.isPresent()) {
                out.println("<th>Placa</th>");
                out.println("<th>Disponibilidad</th>");
            }

            out.println("</tr>");

            vehicles.list().forEach(vehicles -> {
                out.println("<tr>");
                out.println("<td>" + vehicles.getId() + "</td>");
                out.println("<td>" + vehicles.getType() + "</td>");
                out.println("<td>" + vehicles.getModelYear() + "</td>");
                out.println("<td>" + vehicles.getBrand() + "</td>");
                out.println("<td>" + vehicles.getPriceDay() + "</td>");

                if (usernameOptional.isPresent()) {
                    out.println("<td>" + vehicles.getPlate() + "</td>");
                    out.println("<td>" + vehicles.getAvailability() + "</td>");
                }

                out.println("</tr>");
            });

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
