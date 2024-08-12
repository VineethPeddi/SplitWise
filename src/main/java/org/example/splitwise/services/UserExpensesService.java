package org.example.splitwise.services;

import org.example.splitwise.models.Expense;
import org.example.splitwise.models.UserExpense;
import org.example.splitwise.repositories.UserExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpensesService {

    @Autowired
    private UserExpensesRepository userExpensesRepository;
    public void addUserExpenses(List<UserExpense> userExpenses){
        userExpensesRepository.saveAll(userExpenses);
    }
}
