import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.userDTO;
import model.user;
import repository.Repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class newServlet extends HttpServlet implements Repository<userDTO> {

    private Repository<user> userRepository;

    public newServlet(Repository<user> userRepository) {
        this.userRepository = userRepository;
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "1");
        LocalTime hora = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>La hora actualizada</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>La hora actualizada!</h1>");
            out.println("<h3>" + hora.format(df) + "</h3>");
            out.println(" </body>");
            out.println("</html>");
        }
    }


    @Override
    public void addUser(userDTO userDTO) {

    }

    @Override
    public List<userDTO> list() {
        return null;
    }

    @Override
    public userDTO byId(int id) {
        return null;
    }

    @Override
    public void save(userDTO userDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
