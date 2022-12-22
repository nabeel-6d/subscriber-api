package com.example.subscription_module.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.subscription_module.models.SubscriberPlans;

public interface SubscribersPlanRepository extends CrudRepository<SubscriberPlans,Integer>{
}
