package in.devstream.repository;

import org.springframework.data.repository.Repository;

import in.devstream.model.Role;

public interface RoleRepository extends Repository<Role, String> {

    Role findByRole(String role);
}
