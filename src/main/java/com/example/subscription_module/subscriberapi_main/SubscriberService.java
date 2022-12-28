package com.example.subscription_module.subscriberapi_main;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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
            matchedSubscriber.setAddress(subscriber.getAddress());
            matchedSubscriber.setEmail(subscriber.getEmail());
            matchedSubscriber.setPrimarySpokenLanguage(subscriber.getPrimarySpokenLanguage());
            matchedSubscriber.setPlan_ids(matchedSubscriber.getPlan_ids());
            subscriberrepo.save(matchedSubscriber);
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("in subscriber service - in update subscr catch");
        }
    }

    public List<Subscriber> retriveAllSubscribers(){
        Pageable allPagedData=PageRequest.of(0, 20, Sort.by("name").ascending());
        return subscriberrepo.findAll(allPagedData).getContent();

    }

    public Subscriber getSubscriber(int id){
        return  subscriberrepo.findById(id).get();
    }

    public boolean addExistingPlanForASubscriber(Subscriber subscriber,int subscr_id,int plan_id){
       try {
        if(subscriber == null)
            throw new IllegalArgumentException("subscriber object found null while adding existingPlan for subscr");
        SubscriberPlans planEntity = rest.getForObject("http://plan-service/plans/giveplans/"+plan_id, SubscriberPlans.class);
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        System.out.println("---------------------------------------------------/n"+planEntity);
        System.out.println("---------------------------------------------------/n"+matchedSubscriber);
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
           
        return false;
    }
    

    public Collection<Subscriber> allSubscribersByEmail(String email){
        Pageable pagedByEmail=PageRequest.of(0, 10, Sort.by("email").ascending());
        return subscriberrepo.findAllByEmail(email,pagedByEmail);
    }

    public Collection<Subscriber> allSubscribersByMobileNumber(String mob){
        Pageable pagedByMobileNumber=PageRequest.of(0, 10, Sort.by("mobileNumber").ascending());
        return subscriberrepo.findAllByMobileNumber(mob,pagedByMobileNumber);
    }

    public Collection<Subscriber> allSubscribersByAddressHouseNo(String houseno){
        Pageable pagedByHouseNo=PageRequest.of(0, 10);
    return subscriberrepo.findByAddressHouseNo(houseno, pagedByHouseNo);
    }
    
    public Collection<Subscriber> allSubscribersByAddressStreetName(String streetname){
        Pageable pagedByAddress=PageRequest.of(0, 10);
        return subscriberrepo.findByAddressStreetname(streetname, pagedByAddress);
    }

    public Collection<Subscriber> allSubscribersByAddressCity(String city){
        Pageable pagedByCity=PageRequest.of(0, 10);
        return subscriberrepo.findByAddressCity(city, pagedByCity);
    }
    

    public Collection<Subscriber> allSubscribersByAddressZipCode(String zipcode){
        Pageable pagedByZipCode=PageRequest.of(0, 10);
        return  subscriberrepo.findByAddressZipcode(zipcode, pagedByZipCode);
    }

    public Collection<Subscriber> allSubscribersByAddressCountry(String country){
        Pageable pagedByCountry=PageRequest.of(0, 10);
        return subscriberrepo.findByAddressCountry(country, pagedByCountry);
    }

}














/*  java implementation rather to findBy(Reference)(variable with-in referred object)

    Collection<Subscriber> matchedSubscribersLot=new ArrayList<Subscriber>();
        subscriberrepo.findAll(pagedByCountry).forEach(subscriber  -> {
            if(subscriber.getAddress().getCountry().contains(country))
                matchedSubscribersLot.add(subscriber);
        });
     */