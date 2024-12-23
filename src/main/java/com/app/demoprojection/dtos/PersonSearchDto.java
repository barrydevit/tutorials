package com.app.demoprojection.dtos;

import jakarta.annotation.Nullable;

public record PersonSearchDto(@Nullable String firstName,@Nullable String lastName
        , @Nullable String addresse , @Nullable  String profession , @Nullable Integer age) {
}
