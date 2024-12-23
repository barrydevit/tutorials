package com.app.demoprojection.dtos;
public record PersonResponseDto (
        Long id,
     String firstName,
     String lastName,
      String profession,
      int age,
     boolean isMajeur , AddresseResponseDto addresse){

}
