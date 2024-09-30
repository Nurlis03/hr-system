package org.example.hrsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class AuthenticationResponseDto {

    @JsonProperty("access_token")
    private String accessToken;
}
