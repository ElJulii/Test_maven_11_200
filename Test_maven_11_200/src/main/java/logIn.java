import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/log_in")
public class logIn extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-200";
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/log_in.html").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            UserRepository userRepository =new UserRepositoryJdbcImpl(connection);
            String mail = request.getParameter("login");
            String password = request.getParameter("password");
            List<User> password_mail = userRepository.find_password_mail(mail, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
