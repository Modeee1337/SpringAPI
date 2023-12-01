package com.ktu.csgo.insight.authentication;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.ktu.csgo.insight.user.User}
 */
public record UserRoleUpdateDto(@NotNull @NotEmpty String role) implements Serializable {
}