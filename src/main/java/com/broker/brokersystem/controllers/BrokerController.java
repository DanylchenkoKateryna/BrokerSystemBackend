package com.broker.brokersystem.controllers;

import com.broker.brokersystem.data.models.Apartment;
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
@CrossOrigin
@RequestMapping("/apartment")
public class BrokerController {

    @Autowired
    private IBrokerRepository brokerRepository;
    public BrokerController(){
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Apartment>> list(){
        try {
            List<Apartment> apartment = brokerRepository.findAll();
            return new ResponseEntity<List<Apartment>>(apartment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody Apartment apartment){
        apartment.setImgUrl("/p-1.png");
        brokerRepository.save(apartment);
        return "New Apartment Added";
    }


    @PostMapping("/search")
    public List<Apartment> search(@RequestBody Apartment newApartment) {
        List<Apartment> allApartments = brokerRepository.findAll();
        List<Apartment> searchedApartments=new ArrayList<Apartment>();
        for (Apartment apartment : allApartments) {
            if (apartment.getRooms() == newApartment.getRooms() &&
                    apartment.getFloor() == newApartment.getFloor() &&
                    Math.abs(apartment.getArea() - newApartment.getArea()) <= newApartment.getArea() * 0.1) {

                searchedApartments.add(apartment);
            }
        }
        if(searchedApartments.size()==0) {
            //newApartment.setImgUrl("/p-1.png");
            brokerRepository.save(newApartment);
            return searchedApartments;
        }else{
            brokerRepository.delete(searchedApartments.get(0));
            return searchedApartments;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> get(@PathVariable Integer id) {
        try {
            Optional<Apartment> apartment = brokerRepository.findById(id);
            if (apartment.isPresent()) {
                return new ResponseEntity<>(apartment.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> update(@RequestBody Apartment apartment, @PathVariable Integer id) {
        try {
            Optional<Apartment> existingApartment = brokerRepository.findById(id);
            if (existingApartment.isPresent()) {
                brokerRepository.save(apartment);
                return new ResponseEntity<>(HttpStatus.OK);
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
            brokerRepository.deleteById(id);
            return "Deleted Apartment with id " + id;
        } catch (NoSuchElementException e) {
            return "Apartment with id " + id + " not found.";
        }
    }


}