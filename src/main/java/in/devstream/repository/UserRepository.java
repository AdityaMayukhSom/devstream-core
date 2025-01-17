package in.devstream.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import in.devstream.repository.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);
}
