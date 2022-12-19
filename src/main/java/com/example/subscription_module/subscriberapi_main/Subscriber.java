package com.example.subscription_module.subscriberapi_main;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriber_id; //auto-generated so no setter or in-param cnstr
    private String name;
    private String mobileNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="Subscriber_and_its_plans",joinColumns = @JoinColumn(name="plan_id",referencedColumnName = "subscriber_id"))
    private Set<Plan> plan_ids=new LinkedHashSet<Plan>();

    public void setPlan_ids(Set<Plan> plan_ids) {
        this.plan_ids = plan_ids;
    }

    public Subscriber() {
        System.out.println("in subscriber no-arg cnstr");
    }


    public Subscriber(String name, String mobileNumber) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("name of subsriber is either null or empty or blank------->");
        if(mobileNumber == null || mobileNumber.isEmpty() || mobileNumber.isBlank())
            throw new IllegalArgumentException("mobileNumber of subsriber is either null or empty or blank------->");
        this.name = name;
        this.mobileNumber = mobileNumber;
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


    public Set<Plan> getPlan_ids() {
        return plan_ids;
    }


    public void setPlan(Plan plan_id) {
    if(plan_id == null)
        throw new IllegalArgumentException("plan in set plan subscr is null");
    this.plan_ids.add(plan_id);
    }

    
    // private Plan a=null;
    // public void removePlan(Plan theplan){
        
    //     this.plan_ids.forEach(plan -> {
    //         if(plan.getPlan_id()==theplan.getPlan_id())
    //             a=plan;
    //     });
    //     if(a!=null)
    //         this.plan_ids.remove(a);
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + subscriber_id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
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
        if (plan_ids == null) {
            if (other.plan_ids != null)
                return false;
        } else if (!plan_ids.equals(other.plan_ids))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Subscriber [subscriber_id=" + subscriber_id + ", name=" + name + ", mobileNumber=" + mobileNumber
                + ", plan_ids=" + plan_ids + "]";
    }

    
}
