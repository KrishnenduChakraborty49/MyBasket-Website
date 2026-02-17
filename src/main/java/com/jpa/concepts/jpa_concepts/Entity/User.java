package com.jpa.concepts.jpa_concepts.Entity;

import com.jpa.concepts.jpa_concepts.security.ROLE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "my-basket-users")
@SQLDelete(sql=" Update 'my-basket-users' SET Deleted = true WHERE jpa_user_id=? ")
@SQLRestriction("deleted = false")
@Getter
@Setter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="jpa_user_id")
    private Integer userId;
    private String name;
    @Column(length = 100, nullable = false)
    private String email;


    private String password;
    private String userImageUrl;
    private boolean enable=true;
    @Enumerated(EnumType.STRING)
    private ROLE role=ROLE.ROLE_NORMAL;
    @OneToMany(
            mappedBy="user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();



}
