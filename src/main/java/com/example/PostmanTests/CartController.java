package com.example.PostmanTests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Data
@RestController
@RequestMapping("api/v1")
public class CartController {

  private ArrayList<User> users;
  private ArrayList<Item> cart;

  @Autowired
  public CartController(ArrayList<User> users, ArrayList<Item> cart) {
    this.users = users;
    this.cart = cart;
  }

  @GetMapping()
  public void getVersion() {
  }

  @GetMapping(value = "/items", produces = "text/plain")
  public ResponseEntity<String> getItems() {
    return new ResponseEntity<>("Hello", HttpStatus.CREATED);
  }

  @GetMapping(value = "/items", produces = "application/json")
  public ResponseEntity<List<String>> items() {
    var list = List.of("one", "two", "three", "four");
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  // GET request for (empty) cart
  @GetMapping("/cart")
  public List<Item> returnCart() {
    return cart;
  }


  // POST request: add item to cart (update cart from request body)
  @PostMapping("/items")
  public ResponseEntity<Item> addItem(@RequestBody Item item) {

    item.setAmount(item.getAmount() + 1);
    return new ResponseEntity<>(item, HttpStatus.CREATED);
  }

  // DELETE request: Delete item from cart in request body
  @DeleteMapping("/items/{id}")
  public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {

    item.setAmount(item.getAmount() - 1);
    return new ResponseEntity<>(item, HttpStatus.OK);
  }

  // GET request: list of users
  @GetMapping(value = "/users", produces = "application/json")
  public ResponseEntity<List<User>> itemsDetail() {

    users.add(new User(7, "James", "Bond", "james@bond.no", PaymentType.CASH));

    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // Retrieve user from request body, update it with user id, add it to the user list,
  // and return it in the response along with HTTP status code.
  @PostMapping(value = "/users", produces = "application/json")
  public ResponseEntity<User> showUser(@RequestBody User user) {

    int randomId = (int)(Math.random() * 100) + 1;

    user.setId(randomId);

    users.add(user);
    System.out.println(user.getLastName());

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  // Retrieve the user from the request body, update the user id with the id sent as path variable.
  // Add user to userList and return user and HTTP response code.
  @PutMapping(value = "/users/{id}", produces = "application/json")
  public ResponseEntity<User> updateUserPayment(@PathVariable int id, @RequestBody User user) {

    user.setId(id);
    user.setLastName("Nordmann");

    users.add(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

}