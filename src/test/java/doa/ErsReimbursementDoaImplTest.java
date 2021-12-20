package doa;

import models.ErsReimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;


import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErsReimbursementDoaImplTest {

    ErsReimbursementDoa ersReimbursementDoa;

    ErsReimbursementDoaImplTest(){
        this.ersReimbursementDoa = new ErsReimbursementDoaImpl(H2Util.url,H2Util.username,H2Util.password);

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
    void getAllReimbursements() {
        List<ErsReimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(new ErsReimbursement(1,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(2,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(3,100, null, null, "test", null, 1, 2,1,3));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(0));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(1));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(2));

        List<ErsReimbursement> actualResult = ersReimbursementDoa.getAllReimbursements();


        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getAllReumbursementsOfAStatus() {

        List<ErsReimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(new ErsReimbursement(1,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(2,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(3,100, null, null, "test", null, 1, 2,1,3));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(0));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(1));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(2));

        List<ErsReimbursement> actualResult = ersReimbursementDoa.getAllReumbursementsOfAStatus(1);


        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getAllReimbursementsForAnEmployee() {

        List<ErsReimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(new ErsReimbursement(1,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(2,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(3,100, null, null, "test", null, 1, 2,1,3));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(0));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(1));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(2));

        List<ErsReimbursement> actualResult = ersReimbursementDoa.getAllReimbursementsForAnEmployee(1);


        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void submitReimbursement() {

        List<ErsReimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(new ErsReimbursement(1,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(2,100, null, null, "test", null, 1, 2,1,3));
        expectedResult.add(new ErsReimbursement(3,100, null, null, "test", null, 1, 2,1,3));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(0));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(1));
        ersReimbursementDoa.submitReimbursement(expectedResult.get(2));

        Integer actualResult = ersReimbursementDoa.getAllReimbursements().size();


        assertEquals(expectedResult.size(), actualResult);
    }

    @Test
    void resolveReimbursement() {

        List<ErsReimbursement> expectedResult = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        //expectedResult.add(new ErsReimbursement(1,100, null,null, "test", null, 1, 2,2,3));

        ErsReimbursement reimbToPass = (new ErsReimbursement(1,100, null, null, "test", null, 1, 2,1,3));

        ersReimbursementDoa.submitReimbursement(reimbToPass);

        ersReimbursementDoa.resolveReimbursement(1,2);
        expectedResult.add(new ErsReimbursement(1,100, null,currentTimestamp, "test", null, 1, 2,2,3));

        List<ErsReimbursement> actualResult = ersReimbursementDoa.getAllReimbursements();

        ErsReimbursement reimb =  actualResult.get(0);
        ErsReimbursement reimb2 =  expectedResult.get(0);

        reimb.setReimbResolved(null);
        reimb2.setReimbResolved(null);




        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}