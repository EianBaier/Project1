package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import io.javalin.http.Context;
import models.ErsUsers;
import models.JsonResponse;
import org.h2.engine.User;
import services.ErsUsersService;

public class ErsUsersController {

    static ErsUsersService ersUsersService = new ErsUsersService();

    public static void getAUser(Context context) throws JsonProcessingException {

        ErsUsers userlog = context.bodyAsClass(ErsUsers.class);

        //context.sessionAttribute("user-session",userlog);

        //context.contentType("application/json");

        /*String username = context.pathParam("username");
        String password = context.pathParam("password");*/

        System.out.println(userlog.getUsername());
        System.out.println(userlog.getPassword());


       ErsUsers user = ersUsersService.getAUser(userlog.getUsername(),userlog.getPassword());

        if (userlog == null) {
            String jsonString = "wrongo";
            context.result(jsonString);
        } else {

            context.sessionAttribute("user-session", user);
            UserDTO loggedInUser = new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getUserRoleId(), user.getUserId());

            String jsonString = new ObjectMapper().writeValueAsString(loggedInUser);

            context.result(jsonString);
        }


    }

    public static void checkSession(Context context) throws JsonProcessingException {

        ErsUsers user = context.sessionAttribute("user-session");

        if (user == null) {
            new UserDTO(null, null, null, 0,0);
        } else {
            UserDTO loggedInUser = new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getUserRoleId(), user.getUserId());
            String jsonString = new ObjectMapper().writeValueAsString(loggedInUser);

            context.result(jsonString);
        }

    }

    public static void logout(Context context) throws JsonProcessingException {

        context.sessionAttribute("user-session",null);

        context.result("you have been logged out");



    }
}








