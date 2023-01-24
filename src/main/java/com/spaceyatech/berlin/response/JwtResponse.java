package com.spaceyatech.berlin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class JwtResponse {

    private String access_token;
    private String refresh_token;
    private String type = "Bearer";
    private UUID id;
    private String username;
    private String email;
    private List<String> roles;
}
