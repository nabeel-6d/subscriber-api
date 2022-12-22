package com.example.subscription_module.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.subscription_module.models.Subscriber;



public interface SubscriberRepository extends CrudRepository<Subscriber,Integer>{
  
}
