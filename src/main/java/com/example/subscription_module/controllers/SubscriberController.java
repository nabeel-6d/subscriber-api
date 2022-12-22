package com.example.subscription_module.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscription_module.models.Subscriber;
import com.example.subscription_module.services.SubscriberService;

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
    public List<Subscriber> getALLSubscribers(){
        return subService.retriveAllSubscribers();
    }

    @PostMapping("/{subscr_id}/addplan/{plan_id}")
    public void addPlanForASubscriber(@RequestBody Subscriber s,@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.addExistingPlanForASubscriber(s, subscr_id, plan_id);
    }

    @DeleteMapping("/{subscr_id}/deleteplan/{plan_id}")
    public void removePlanForSubscriber(@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.removeExistingPlanForASubscriber(subscr_id, plan_id);
    }
    // @GetMapping("/get/{id}")
    // public Subscriber getASubscriber(@PathVariable int id){
        
    // }
}
