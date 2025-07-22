package ticket.booking.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonNaming (PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

//    parameterised constructor
    public User(String name,String password,String hashedPassword,List<Ticket> ticketsBooked,String userId){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

//    empty constructor
    public User(){

    }

//    getters and setters as the attributes are private we need getters and setters to access or modify them
//    Encapsulation — wrapping data (fields) and methods (getters/setters) together, and controlling access to the data.


//    getName
    public String getName(){
        return this.name; //return this.name works and return name also works
    }

//    getPassword
    public String getPassword(){
        return this.password; //avoid writing getter for password
    }

//    getHashedPassword
    public String getHashedPassword(){
        return this.hashedPassword;
    }

//    getTicketsBooked
    public List<Ticket> getTicketsBooked(){
        return ticketsBooked;
    }

//    getUserId
    public String getUserId(){
        return userId;
    }

//    method to print the tickets
    public void printTickets(){
        if (ticketsBooked == null || ticketsBooked.isEmpty()) {
            System.out.println("No tickets booked.");
            return;
        }
//        == null , checks if the list itself exists or no
//        isEmpty() checks if the list exists but has no elements
        for(int i=0;i<ticketsBooked.size();i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

//    setters
//    setName
    public void setName(String name){
        this.name = name;
    }

//    setHashedPassword
    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

//    setTicketsBooked
    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

//    setUserId
    public void setUserId(String userId){
        this.userId = userId;
    }

//    end of function
}
