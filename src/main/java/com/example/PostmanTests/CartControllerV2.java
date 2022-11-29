package com.example.PostmanTests;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@RequestMapping("/api/v2")
public class CartControllerV2 {

  private ArrayList<User> users;
  private ArrayList<Item> items;
  private Cart cart = new Cart();

  @Autowired
  public CartControllerV2(ArrayList<User> users, ArrayList<Item> items) {
    this.users = users;
    this.items = items;

    this.items.add(new Item(0, 1, "t-shirt", "dedicated", 34));
    this.items.add(new Item(1, 2, "sweater", "dedicated", 45));
    this.items.add(new Item(2, 1, "apple", "bama", 1));
    this.items.add(new Item(3, 3, "t-shirt", "raw", 23));
    this.items.add(new Item(4, 1, "laptop", "Dell", 1400));
    this.items.add(new Item(5, 2, "gloves", "scan", 24));
    this.items.add(new Item(6, 1, "scarf", "dedicated", 14));

    this.cart.setItems(List.of(items.get(3), items.get(4), items.get(5)));
  }

  @GetMapping(value = "/items/{id}", produces = "application/json")
  public ResponseEntity<Item> itemsDetail(@PathVariable int id) {

    return new ResponseEntity<>(items.get(id), HttpStatus.OK);
  }


  @GetMapping(value = "/items", produces = "application/json")
  public ResponseEntity<List<Item>> itemsDetail(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "pageSize", required = false) Integer pageSize)
  {
    if (page != null || pageSize != null) {
      return new ResponseEntity<>(items.subList(page, page + pageSize), HttpStatus.OK);
    }

    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @GetMapping("/items")
  public ResponseEntity<Item> findItem(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value ="brand", required = false) String brand) {

    if (name != null && !name.isEmpty()) {
      Item item = items.stream().filter(i -> i.getName().equals(name)).findAny().get();

      return new ResponseEntity<>(item, HttpStatus.OK);

    } else if (brand != null && !brand.isEmpty()) {
      Item item = items.stream().filter(i -> i.getBrand().equals(brand)).findAny().get();

      return new ResponseEntity<>(item, HttpStatus.OK);
    }

    return new ResponseEntity<>(items.get(0), HttpStatus.OK);
  }

  @GetMapping("/cart")
  public ResponseEntity<List<Object>> checkout() {

    cart.setPreferedPayment("Card");
           var schema = List.of(cart,
            cart.getTotalPrice(),
            cart.getNumberOfItems(),
            cart.getPreferedPayment());

    return new ResponseEntity<>(schema, HttpStatus.OK);
  }


//  var schema = {
//          "cart": [
//  {
//    "id": {"type": "integer"},
//    "amount": {"type": "integer"},
//    "name": {"type": "string"},
//    "brand": {"type": "string"},
//    "pricePerUnit": {"type": "integer"}
//  },{
//    "id": {"type": "integer"},
//    "amount": {"type": "integer"},
//    "name": {"type": "string"},
//    "brand": {"type": "string"},
//    "pricePerUnit": {"type": "integer"}
//  }
//    ],
//            "totalPrice": {"type": "integer"},
//          "numberOfItems": {"type": "integer"},
//          "preferedPayment": {"type": "string"}
//}


}