package org.example.splitwise.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {
    private long userId;
    private int amount;
    public Pair(long userId,int amount){
        this.userId=userId;
        this.amount=amount;
    }
}
