package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private boolean active;
}
