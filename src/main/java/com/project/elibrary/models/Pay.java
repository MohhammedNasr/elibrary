package com.project.elibrary.models; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "pay")
public class Pay
{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
   
   
  
    @Column (name = "full_name")
    private String fullName;
  
    @Column (name = "email")
    private String email;
  
    @Column (name = "address")
    private String address;
  
    @Column(name = "city")
    private String city;
  
    @Column(name = "state")
    private String state;
  
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "paymentMethod")
    private String paymentMethod;

    public Pay() {
    }

    public Pay(Long id, String fullName, String email, String address, String city, String state, String zipCode, String paymentMethod) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Pay id(Long id) {
        setId(id);
        return this;
    }

    public Pay fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public Pay email(String email) {
        setEmail(email);
        return this;
    }

    public Pay address(String address) {
        setAddress(address);
        return this;
    }

    public Pay city(String city) {
        setCity(city);
        return this;
    }

    public Pay state(String state) {
        setState(state);
        return this;
    }

    public Pay zipCode(String zipCode) {
        setZipCode(zipCode);
        return this;
    }

    public Pay paymentMethod(String paymentMethod) {
        setPaymentMethod(paymentMethod);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pay)) {
            return false;
        }
        Pay pay = (Pay) o;
        return Objects.equals(id, pay.id) && Objects.equals(fullName, pay.fullName) && Objects.equals(email, pay.email) && Objects.equals(address, pay.address) && Objects.equals(city, pay.city) && Objects.equals(state, pay.state) && Objects.equals(zipCode, pay.zipCode) && Objects.equals(paymentMethod, pay.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, address, city, state, zipCode, paymentMethod);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            "}";
    }

   
    
  
}