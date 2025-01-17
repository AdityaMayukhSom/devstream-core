package in.devstream.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import in.devstream.repository.model.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

    Optional<Role> findByRole(String role);
}
