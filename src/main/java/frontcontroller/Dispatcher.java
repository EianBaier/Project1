package frontcontroller;

import controllers.ErsReimbursementController;
import controllers.ErsUsersController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {

    public Dispatcher(Javalin app){





        app.routes(() ->{
           path("check-session", () ->{
               get(ErsUsersController::checkSession);
           });

            path("logout", () ->{
                delete(ErsUsersController::logout);
            });

            path("login", () ->{
                post(ErsUsersController::getAUser);
            });

          /* path("login", () ->{
               path("{username}",() ->{
                   path("{password}", () ->{
                      post(ErsUsersController::getAUser);

                   });
               });
            });*/

           path("resolve", () ->{
               path("{reimbId}", () ->{
                   path("{statusId}", () ->{
                       patch(ErsReimbursementController::resolveReimbursement);
                   });
               });
           });
           path("reimbursements", () ->{
              get(ErsReimbursementController::getAllReimbursements);
              post(ErsReimbursementController::submitAReimbursement);
              path("{id}", () ->{
                    get(ErsReimbursementController::getAllReimbursementsForAnEmployee);

              });
              path("status",() ->{
                  path("{statusid}", () ->{
                      get(ErsReimbursementController::getAllReimbursementsByStatusID);
                  });
              });

           });
        });
    }
}
