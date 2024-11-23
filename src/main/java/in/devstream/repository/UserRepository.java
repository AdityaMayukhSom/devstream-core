package in.devstream.repository;

import org.springframework.data.repository.CrudRepository;

import in.devstream.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByEmail(String email);
}
