package com.abhishek.loginapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("first_name")
        String first_name,

        @JsonProperty("last_name")
                String last_name,

        @JsonProperty("email")
        String email
) {
}
