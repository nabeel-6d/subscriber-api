package com.example.subscription_module.subscriberapi_main;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageNo",nullable = false)
    private int sl_no;
    private String name;
    private String origin;

    public Language() {
        System.out.println("in language no-arg cnstr");
    }

    public Language(String name, String origin) {
        System.out.println("in language param cnstr");
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name of the language is not valid");
        if(origin == null || origin.isEmpty())
            throw new IllegalArgumentException("origin of the language is not valid");
        
        this.name = name;
        this.origin = origin;
    }

    public int getSl_no() {
        System.out.println("in language getSl_no");
        return sl_no;
    }

    public void setSl_no(int sl_no) {
        this.sl_no = sl_no;
    }

    public String getName() {
        System.out.println("in language getName");
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name of the language is not valid");
   
        this.name = name;
    }

    public String getOrigin() {
        System.out.println("in language getOrigin");
        return origin;
    }

    public void setOrigin(String origin) {
        if(origin == null || origin.isEmpty())
            throw new IllegalArgumentException("origin of the language is not valid");
    
        this.origin = origin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
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
        Language other = (Language) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Language [name=" + name + ", origin=" + origin + "]";
    }
    
}
