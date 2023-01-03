package com.example.subscription_module.subscriberapi_main;

//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserDetailsDbRepository extends CrudRepository<UserDetailsFromDb,Integer>{
   UserDetailsFromDb findByUsername(String username);
}
