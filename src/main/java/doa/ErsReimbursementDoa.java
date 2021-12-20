package doa;

import models.ErsReimbursement;

import java.util.List;

public interface ErsReimbursementDoa {
    List<ErsReimbursement> getAllReimbursements();
    List<ErsReimbursement> getAllReimbursementsForAnEmployee(Integer reimbAuthor);
    void submitReimbursement(ErsReimbursement reimbursement);
    void resolveReimbursement(Integer reimbId, Integer reimbStatusId);
    List<ErsReimbursement> getAllReumbursementsOfAStatus(Integer reimbStatusId);

}
