package services;

import doa.ErsReimbursementDoa;
import doa.ErsReimbursementDoaImpl;
import models.ErsReimbursement;

import java.util.List;

public class ErsReimbursementService {
    ErsReimbursementDoa ersReimbursementDoa;

    public ErsReimbursementService(ErsReimbursementDoa ersReimbursementDoa){

        this.ersReimbursementDoa = ersReimbursementDoa;
    }

    public ErsReimbursementService(){

        this.ersReimbursementDoa = new ErsReimbursementDoaImpl();
    }

    public List<ErsReimbursement> getAllReimbursements(){

        return ersReimbursementDoa.getAllReimbursements();
    }

    public List<ErsReimbursement> getAllReimbursementsForAnEmployee(Integer reimbAuthor){
        return ersReimbursementDoa.getAllReimbursementsForAnEmployee(reimbAuthor);
    }

    public void submitReimbursement(ErsReimbursement reimbursement) {
        ersReimbursementDoa.submitReimbursement(reimbursement);
    }

    public List<ErsReimbursement> getAllReumbursementsOfAStatus(Integer reimbStatusId){
        return ersReimbursementDoa.getAllReumbursementsOfAStatus(reimbStatusId);
    }

    public void resolveReimbursement(Integer reimbId, Integer reimbStatusId){
        ersReimbursementDoa.resolveReimbursement(reimbId, reimbStatusId);

        //System.out.println(reimbId + " " + reimbStatusId);
    }
}
