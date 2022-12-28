package com.example.subscription_module.subscriberapi_main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubscriberPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plan_id;
    private String name;
    private Date validity;
    private Date creationDate;
    private Date updationDate;

    public SubscriberPlans() {
        System.out.println("in plan no-arg cnstr");
    }
    
    public SubscriberPlans(String name, Date validity) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("SubscriberPlans name is either null or empty or blank------->");
        if(validity==null || validity.before(new Date()))
            throw new IllegalArgumentException("SubscriberPlans validity is null or improper pls give a future date------->");
        this.name = name;
        this.validity = validity;
        System.out.println("in SubscriberPlans param cnstr");
    }

    public int getPlan_id() {
        System.out.println("in SubscriberPlans plan_id getter");
        return plan_id;
    }
   
    public String getName() {
        System.out.println("in SubscriberPlans name getter");
        return name;
    }
    public void setName(String name) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("SubscriberPlans name is either null or empty or blank------->");
        this.name = name;
        System.out.println("in SubscriberPlans name setter");
    }
    public Date getValidity() {
        System.out.println("in SubscriberPlans validity getter");
        return validity;
    }
    public void setValidity(Date validity) {
        System.out.println("in SubscriberPlans validity setter");
        if(validity==null || validity.before(new Date()))
            throw new IllegalArgumentException("SubscriberPlans validity is null or improper pls give a future date------->");
        this.validity = validity;
    }
    
    public void setPlan_id(int plan_id) {
        System.out.println("in SubscriberPlans plan_id setter");
        this.plan_id = plan_id;
    }

    public Date getCreationDate() {
        System.out.println("in SubscriberPlans creationDate getter");
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        System.out.println("in SubscriberPlans creationDate setter");
        this.creationDate = creationDate;
    }

    public Date getUpdationDate() {
        System.out.println("in SubscriberPlans updationDate getter");
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        System.out.println("in SubscriberPlans updationDate setter");
        this.updationDate = updationDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + plan_id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((validity == null) ? 0 : validity.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((updationDate == null) ? 0 : updationDate.hashCode());
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
        SubscriberPlans other = (SubscriberPlans) obj;
        if (plan_id != other.plan_id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (validity == null) {
            if (other.validity != null)
                return false;
        } else if (!validity.equals(other.validity))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (updationDate == null) {
            if (other.updationDate != null)
                return false;
        } else if (!updationDate.equals(other.updationDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubscriberPlans [plan_id=" + plan_id + ", name=" + name + ", validity=" + validity + ", creationDate="
                + creationDate + ", updationDate=" + updationDate + "]";
    }

    
}
