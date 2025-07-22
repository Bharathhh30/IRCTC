package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {

    private User user;
    private List<User> userList;
//    object mapper to map the key names as they are snake case and camel case
//    jackson object mapper
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";   //final is used as once we initialised the path its fixed no changes

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
//        java uses Type Erasure ,i.e. at runtime java forgets the actual type inside the generic ,
//        to avoid this problem we use TypeReference which acts like a remainder during runtime telling jackson that , “Hey, this isn’t just a generic List — it’s a List of User objects.”
//        List<E> -> List is a generic class and E is generic type Parameter
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

//    cancelBooking()  home work
}

//  User is a class , meaning it can have mulitple objects so inorder to refer we use this keyword
//  to access the json data inorder to deseraialize and map we use the json file via path
//  we used jackson for objectmapping
//  why object mapping , because java cannot access or use the objects of json so we have to convert them into the java objects inorder to use
//  always remember to declare the type like List<User> userList; and use it or List<User> userList = objectMapper.readValue(users, new TypeReference<List<User>>(){})

//  seralization  = taking a java object and turning into JSON text(or xml,etc)
//  deserialization  = taking JSON text and turning into a Java object

//  once we start using static keyword it gets stored in the memory and can be accessed without any creation of object

//why did we declare Optional<User> becuz it can return us null and we also need to handle it to over come the null pointer exception by using isPresent() method