package com.challenge.endpoints;

import com.challenge.endpoints.exceptions.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping("/user")
    List<User> getByQueryParameters(@RequestParam Map<String,String> requestParams) {
        String accelerationName = requestParams.get("accelerationName");
        String companyId = requestParams.get("companyId");

        List<User> response;
        if(accelerationName != null){
            response = userService.findByAccelerationName(accelerationName);
        }else{
            response = userService.findByCompanyId(Long.valueOf(companyId));
        }
        return response;
    }
}
