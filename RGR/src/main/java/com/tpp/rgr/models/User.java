package com.tpp.rgr.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Унікальне ім'я користувача

    @Column(nullable = false)
    private String password; // Пароль користувача

    @Column(nullable = false)
    private String role; // Роль користувача (наприклад, ADMIN або USER)

    /**
     * Геттер для імені користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Геттер для пароля.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Геттер для ролі користувача.
     */
    public String getRole() {
        return role;
    }

    /**
     * Сеттер для імені користувача.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Сеттер для пароля.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Сеттер для ролі користувача.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
