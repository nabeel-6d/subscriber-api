package com.example.subscription_module.subscriberapi_main;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberrepo;
    @Autowired
    private RestTemplate rest;
    @Autowired
    private SubscribersPlanRepository subscrplanrepo;

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

    public boolean addExistingPlanForASubscriber(int subscr_id,int plan_id){
       try {
            /* ------------------ for plan service user authentication ------------------ */
           String username="";
           String password="";
           JwtRequest requestjson=null;
            
            if(Utility.getUsername()!=null && Utility.getPassword()!=null){
                username = Utility.getUsername();
                password = Utility.getPassword();
                requestjson = new JwtRequest(username, password);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            // headers.setBasicAuth(username, password);
            //ResponseEntity<Object> re=new ResponseEntity<>(requestjson, HttpStatus.OK);
            Map<String,Object> map=new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            HttpEntity<Map<String,Object>> entity = new HttpEntity<>(map,headers);
            System.out.println("\nentity body is -------------------------------"+entity.getBody());
            System.out.println("\nusername -------------------------------"+username);
            System.out.println("\npassword -------------------------------"+password);
            String tokenResponse = rest.postForObject("http://plan-service/plans/user/authenticate", entity, String.class);
            System.out.println("\nresulting post request body or token-------------- \n"+tokenResponse+"\n");
            String tokenIncomplete=tokenResponse.substring(13);
            String[] tokenSplit=tokenIncomplete.split("\"");
            String parseableToken = tokenSplit[0];
            System.out.println("\nparseable token got is --------------------------------------"+parseableToken+"\n and are both tokens equal? "+tokenResponse.equals(parseableToken));
            HttpHeaders headers2 = new HttpHeaders();
            headers2.setBearerAuth(parseableToken);
            
            System.out.println("\n\nin chechking bearer auth ---------------------------"+"\n"+headers2+"\n\n");
            HttpEntity<String> access = new HttpEntity<String>(headers2);
            ResponseEntity<SubscriberPlans> response = rest.exchange("http://plan-service/plans/giveplans/"+plan_id, HttpMethod.GET, access,SubscriberPlans.class);
            
            if(response.getStatusCode() != HttpStatus.OK)
                throw new HttpException("\n\nSome error occured mate cross check\n\n");
            
        SubscriberPlans suscbrPlanEntity = response.getBody();
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        System.out.println("---------------------------------------------------/n"+suscbrPlanEntity);
        System.out.println("---------------------------------------------------/n"+matchedSubscriber);

        matchedSubscriber.setPlan(suscbrPlanEntity);
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
    
    public Collection<Subscriber> allSubscribersByName(String name){
        Pageable pagedByName=PageRequest.of(0, 10, Sort.by("email").ascending());
        return subscriberrepo.findByNameLike('%'+name+'%', pagedByName);
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

    public Collection<Subscriber> allSubscribersByAddressCountryAndLanguage(String country,String spokenLanguage){
        Pageable pagedByCountryAndLanguage=PageRequest.of(0, 10);
        return subscriberrepo.findByAddressCountryAndPrimarySpokenLanguage(country, spokenLanguage, pagedByCountryAndLanguage);
    }

    public Collection<Subscriber> allSubscribersByAddressCountryAndSubscriberName(String countryname,String name){
        Pageable pagedByCountryAndName=PageRequest.of(0, 15);
        return subscriberrepo.findByAddressCountryAndNameContaining(countryname,name, pagedByCountryAndName);
    }
}














/*  java implementation rather to findBy(Reference)(variable with-in referred object)

    Collection<Subscriber> matchedSubscribersLot=new ArrayList<Subscriber>();
        subscriberrepo.findAll(pagedByCountry).forEach(subscriber  -> {
            if(subscriber.getAddress().getCountry().contains(country))
                matchedSubscribersLot.add(subscriber);
        });
     */