package lassonde_hackaton;

import java.util.ArrayList;

public abstract class Person {
protected String name;
protected String address;
protected String paymentInfo;
protected String gender;
protected String userName;
protected String email;
protected int age;
//make this generic parent class

public Person()
{
	
}
public Person(String name) {
    this.name = name;
}


public abstract void setPaymentInfo(String i);
public abstract String getPaymentInfo();
public abstract String getallInfo();

public void setAge(int age) {
    this.age = age;
}


public void setName(String name) {
    this.name = name;
}

public void setAddress(String address) {
    this.address = address;
}

public void setGender(String gender) {
    this.gender = gender;
}
public void setUsername(String username) {
    this.userName = username;
}

public void setEmail(String email) {
    this.email = email;
}

public String getName() {
    return name;
}

public String getAddress() {
    return address;
}

public String getGender() {
    return gender;
}

public String getUserName() {
    return userName;
}

public int getAge() {
    return age;
}


}