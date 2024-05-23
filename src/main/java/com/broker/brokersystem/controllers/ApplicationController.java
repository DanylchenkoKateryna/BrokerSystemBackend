package com.broker.brokersystem.controllers;

import com.broker.brokersystem.data.models.Application;
import com.broker.brokersystem.repositories.IApplicationRepository;
import com.broker.brokersystem.repositories.IBrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private IApplicationRepository applicationRepository;
    private List<Application> searchedApartments;
    public ApplicationController(){
        searchedApartments=new ArrayList<Application>();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Application>> list(){
        try {
            List<Application> apartment = applicationRepository.findAll();
            return new ResponseEntity<List<Application>>(apartment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Application>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody Application apartment){
        applicationRepository.save(apartment);
        return "New Apartment Added";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> get(@PathVariable Integer id) {
        try {
            Optional<Application> apartment = applicationRepository.findById(id);
            if (apartment.isPresent()) {
                return new ResponseEntity<>(apartment.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            applicationRepository.deleteById(id);
            return "Deleted Apartment with id " + id;
        } catch (NoSuchElementException e) {
            return "Apartment with id " + id + " not found.";
        }
    }
}
