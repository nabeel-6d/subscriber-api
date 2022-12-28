package com.example.subscription_module.subscriberapi_main;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int sl_no;
    private String houseNo;
    private String streetname;
    private String city;
    private String zipcode;
    private String country;  
  
    
    public Address() {
        System.out.println("in address no-arg cnstr");
    }

    public Address(String houseNo, String streetname, String city, String zipcode, String country) {
        System.out.println("in address param cnstr");
        if(houseNo==null || houseNo.isEmpty())
            throw new IllegalArgumentException("houseNo is not valid");
        if(streetname==null || streetname.isEmpty())
            throw new IllegalArgumentException("streetname is not valid");
        if(city==null || city.isEmpty())
            throw new IllegalArgumentException("city is not valid");
        if(zipcode==null || zipcode.isEmpty())
            throw new IllegalArgumentException("zipcode is not valid");
        if(country==null )
            throw new IllegalArgumentException("country is not valid");

            this.houseNo = houseNo;
            this.streetname = streetname;
            this.city = city;
            this.zipcode = zipcode;
            this.country = country;

    }

    public String getHouseNo() {
        System.out.println("in address gethouseNo");
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        System.out.println("in address sethouseNo");
        if(houseNo==null || houseNo.isEmpty())
            throw new IllegalArgumentException("houseNo is not valid");
        this.houseNo = houseNo;
    }

    public String getStreetname() {
        System.out.println("in address getstreetname");
        return streetname;
    }

    public void setStreetname(String streetname) {
        System.out.println("in address setstreetname");
        if(streetname==null || streetname.isEmpty())
            throw new IllegalArgumentException("streetname is not valid");
    
        this.streetname = streetname;
    }

    public String getCity() {
        System.out.println("in address getcity");
        return city;
    }

    public void setCity(String city) {
        System.out.println("in address sethouseNo");
        if(city==null || city.isEmpty())
            throw new IllegalArgumentException("city is not valid");
    
        this.city = city;
    }

    public String getZipcode() {
        System.out.println("in address getzipcode");
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        System.out.println("in address sethouseNo");
        if(zipcode==null || zipcode.isEmpty())
            throw new IllegalArgumentException("zipcode is not valid");
    
        this.zipcode = zipcode;
    }

    public String getCountry() {
        System.out.println("in address getcountry");
        return country;
    }

    public void setCountry(String country) {
        System.out.println("in address setcountry");
        if(country==null )
            throw new IllegalArgumentException("country is not valid");
        this.country = country;
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
        result = prime * result + ((streetname == null) ? 0 : streetname.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
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
        Address other = (Address) obj;
        if (houseNo == null) {
            if (other.houseNo != null)
                return false;
        } else if (!houseNo.equals(other.houseNo))
            return false;
        if (streetname == null) {
            if (other.streetname != null)
                return false;
        } else if (!streetname.equals(other.streetname))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (zipcode == null) {
            if (other.zipcode != null)
                return false;
        } else if (!zipcode.equals(other.zipcode))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Address [houseNo=" + houseNo + ", streetname=" + streetname + ", city=" + city + ", zipcode=" + zipcode
                + ", country=" + country + "]";
    }

    
    
}
