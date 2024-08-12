package org.example.splitwise.services;

import org.example.splitwise.dtos.UserExpenseRequestDTO;
import org.example.splitwise.models.*;
import org.example.splitwise.repositories.ExpensesRepository;
import org.example.splitwise.repositories.GroupRepository;
import org.example.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserExpensesService userExpensesService;

    @Autowired
    private UserRepository userRepository;

    public Expense addExpense(int amount, String description, long paidByUserId, List<UserExpenseRequestDTO> userExpensesRequestDTOs, long groupId, ExpenseType expenseType) throws RuntimeException{

        List<UserExpense> userExpenses=new ArrayList<>();
        for (UserExpenseRequestDTO requestDTO:userExpensesRequestDTOs){
            long userId=requestDTO.getUserId();
            Optional<User> userOptional=userRepository.findById(userId);
            if (userOptional.isEmpty())throw new RuntimeException("User not found");
            UserExpense userExpense=new UserExpense();
            userExpense.setUser(userOptional.get());
            userExpense.setAmount(requestDTO.getAmount());
            userExpense.setUserExpenseType(UserExpenseType.valueOf(requestDTO.getUserExpenseType()));
            userExpenses.add(userExpense);
        }

        Optional<Group> groupOptional=groupRepository.findById(groupId);
        if (groupOptional.isEmpty())throw new RuntimeException("Group not found");
        Optional<User> userOptional=userRepository.findById(paidByUserId);
        if (userOptional.isEmpty())throw new RuntimeException("User not found");


        Expense expense=new Expense();
        expense.setGroup(groupOptional.get());
        expense.setExpenseType(expenseType);
        expense.setAmount(amount);
        expense.setDescription(description);
        expense=expensesRepository.save(expense);
        UserExpense userExpenseFromExpense=new UserExpense();
        userExpenseFromExpense.setAmount(amount);
        userExpenseFromExpense.setUserExpenseType(UserExpenseType.PAID_BY);
        userExpenseFromExpense.setUser(userOptional.get());
        userExpenses.add(userExpenseFromExpense);
        for (UserExpense userExpense:userExpenses){
            long userId=userExpense.getUser().getId();
            userOptional=userRepository.findById(userId);
            if (userOptional.isEmpty())throw new RuntimeException("User not found");
            userExpense.setExpense(expense);
        }

        userExpensesService.addUserExpenses(userExpenses);
        return expense;
    }

}
