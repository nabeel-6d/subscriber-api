package com.example.subscription_module.subscriberapi_main;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subService;

    @PostMapping("/add")
    public void add(@RequestBody Subscriber s){
        subService.addSubscriber(s);
        System.out.println("in subscr contrller add");
    }

    @DeleteMapping("/delete/{subscr_id}")
    public void delete(@PathVariable int subscr_id){
        subService.removeSubscriber(subscr_id);
        System.out.println(" in subscr contrller delete");
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Subscriber s,@PathVariable int id){
        subService.updateSubscriber(s,id);
        System.out.println(" in subscr contrller update");
    }

    @GetMapping("/getall")
    public Collection<Subscriber> getALLSubscribers(){
        return subService.retriveAllSubscribers();
    }

    /* -------------------------- code involving plans -------------------------- */

    @PostMapping("/{subscr_id}/addplan/{plan_id}")
    public void addPlanForASubscriber(@RequestBody Subscriber s,@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.addExistingPlanForASubscriber(s, subscr_id, plan_id);
    }

    @DeleteMapping("/{subscr_id}/deleteplan/{plan_id}")
    public void removePlanForSubscriber(@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.removeExistingPlanForASubscriber(subscr_id, plan_id);
    }
    
  /* -------------------------- code involving search criteria by address type -------------------------- */

    @GetMapping("/getall/bymob/{mob}")
    public Collection<Subscriber> getSubscribersByMobileNumber(@PathVariable String mob){
        return subService.allSubscribersByMobileNumber(mob);
    }

    @GetMapping("/getall/byemail/{email}")
    public Collection<Subscriber> getSubscribersByEmail(@PathVariable String email){
        return subService.allSubscribersByEmail(email);
    }

    @GetMapping("/getall/address/houseno/{housno}")
    public Collection<Subscriber> getSubscribersByAddressHouseNo(@PathVariable String housno){
        return subService.allSubscribersByAddressHouseNo(housno);
    }

    
    @GetMapping("/getall/address/streetname/{streetname}")
    public Collection<Subscriber> getSubscribersByAddressSteetName(@PathVariable String streetname){
        return subService.allSubscribersByAddressStreetName(streetname);
    }

    
    @GetMapping("/getall/address/city/{city}")
    public Collection<Subscriber> getSubscribersByAddressCity(@PathVariable String city){
        return subService.allSubscribersByAddressCity(city);
    }

    
    @GetMapping("/getall/address/zipcode/{zipcode}")
    public Collection<Subscriber> getSubscribersByAddressZipcode(@PathVariable String zipcode){
        return subService.allSubscribersByAddressZipCode(zipcode);
    }

    @GetMapping("/getall/address/country/{countryname}")
    public Collection<Subscriber> getSubscribersByAddressCountry(@PathVariable String countryname){
        return subService.allSubscribersByAddressCountry(countryname);
    }

}
