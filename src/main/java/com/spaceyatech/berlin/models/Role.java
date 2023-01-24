package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spaceyatech.berlin.enums.RoleName;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Role")
public class Role extends BaseEntity{
    //id from base entity
    @Column(name="role_name", length=45)
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @Column(name ="role_col",length=45)
    private String rolecol;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "role")
    @JsonIgnore
    private Set<User> user = new HashSet<>();


}
