package com.broker.brokersystem.repositories;
import com.broker.brokersystem.data.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrokerRepository extends JpaRepository<Apartment,Integer> {


}