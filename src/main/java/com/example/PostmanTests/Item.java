package com.example.PostmanTests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
  private int id;
  private int amount;
  private String name;
  private String brand;
  private int pricePerUnit;
}