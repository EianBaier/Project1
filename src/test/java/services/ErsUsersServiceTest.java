package services;

import doa.ErsUsersDoa;
import models.ErsUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ErsUsersServiceTest {

    ErsUsersDoa ersUsersDoa = Mockito.mock((ErsUsersDoa.class));

    ErsUsersService ersUsersService;

    public ErsUsersServiceTest(){
        this.ersUsersService = new ErsUsersService((ersUsersDoa));
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAUser() {


        //arrange
        ErsUsers user = new ErsUsers(1,"eian","password","Eian","Baier","eian@gmail.com",2);
        ErsUsers expectedValue = user;
        Mockito.when(ersUsersDoa.getAUser("eian","password")).thenReturn(user);

        //act
        ErsUsers actualResult = ersUsersDoa.getAUser("eian","password");

        //assert

        assertEquals(expectedValue,actualResult);


    }
}