package services;

import doa.ErsReimbursementDoa;
import doa.ErsUsersDoa;
import doa.ErsUsersDoaImpl;
import models.ErsUsers;

public class ErsUsersService {

    ErsUsersDoa ersUsersDoa;

    public ErsUsersService(ErsUsersDoa ersUsersDoa){

        this.ersUsersDoa = ersUsersDoa;
    }
    public ErsUsersService(){
        this.ersUsersDoa = new ErsUsersDoaImpl();
        };

    public ErsUsers getAUser(String username, String password) throws NullPointerException{
        return ersUsersDoa.getAUser(username,password);
       // ersUsersDoa.getAUser(user);
    }



    }





