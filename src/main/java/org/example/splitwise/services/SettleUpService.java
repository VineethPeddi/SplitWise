package org.example.splitwise.services;

import org.example.splitwise.models.*;
import org.example.splitwise.repositories.ExpensesRepository;
import org.example.splitwise.repositories.GroupRepository;
import org.example.splitwise.repositories.UserExpensesRepository;
import org.example.splitwise.strategies.PricingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    @Autowired
    private ExpensesRepository expensesRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserExpensesRepository userExpensesRepository;

    @Autowired
    private PricingStrategyFactory pricingStrategyFactory;
    public List<Transaction> settleUpGroup(long groupId) throws RuntimeException{
        Optional<Group> groupOptional=groupRepository.findById(groupId);
        if (groupOptional.isEmpty())throw new RuntimeException("Group not found");
        Group group=groupOptional.get();
        List<Expense> expenses=expensesRepository.findAllByGroup(group);
        HashMap<Long,Integer> mp=new HashMap<>();
        for (Expense expense:expenses){
            long userId=expense.getCreatedBy().getId();
            int prev=0;
            if (mp.containsKey(userId)){
                prev=mp.get(userId);
            }
            mp.put(userId,prev+ expense.getAmount());
            List<UserExpense> userExpenses=userExpensesRepository.findAllByExpense(expense);
            for (UserExpense userExpense:userExpenses){
                long uid=userExpense.getUser().getId();
                prev=0;
                if(mp.containsKey(uid))prev=mp.get(uid);
                mp.put(uid,prev+userExpense.getAmount());
            }
        }
        return pricingStrategyFactory.getPricingStrategy(Strategies.TWO_POINTER).settleUp(mp);
    }
}
