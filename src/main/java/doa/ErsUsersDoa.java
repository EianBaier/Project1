package doa;

import models.ErsUsers;

public interface ErsUsersDoa {
     ErsUsers getAUser(String username, String password);
     //void getAUser(ErsUsers user);
     void isUserValid(ErsUsers user);
}
