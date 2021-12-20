package doa;

import models.ErsUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import static org.junit.jupiter.api.Assertions.*;

class ErsUsersDoaImplTest {

    ErsUsersDoa ersUsersDoa;

    ErsUsersDoaImplTest(){
        this.ersUsersDoa = new ErsUsersDoaImpl(H2Util.url,H2Util.username,H2Util.password);
    }



    @BeforeEach
    void setUp() {
        H2Util.createTables();
    }

    @AfterEach
    void tearDown() {

        H2Util.dropTables();
    }

    @Test
    void getAUser() {
        ErsUsers expecteduser = new ErsUsers(1,"Bob","P4ssw0rd","Bob","Roberts","SecretFormula@email.com",1);

        ErsUsers actualUser = ersUsersDoa.getAUser("Bob","P4ssw0rd");

        assertEquals(expecteduser.toString(),actualUser.toString());




    }
}