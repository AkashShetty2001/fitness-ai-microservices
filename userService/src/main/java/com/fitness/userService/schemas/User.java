package com.fitness.userService.schemas;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String emailId;

    @Column(nullable = false)

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

}
