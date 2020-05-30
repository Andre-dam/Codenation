package com.challenge.endpoints;

import com.challenge.endpoints.exceptions.ResourceNotFoundException;
import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
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
@RequestMapping("acceleration")
public class AccelerationController {

    @Autowired
    AccelerationServiceInterface accelerationService;

    @GetMapping("/{id}")
    ResponseEntity<Acceleration> getById(@PathVariable Long id) {
        return new ResponseEntity<>(accelerationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public List<Acceleration> getByCompanyId(@RequestParam Long companyId) {
        return accelerationService.findByCompanyId(companyId);
    }
}
