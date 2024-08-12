package org.example.splitwise.controllers;

import org.example.splitwise.dtos.ResponseStatus;
import org.example.splitwise.dtos.SettleUpGroupRequestDTO;
import org.example.splitwise.dtos.SettleUpGroupResponseDTO;
import org.example.splitwise.models.Transaction;
import org.example.splitwise.repositories.ExpensesRepository;
import org.example.splitwise.services.ExpensesService;
import org.example.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO request){
        SettleUpGroupResponseDTO response=new SettleUpGroupResponseDTO();
        try{
            List<Transaction> transactions=settleUpService.settleUpGroup(request.getGroupId());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setTransactions(transactions);
        }catch (Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }
        return response;
    }
}
