package org.example.splitwise.controllers;

import org.example.splitwise.dtos.AddExpenseRequestDTO;
import org.example.splitwise.dtos.AddExpenseResponseDTO;
import org.example.splitwise.dtos.ResponseStatus;
import org.example.splitwise.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpensesController {
    private final ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }
    public AddExpenseResponseDTO addExpense(AddExpenseRequestDTO request){
        AddExpenseResponseDTO response=new AddExpenseResponseDTO();
        try{
            response.setId(expensesService.addExpense(request.getAmount(),request.getDescription(), request.getCreatedBy(), request.getUserExpensesRequestDTO(),request.getGroupId(),request.getExpenseType()).getId());
            response.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }
        return response;
    }
}
