package org.example.splitwise.commands;

import org.example.splitwise.controllers.ExpensesController;
import org.example.splitwise.dtos.AddExpenseRequestDTO;
import org.example.splitwise.dtos.AddExpenseResponseDTO;
import org.example.splitwise.dtos.UserExpenseRequestDTO;
import org.example.splitwise.models.ExpenseType;
import org.example.splitwise.services.ExpensesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddExpense implements Command{
    @Override
    public void execute(String input) {
        String[] arr=input.split(" ");
        AddExpenseRequestDTO requestDTO=new AddExpenseRequestDTO();
        int amount=Integer.parseInt(arr[1]);
        requestDTO.setAmount(amount);
        String description=arr[2];
        requestDTO.setDescription(description);
        long groupId=Long.parseLong(arr[3]);
        requestDTO.setGroupId(groupId);
        ExpenseType expenseType=ExpenseType.valueOf(arr[4]);
        requestDTO.setExpenseType(expenseType);
        long createdByUserId=Long.parseLong(arr[5]);
        requestDTO.setCreatedBy(createdByUserId);
        int numUserExpenses=Integer.parseInt(arr[6]);
        Scanner sc=new Scanner(System.in);
        List< UserExpenseRequestDTO> userExpenseRequestDTOS=new ArrayList<>();
        while(numUserExpenses-->0){
            String ip=sc.nextLine();
            String[] ips=ip.split(" ");
            long userId=Long.parseLong(ips[0]);
            int amt=Integer.parseInt(ips[1]);
            String userExpenseType=ips[2];
            userExpenseRequestDTOS.add(new UserExpenseRequestDTO(userId,amt,userExpenseType));
        }
        requestDTO.setUserExpensesRequestDTO(userExpenseRequestDTOS);
        ExpensesController controller=new ExpensesController(new ExpensesService());
        AddExpenseResponseDTO response=controller.addExpense(requestDTO);
        System.out.println(response.getFailureMessage());
    }

}

