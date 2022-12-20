package com.example.subscription_module.subscriberapi_main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subrepo;
    @Autowired
    private RestTemplate rest;
    @Autowired
    Subscribers_Plan_Repository subplanrepo;

    public boolean addSubscriber(Subscriber subscr){
       try {
        if(subscr==null)
            throw new IllegalArgumentException("subscriber in sub service found null during adding");
            subrepo.save(subscr);
        return true;
       } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("in subscr service - in add subcr catch");
       }
       return false;
    }

    public boolean removeSubscriber(int subscr_id){
       try {
        if(subscr_id<0)
            throw new IllegalArgumentException("subscr id found invalid while removing");
        subrepo.deleteById(subscr_id);
        return true;
       } catch (IllegalArgumentException e) {
        e.printStackTrace();
        System.out.println("in subcr service - in remove subcr catch");
       }
       return false;
    }

    public void updateSubscriber(Subscriber sub,int id){
        try {
            if(sub==null)
                throw new IllegalArgumentException("subscr object found null while updating subscr");
            Subscriber s=subrepo.findById(id).get();
            s.setName(sub.getName());
            s.setMobileNumber(sub.getMobileNumber());
            s.setPlan_ids(s.getPlan_ids());
            subrepo.save(s);
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("in subscr service - in update subscr catch");
        }
    }

    public List<Subscriber> retriveAllSubscribers(){
        List<Subscriber> recivedData=new ArrayList<Subscriber>();
        subrepo.findAll().forEach(recivedData::add);
        
        return recivedData;
    }

    public Subscriber getSubscriber(int id){
        return  subrepo.findById(id).get();
    }

    public boolean addExistingPlanForASubscriber(Subscriber sub,int subscr_id,int plan_id){
       try {
        if(sub == null)
            throw new IllegalArgumentException("subscr object found null while adding existingPlan for subscr");
        Subscriber_Plans theplan = rest.getForObject("http://localhost:8082/plans/giveplans/"+plan_id, Subscriber_Plans.class);
        Subscriber s = subrepo.findById(subscr_id).get();
        s.setPlan(theplan);
        subrepo.save(s);
        return true;
       } catch (Exception e) {
        e.printStackTrace();
        System.out.println("in subscr service - in addExistingPlan catch");
       }
       return false;
    }

    public boolean removeExistingPlanForASubscriber(int subscr_id,int plan_id){
        Subscriber s = subrepo.findById(subscr_id).get();
        Subscriber_Plans proto=null;
        for(Subscriber_Plans p: s.getPlan_ids()){
            if(p.getPlan_id()==plan_id){
                proto=p;
            }
        }
        if(proto!=null){
                System.out.println("plan to remove is ------------------------->>>>>"+proto);
                s.removePlan(proto);
                subplanrepo.deleteById(plan_id);
            }
           
        

        // if(deletionPlan!=null){
        //     subplanrepo.deleteById(plan_id);
        //     s.removePlan(deletionPlan);
        //     subrepo.save(s);
        //     return true;
        // }else
            return false;
    }
    
}
