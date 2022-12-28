package com.example.subscription_module.subscriberapi_main;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Country {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryIdentifier",nullable = false)
    private int sl_no;
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sl_no",referencedColumnName = "countryIdentifier")
    private Set<Language> languages=new LinkedHashSet<Language>();  

    public Country() {
        System.out.println("in country no -arg cnstr");
    }

    public Country(Set<Language> languages) {
        System.out.println("in country param cnstr");
        this.languages = languages;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Language> getLanguages() {
        System.out.println("in languages getter method");
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        if(languages==null)
            throw new IllegalArgumentException("languages should not be not null");
        this.languages = languages;
    }

    public int getSl_no() {
        return sl_no;
    }

    public void setSl_no(int sl_no) {
        this.sl_no = sl_no;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sl_no;
        result = prime * result + ((languages == null) ? 0 : languages.hashCode());
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
        Country other = (Country) obj;
        if (sl_no != other.sl_no)
            return false;
        if (languages == null) {
            if (other.languages != null)
                return false;
        } else if (!languages.equals(other.languages))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Country [sl_no=" + sl_no + ", languages=" + languages + "]";
    }

}
