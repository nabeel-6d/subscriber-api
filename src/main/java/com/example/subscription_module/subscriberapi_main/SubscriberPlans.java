package com.example.subscription_module.subscriberapi_main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubscriberPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int plan_id;
    private String name;
    private Date validity;

    public SubscriberPlans() {
        System.out.println("in plan no-arg cnstr");
    }
    
    public SubscriberPlans(String name, Date validity) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Plan_Prototype name is either null or empty or blank------->");
        if(validity==null || validity.before(new Date()))
            throw new IllegalArgumentException("Plan_Prototype validity is null or improper pls give a future date------->");
        this.name = name;
        this.validity = validity;
        System.out.println("in Plan_Prototype param cnstr");
    }

    public int getPlan_id() {
        System.out.println("in Plan_Prototype plan_id getter");
        return plan_id;
    }
   
    public String getName() {
        System.out.println("in Plan_Prototype name getter");
        return name;
    }
    public void setName(String name) {
        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Plan_Prototype name is either null or empty or blank------->");
        this.name = name;
        System.out.println("in Plan_Prototype name setter");
    }
    public Date getValidity() {
        System.out.println("in Plan_Prototype validity getter");
        return validity;
    }
    public void setValidity(Date validity) {
        System.out.println("in Plan_Prototype validity setter");
        if(validity==null || validity.before(new Date()))
            throw new IllegalArgumentException("Plan_Prototype validity is null or improper pls give a future date------->");
        this.validity = validity;
    }
    
    public void setPlan_id(int plan_id) {
        System.out.println("in Plan_Prototype plan_id setter");
        this.plan_id = plan_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + plan_id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((validity == null) ? 0 : validity.hashCode());
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
        return true;
    }

    @Override
    public String toString() {
        return "Plan_Prototype [plan_id=" + plan_id + ", name=" + name + ", validity=" + validity + "]";
    }
    
}
