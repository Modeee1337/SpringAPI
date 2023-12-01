package com.ktu.csgo.insight.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
public record UserRegisterDto(String username, String password, String email, String role) implements Serializable {
}