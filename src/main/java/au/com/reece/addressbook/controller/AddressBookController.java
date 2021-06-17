package au.com.reece.addressbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello world 1234 5 ";
    }
}
