package dev.budioct.blog.services;

import dev.budioct.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
