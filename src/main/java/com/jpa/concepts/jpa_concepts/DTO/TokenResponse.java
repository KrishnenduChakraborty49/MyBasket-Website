package com.jpa.concepts.jpa_concepts.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    public String accessToken;
    public String refreshToken;
    public UserDto user;


}
