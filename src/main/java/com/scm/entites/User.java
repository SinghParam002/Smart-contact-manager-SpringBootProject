package com.scm.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class User {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "user is required")
    @Size(min = 3, message = "Min 3 Character required.")
    private String name;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "email invalid email")
    @Email(message = "invalid email format")
    private String email;

    @Size(min = 4, message = "Min 4 Character password required.")
    @NotBlank(message = "Password is required")
    private String password;

    @Column(length = 7000)
    @NotBlank(message = "about is reqired")
    private String about;

    @Column(length = 7000)
    private String profilePic;

    @Size(min = 8, max = 12, message = "Invalid phone number")
    private String PhoneNumber;

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

}