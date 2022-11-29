package com.example.PostmanTests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
  private List<Item> items;
  private int totalPrice;
  private int numberOfItems;
  private String preferedPayment;

  public int getTotalPrice() {
    return items.stream().map(i -> i.getPricePerUnit() * i.getAmount())
                     .reduce(0, Integer::sum);
  }

  public int getNumberOfItems() {
    return items.size();
  }

  public void setPreferedPayment(String preferedPayment) {
    this.preferedPayment = preferedPayment;
  }
}