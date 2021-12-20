package services;

import com.fasterxml.jackson.annotation.JsonFormat;
import doa.ErsReimbursementDoa;
import doa.ErsUsersDoa;
import models.ErsReimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErsReimbursementServiceTest {

   /* ErsUsersDoa ersUsersDoa = Mockito.mock((ErsUsersDoa.class));

    ErsUsersService ersUsersService;

    public ErsUsersServiceTest(){
        this.ersUsersService = new ErsUsersService((ersUsersDoa));
    }*/

    ErsReimbursementDoa ersReimbursementDoa = Mockito.mock((ErsReimbursementDoa.class));

    ErsReimbursementService ersReimbursementService;

    public ErsReimbursementServiceTest(){
        this.ersReimbursementService = new ErsReimbursementService(((ersReimbursementDoa)));
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllReimbursements() {


        //arrange
        List<ErsReimbursement> reimbursements = new ArrayList<>();
        reimbursements.add(new ErsReimbursement(1,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(2,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(3,100, null, null, "test", null, 5, 2,1,3));
        List<ErsReimbursement> expectedvalues = reimbursements;
        //act
        Mockito.when(ersReimbursementDoa.getAllReimbursements()).thenReturn(reimbursements);

        List<ErsReimbursement> actualresult = ersReimbursementDoa.getAllReimbursements();

        //assert
        assertEquals(expectedvalues, actualresult);

    }

    @Test
    void getAllReimbursementsForAnEmployee() {
        List<ErsReimbursement> reimbursements = new ArrayList<>();
        reimbursements.add(new ErsReimbursement(1,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(2,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(3,100, null, null, "test", null, 5, 2,1,3));
        List<ErsReimbursement> expectedvalues = reimbursements;
        //act
        Mockito.when(ersReimbursementDoa.getAllReimbursementsForAnEmployee(5)).thenReturn(reimbursements);

        List<ErsReimbursement> actualresult = ersReimbursementDoa.getAllReimbursementsForAnEmployee(5);

        //assert
        assertEquals(expectedvalues, actualresult);
    }

    @Test
    void submitReimbursement() {

        ErsReimbursement reimbursement = new ErsReimbursement(1, 2, null, null, "test",null, 5, 2, 1, 3);

        ersReimbursementService.submitReimbursement(reimbursement);

        Mockito.verify(ersReimbursementDoa).submitReimbursement(reimbursement);
    }

    @Test
    void getAllReumbursementsOfAStatus() {
        List<ErsReimbursement> reimbursements = new ArrayList<>();
        reimbursements.add(new ErsReimbursement(1,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(2,100, null, null, "test", null, 5, 2,1,3));
        reimbursements.add(new ErsReimbursement(3,100, null, null, "test", null, 5, 2,1,3));
        List<ErsReimbursement> expectedvalues = reimbursements;
        //act
        Mockito.when(ersReimbursementDoa.getAllReumbursementsOfAStatus(3)).thenReturn(reimbursements);

        List<ErsReimbursement> actualresult = ersReimbursementDoa.getAllReumbursementsOfAStatus(3);

        //assert
        assertEquals(expectedvalues, actualresult);
    }

    @Test
    void resolveReimbursement() {
        Integer reimbId =1 ;
        Integer reimbStatusId = 2;

        ersReimbursementService.resolveReimbursement(reimbId,reimbStatusId);

        Mockito.verify(ersReimbursementDoa).resolveReimbursement(reimbId,reimbStatusId);



    }
}