package com.app.demoprojection.dtos;

import com.app.demoprojection.validations.Phone;
import com.app.demoprojection.validations.groups.Create;
import com.app.demoprojection.validations.groups.Update;
import jakarta.validation.constraints.*;

public record PersonRequestDto(
        @NotNull(message = "L'id est obligatoire" , groups = Update.class)
        Long id,
      @NotBlank(message = "Le firstName est obligatoire") String firstName,
       @NotBlank(message = "LastName est obligatoire") String lastName,
      @NotBlank(message = "LastName est obligatoire")String profession,
       @Min(value = 1 , message = "superieur ou egal a 1")
       @Max(value = 3 , message = "maximum 3")
       Long addresseId,
       @Phone String phone,
       @Email String email,
      @Min(value = 15 , message = "superieur ou egal a 15")
      @Max(value = 100 , message = "maximum 100")
       int age
) {
}
