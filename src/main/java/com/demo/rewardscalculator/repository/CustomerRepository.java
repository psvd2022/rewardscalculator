package com.demo.rewardscalculator.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.rewardscalculator.model.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}