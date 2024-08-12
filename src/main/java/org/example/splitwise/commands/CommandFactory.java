package org.example.splitwise.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private CommandFactory(){}
    private static CommandFactory instance;
    Map<String,Command> commandMap=new HashMap<>();
    public void addCommand(String commandStr,Command command){
        commandMap.put(commandStr,command);
    }
    public void removeCommand(String commandStr){
        commandMap.remove(commandStr);
    }

    public static CommandFactory getInstance(){
        if(instance==null)instance=new CommandFactory();
        return instance;
    }

    public void execute(String input){
        String str=input.split(" ")[0];
        Command command=commandMap.get(str);
        command.execute(input);
    }
}
