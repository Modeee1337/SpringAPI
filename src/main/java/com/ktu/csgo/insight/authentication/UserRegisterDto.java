package com.ktu.csgo.insight.authentication;

import java.io.Serializable;

public record UserRegisterDto(String username, String password, String email) implements Serializable {
}