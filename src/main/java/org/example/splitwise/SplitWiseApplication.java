package org.example.splitwise;

import org.example.splitwise.commands.AddExpense;
import org.example.splitwise.commands.CommandFactory;
import org.example.splitwise.commands.SettleUpGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Set;

/*

AddExpense 100 firstTxn 1 REAL 1 2
2 30 HAD_TO_PAY
3 70 HAD_TO_PAY

 */


@SpringBootApplication
public class SplitWiseApplication {

	public static void run(){
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.println("Enter input");
			String input="AddExpense 100 firstTxn 1 REAL 1 2";
			CommandFactory.getInstance().execute(input);
		}
	}
	public static void initializeCommands(){
		CommandFactory commandFactory=CommandFactory.getInstance();
		commandFactory.addCommand("AddExpense",new AddExpense());
		commandFactory.addCommand("SettleUpGroup",new SettleUpGroup());
	}
	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
		initializeCommands();
		run();
	}
}
