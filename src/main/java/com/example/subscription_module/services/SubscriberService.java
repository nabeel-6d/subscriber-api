package com.example.subscription_module.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.subscription_module.models.Subscriber;
import com.example.subscription_module.models.SubscriberPlans;
import com.example.subscription_module.repositories.SubscriberRepository;
import com.example.subscription_module.repositories.SubscribersPlanRepository;


@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberrepo;
    @Autowired
    private RestTemplate rest;
    @Autowired
    SubscribersPlanRepository subscrplanrepo;

    public boolean addSubscriber(Subscriber subscr){
       try {
        if(subscr==null)
            throw new IllegalArgumentException("subscriber in sub service found null during adding");
            subscriberrepo.save(subscr);
        return true;
       } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("in subscriber service - in add subcr catch");
       }
       return false;
    }

    public boolean removeSubscriber(int subscr_id){
       try {
        if(subscr_id<0)
            throw new IllegalArgumentException("subscriber id found invalid while removing");
        subscriberrepo.deleteById(subscr_id);
        return true;
       } catch (IllegalArgumentException e) {
        e.printStackTrace();
        System.out.println("in subscriber service - in remove subcr catch");
       }
       return false;
    }

    public void updateSubscriber(Subscriber subscriber,int id){
        try {
            if(subscriber==null)
                throw new IllegalArgumentException("subscriber object found null while updating subscr");
            Subscriber matchedSubscriber=subscriberrepo.findById(id).get();
            matchedSubscriber.setName(subscriber.getName());
            matchedSubscriber.setMobileNumber(subscriber.getMobileNumber());
            matchedSubscriber.setPlan_ids(matchedSubscriber.getPlan_ids());
            subscriberrepo.save(matchedSubscriber);
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("in subscriber service - in update subscr catch");
        }
    }

    public List<Subscriber> retriveAllSubscribers(){
        List<Subscriber> recivedData=new ArrayList<Subscriber>();
        subscriberrepo.findAll().forEach(recivedData::add);
        
        return recivedData;
    }

    public Subscriber getSubscriber(int id){
        return  subscriberrepo.findById(id).get();
    }

    public boolean addExistingPlanForASubscriber(Subscriber subscriber,int subscr_id,int plan_id){
       try {
        if(subscriber == null)
            throw new IllegalArgumentException("subscriber object found null while adding existingPlan for subscr");
        SubscriberPlans planEntity = rest.getForObject("http://localhost:8082/plans/giveplans/"+plan_id, SubscriberPlans.class);
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        matchedSubscriber.setPlan(planEntity);
        subscriberrepo.save(matchedSubscriber);
        return true;
       } catch (Exception e) {
        e.printStackTrace();
        System.out.println("in subscriber service - in addExistingPlan catch");
       }
       return false;
    }

    public boolean removeExistingPlanForASubscriber(int subscr_id,int plan_id){
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        SubscriberPlans planToBeDeleted=null;
        for(SubscriberPlans p: matchedSubscriber.getPlan_ids()){
            if(p.getPlan_id()==plan_id){
                planToBeDeleted=p;
            }
        }
        if(planToBeDeleted!=null){
                System.out.println("plan to remove is ------------------------->>>>>"+planToBeDeleted);
                matchedSubscriber.removePlan(planToBeDeleted);
                subscrplanrepo.deleteById(plan_id);
            }
           
        

        // if(deletionPlan!=null){
        //     subscrplanrepo.deleteById(plan_id);
        //     s.removePlan(deletionPlan);
        //     subscriberrepo.save(s);
        //     return true;
        // }else
            return false;
    }
    
}
