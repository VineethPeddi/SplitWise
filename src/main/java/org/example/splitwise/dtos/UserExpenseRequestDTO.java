package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExpenseRequestDTO {
    private long userId;
    private int amount;
    private String userExpenseType;
    public UserExpenseRequestDTO(long userId,int amount,String userExpenseType){
        this.userId=userId;
        this.amount=amount;
        this.userExpenseType=userExpenseType;
    }
}
