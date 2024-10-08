package in.devstream.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.devstream.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
