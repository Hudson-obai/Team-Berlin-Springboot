package com.spaceyatech.berlin.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name ="Users")
public class User extends BaseEntity{

    @Column(name="user_name",length=50)
    private String username;
    @Column(name="email",length=50)
    private String email;
    @Column(name="password",length=60)
    private String password;
    @Column(name="phone_number",length=45)
    private String phone_number;
    @Column(name="users_col",length=45)
    private String userscol;
    @Column(name="verification_code",length=45)
    private String verification_code;
    @Column(name="date_created",length=6)
    private Timestamp date_created;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "UsersHasRole",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> role = new HashSet<>();


}
