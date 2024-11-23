package in.devstream.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "roles",
        indexes = {
            @Index(columnList = "role", unique = true)})
public class Role {

    public static final Role ADMIN = new Role("ADMIN");
    public static final Role USER = new Role("USER");
    public static final Role VIEWER = new Role();

    // https://docs.jboss.org/hibernate/orm/3.5/reference/en/html/persistent-classes.html
    // All persistent classes must have a default constructor (which can be non-public) 
    // so that Hibernate can instantiate them using Constructor.newInstance().
    private Role() {
        this("VIEWER");
    }

    private Role(String role) {
        this.role = role;
    }

    @Id
    @NotBlank
    @NotEmpty
    @NonNull
    @Column(unique = true, nullable = false, updatable = false)
    private final String role;
}
