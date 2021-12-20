package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import models.ErsReimbursement;
import services.ErsReimbursementService;

import java.util.List;

public class ErsReimbursementController {

    static ErsReimbursementService ersReimbursementService = new ErsReimbursementService();

    public static void getAllReimbursements(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        List<ErsReimbursement> reimbursementsList = ersReimbursementService.getAllReimbursements();


        String jsonString = new ObjectMapper().writeValueAsString(reimbursementsList);

        context.result(jsonString);


    }

    public static void getAllReimbursementsByStatusID(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        Integer reimbStatusId = Integer.parseInt(context.pathParam("statusid"));

        List<ErsReimbursement> reimbursementsList = ersReimbursementService.getAllReumbursementsOfAStatus(reimbStatusId);

        String jsonString = new ObjectMapper().writeValueAsString(reimbursementsList);

        context.result(jsonString);




    }



    public static void getAllReimbursementsForAnEmployee(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        Integer reimbAuthor = Integer.parseInt(context.pathParam("id"));

        List<ErsReimbursement> reimbursementsList = ersReimbursementService.getAllReimbursementsForAnEmployee(reimbAuthor);


        String jsonString = new ObjectMapper().writeValueAsString(reimbursementsList);

        context.result(jsonString);


    }

    public static void submitAReimbursement(Context context) throws JsonProcessingException{
        ErsReimbursement reimbursement = context.bodyAsClass(ErsReimbursement.class);

        ersReimbursementService.submitReimbursement(reimbursement);


    }

    public static void resolveReimbursement(Context context) throws JsonProcessingException{
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));

        Integer reimbStatusId =  Integer.parseInt(context.pathParam("statusId"));

        ersReimbursementService.resolveReimbursement(reimbId, reimbStatusId);

        context.result(reimbId.toString()+ " "+ reimbStatusId.toString());

    }






}
