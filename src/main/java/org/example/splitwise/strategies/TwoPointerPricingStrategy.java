package org.example.splitwise.strategies;

import org.example.splitwise.models.User;
import org.example.splitwise.repositories.UserRepository;
import org.example.splitwise.utils.Pair;
import org.example.splitwise.models.Transaction;
import org.example.splitwise.utils.PairComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.*;

@Component
public class TwoPointerPricingStrategy implements PricingStrategy{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Transaction> settleUp(Map<Long, Integer> mp) {
        List<Pair> list=new ArrayList<>();
        for (Map.Entry<Long,Integer> entry:mp.entrySet()){
            list.add(new Pair(entry.getKey(),entry.getValue()));
        }
        list.sort(new PairComparator());
        for (Pair pair:list){
            if(pair.getAmount()<0)pair.setAmount(-1*pair.getAmount());
        }
        int n=list.size();
        int p1=0,p2=n-1;
        List<Transaction> txns=new ArrayList<>();
        while(p1<p2){
            int amt1=list.get(p1).getAmount();
            int amt2=list.get(p2).getAmount();
            Optional<User> lenderOptional=userRepository.findById(list.get(p1).getUserId());
            Optional<User> borrowerOptional=userRepository.findById(list.get(p2).getUserId());
            Transaction transaction;
            if(amt1<amt2){
                transaction=new Transaction(lenderOptional.get(),borrowerOptional.get(),amt1);
                Pair pair=list.get(p2);
                pair.setAmount(amt2-amt1);
                p1++;
            }else if (amt1>amt2){
                transaction=new Transaction(lenderOptional.get(),borrowerOptional.get(),amt2);
                Pair pair=list.get(p1);
                pair.setAmount(amt1-amt2);
                p2--;
            }else{
                transaction=new Transaction(lenderOptional.get(),borrowerOptional.get(),amt2);
                p1++;
                p2--;
            }

            txns.add(transaction);
        }
        return txns;
    }
}
