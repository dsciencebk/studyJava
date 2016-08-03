package com.JDBC;

/**
 * Created by Анна on 03.08.2016.
 */
public class Users {
    private int idUsers;
    private String firstName;
    private String lastName;

    public Users(){}
    public Users(int idUsers, String firstName, String lastName){
        this.idUsers=idUsers;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @Override
    public String toString(){
        return "Full Name: "+ idUsers +"\t" +firstName +"\t"+lastName;
    }
}
