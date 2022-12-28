package com.example.subscription_module.subscriberapi_main;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Subscriber implements Comparable<Subscriber>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriber_id; //auto-generated so no setter or in-param cnstr
    private String name;
    private String mobileNumber;
    private String email;
    private String primarySpokenLanguage;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_sl_no",nullable = false)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="subscriber_plan_id",nullable = false,columnDefinition = "int DEFAULT 0")
    private Set<SubscriberPlans> plan_ids=new LinkedHashSet<SubscriberPlans>();

    public void setPlan_ids(Set<SubscriberPlans> plan_ids) {
        this.plan_ids = plan_ids;
    }

    public Subscriber() {
        System.out.println("in subscriber no-arg cnstr");
    }


    public Subscriber(String name, String mobileNumber,String email,String primarySpokenLanguage,Address address) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("name of subsriber is either null or empty or blank------->");
        if(mobileNumber == null || mobileNumber.isEmpty() || mobileNumber.isBlank())
            throw new IllegalArgumentException("mobileNumber of subsriber is either null or empty or blank------->");
        if(email == null || email.isEmpty() || email.isBlank())
            throw new IllegalArgumentException("email of subsriber is either null or empty or blank------->");
        if(address == null )
            throw new IllegalArgumentException("address of subsriber is null ------->");
        if(primarySpokenLanguage == null || primarySpokenLanguage.isEmpty() || primarySpokenLanguage.isBlank())
            throw new IllegalArgumentException("primarySpokenLanguage of subsriber is either null or empty or blank------->");
        
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email=email;
        this.primarySpokenLanguage=primarySpokenLanguage;
        this.address=address;
        System.out.println("in subscriber param cnstr");
    }
    
    public int getSubscriber_id() {
        System.out.println("in subscriber sub_id getter");
        return subscriber_id;
    }


    public String getName() {
        System.out.println("in subscriber name getter");
        return name;
    }


    public void setName(String name) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("name of subsriber is either null or empty or blank");
        this.name = name;
        System.out.println("in subscriber name setter");
    }


    public String getMobileNumber() {
        System.out.println("in subscriber mobileno getter");
        return mobileNumber;
    }


    public void setMobileNumber(String mobileNumber) {
        if(mobileNumber == null || mobileNumber.isEmpty() || mobileNumber.isBlank())
            throw new IllegalArgumentException("mobileNumber of subsriber is either null or empty or blank");
        this.mobileNumber = mobileNumber;
        System.out.println("in subscriber mobileno setter");
    }


    public Set<SubscriberPlans> getPlan_ids() {
        return plan_ids;
    }


    public void setPlan(SubscriberPlans plan_id) {
    if(plan_id == null)
        throw new IllegalArgumentException("plan in set plan subscr is null");
    this.plan_ids.add(plan_id);
    }

    
    public void removePlan(SubscriberPlans theplan){
        Set<SubscriberPlans> newdata=new LinkedHashSet<SubscriberPlans>();
        System.out.println("in subscr remove plan()");
        for(SubscriberPlans p:this.plan_ids){
            if(p!=null && p.getPlan_id()==theplan.getPlan_id())
                continue;
            else
                newdata.add(p);
        }
        this.plan_ids.clear();
        this.setPlan_ids(newdata); 
    }

    public Address getAddress() {
        System.out.println("in subscriber address getter");
        return address;
    }

    public void setAddress(Address address) {
        if(address == null)
            throw new IllegalArgumentException("address is not valid");
        this.address = address;
    }
    
    public void setSubscriber_id(int subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException("email is invalid");
        this.email = email;
    }

   
    public String getPrimarySpokenLanguage() {
        return primarySpokenLanguage;
    }

    public void setPrimarySpokenLanguage(String primarySpokenLanguage) {
        if(primarySpokenLanguage == null || primarySpokenLanguage.isEmpty() || primarySpokenLanguage.isBlank())
            throw new IllegalArgumentException("primarySpokenLanguage of subsriber is either null or empty or blank------->");
    
        this.primarySpokenLanguage = primarySpokenLanguage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + subscriber_id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((primarySpokenLanguage == null) ? 0 : primarySpokenLanguage.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((plan_ids == null) ? 0 : plan_ids.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subscriber other = (Subscriber) obj;
        if (subscriber_id != other.subscriber_id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (mobileNumber == null) {
            if (other.mobileNumber != null)
                return false;
        } else if (!mobileNumber.equals(other.mobileNumber))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (primarySpokenLanguage == null) {
            if (other.primarySpokenLanguage != null)
                return false;
        } else if (!primarySpokenLanguage.equals(other.primarySpokenLanguage))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (plan_ids == null) {
            if (other.plan_ids != null)
                return false;
        } else if (!plan_ids.equals(other.plan_ids))
            return false;
        return true;
    }

    @Override
    public int compareTo(Subscriber subscriber) {
        if(subscriber!=null){
            return (this.name+this.mobileNumber+this.email+this.address.toString()).compareTo(subscriber.getName()+subscriber.getMobileNumber()+subscriber.getEmail()+subscriber.getAddress().toString()); 
        }
        return 0;
    }

    
    @Override
    public String toString() {
        return "Subscriber [subscriber_id=" + subscriber_id + ", name=" + name + ", mobileNumber=" + mobileNumber
                + ", email=" + email + ", primarySpokenLanguage=" + primarySpokenLanguage + ", address=" + address
                + ", plan_ids=" + plan_ids + "]";
    }

}
