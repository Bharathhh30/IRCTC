package ticket.booking.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
//    hashpassword - we pass user password and hash that
    public static String hashpassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
    }
//    checkpassword  - we take user password as input and hash it and check if it is same or not with the existing hashed password
    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }
}


//  salting in hashing password , where we add a salt(random string ) to the password before hashing ,
//  just to make sure that same passwords dont get hashed same
//  as we add random salts to the passwords, even the passwords are same the hashed password will not be same due to
//  random string (salt) that is being added to the password before hashing it