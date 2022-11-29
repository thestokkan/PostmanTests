package com.example.PostmanTests;


import lombok.*;

@Data
@AllArgsConstructor
public class User {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private PaymentType preferedPaymentType;
}