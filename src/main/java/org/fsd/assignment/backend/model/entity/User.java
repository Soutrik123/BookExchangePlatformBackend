package org.fsd.assignment.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column("id")
    private Long id;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("name")
    private String name;

    @Column("location")
    private String location;

    @Column("role")
    private String role;
}
