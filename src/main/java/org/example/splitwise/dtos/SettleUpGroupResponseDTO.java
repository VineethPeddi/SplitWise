package org.example.splitwise.dtos;

import lombok.Setter;
import org.example.splitwise.models.Transaction;

import java.util.List;

@Setter
public class SettleUpGroupResponseDTO extends BaseResponseDTO{
    private List<Transaction> transactions;
}
