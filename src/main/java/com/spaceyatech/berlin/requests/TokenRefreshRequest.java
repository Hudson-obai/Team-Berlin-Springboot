package com.spaceyatech.berlin.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;

}
