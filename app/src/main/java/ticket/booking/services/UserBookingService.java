package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;
    private List<User> userList;
//    object mapper to map the key names as they are snake case and camel case
//    jackson object mapper
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";   //final is used as once we initialised the path its fixed no changes

    public UserBookingService(User user) throws IOException {
        this.user = user;

//        java uses Type Erasure ,i.e. at runtime java forgets the actual type inside the generic ,
//        to avoid this problem we use TypeReference which acts like a remainder during runtime telling jackson that , “Hey, this isn’t just a generic List — it’s a List of User objects.”
//        List<E> -> List is a generic class and E is generic type Parameter
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException{
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException{
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

//    cancelBooking()  home work
    public Boolean cancelBooking(String ticketId){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }
        String finalTicketId1 = ticketId;   // Strings are immutable, so we can store directly
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }

    }
    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }


//    end of function
}

//  Boolean is an object of true or false , boolean is a primitive datatype
//  User is a class , meaning it can have mulitple objects so inorder to refer we use this keyword
//  to access the json data inorder to deseraialize and map we use the json file via path
//  we used jackson for objectmapping
//  why object mapping , because java cannot access or use the objects of json so we have to convert them into the java objects inorder to use
//  always remember to declare the type like List<User> userList; and use it or List<User> userList = objectMapper.readValue(users, new TypeReference<List<User>>(){})

//  seralization  = taking a java object and turning into JSON text(or xml,etc)
//  deserialization  = taking JSON text and turning into a Java object

//  once we start using static keyword it gets stored in the memory and can be accessed without any creation of object

//why did we declare Optional<User> becuz it can return us null and we also need to handle it to over come the null pointer exception by using isPresent() method