package org.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
@Entity(name = "expenses")
public class Expense extends BaseModel{
    private int amount;
    private String description;

    @ManyToOne
    private Group group;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;

    @ManyToOne
    private User createdBy;
}
