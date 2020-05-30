package com.challenge.endpoints;

import com.challenge.endpoints.exceptions.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceInterface userService;

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping(params = "accelerationName")
    List<User> getByAccelerationName(@RequestParam String accelerationName) {
        return userService.findByAccelerationName(accelerationName);
    }

    @GetMapping(params = "companyId")
    List<User> getByCompanyId(@RequestParam Long companyId) {
        return userService.findByCompanyId(companyId);
    }
}
