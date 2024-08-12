package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.models.ExpenseType;
import org.example.splitwise.models.User;
import org.example.splitwise.models.UserExpense;

import java.util.List;

@Getter
@Setter
public class AddExpenseRequestDTO {
    private int amount;
    private String description;

    private long groupId;

    private ExpenseType expenseType;

    List<UserExpenseRequestDTO> userExpensesRequestDTO;
    private long createdBy;
}
