package com.broker.brokersystem.repositories;

import com.broker.brokersystem.data.models.Apartment;
import com.broker.brokersystem.data.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application,Integer> {


}