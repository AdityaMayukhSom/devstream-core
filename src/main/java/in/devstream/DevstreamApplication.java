package in.devstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import in.devstream.model.Role;
import in.devstream.repository.RoleRepository;

@SpringBootApplication
public class DevstreamApplication {

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DevstreamApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            this.roleRepository.save(Role.ADMIN);
            this.roleRepository.save(Role.USER);
            this.roleRepository.save(Role.VIEWER);
        };
    }
}
