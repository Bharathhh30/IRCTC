package ticket.booking.entities;

import java.util.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfTravel;
    private Train train;


    // parameterised constructor
    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    //    getters
//    return ticket info
    public String getTicketInfo() {
        return String.format("Ticket Id: %s belongs to User %s from %s to %s on %s", ticketId, userId, source, destination, dateOfTravel);
    }

    //    getTicketId
    public String getTicketId() {
        return ticketId;
    }

//    getUserId
    public String getUserId(){
        return userId;
    }

//    getSource
    public String getSource(){
        return source;
    }

//    getDestination
    public String getDestination(){
        return destination;
    }

//    getDateOfTravel
    public String getDateOfTravel(){
        return  dateOfTravel;
    }

//    getTrain
    public Train getTrain(){
        return train;
    }

//    setters
//    setTicketId
    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

//    setSource
    public void setSource(String source){
        this.source = source;
    }

//    setUserId
    public void setUserId(String userId){
        this.userId = userId;
    }

//    setDestination
    public void setDestination(String destination){
        this.destination = destination;
    }

//    setDateOfTravel
    public void setDateOfTravel(String dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }

//    setTrain
    public void setTrain(Train train){
        this.train = train;
    }

// End of function
}