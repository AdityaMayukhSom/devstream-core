package in.devstream.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.devstream.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
