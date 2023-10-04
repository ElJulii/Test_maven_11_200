import java.util.List;

public interface UserRepository <T> {
List<T> find_password_mail(String mail, String password);
}
