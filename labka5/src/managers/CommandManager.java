package managers;
import commands.*;
import generators.OrganizationGenerator;

import java.util.*;

public class CommandManager {
    private Hashtable<String, Command> commands;

    public CommandManager(CollectionManager collectionManager)
    {
        this.commands = new Hashtable<>();
        XmlManager xmlManager = new XmlManager();
        OrganizationGenerator organizationBuilder = new OrganizationGenerator();
        commands.put("help", new Help(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("exit", new Exit(collectionManager));
        commands.put("history", new History(collectionManager));
        commands.put("insert", new Insert(collectionManager, xmlManager, organizationBuilder));
        commands.put("update", new Update(collectionManager, organizationBuilder));
        commands.put("remove_key", new RemoveKey(collectionManager));
        commands.put("save", new Save(collectionManager, xmlManager, "organizations.xml"));
        commands.put("remove_all_by_type", new RemoveAllByType(collectionManager));
        commands.put("count_less_than_annual_turnover", new CountLessThanAnnualTurnover(collectionManager));
        commands.put("filter_by_annual_turnover", new FilterByAnnualTurnover(collectionManager));
        commands.put("remove_greater", new RemoveGreater(collectionManager));
        commands.put("replace_if_greater", new ReplaceIfGreater(collectionManager, xmlManager, organizationBuilder));
        commands.put("execute_script", new ExecuteScript(collectionManager, this));
    }

    public void executeCommand(String line)
    {
        String[] parts = line.trim().split("\\s+", 2); // разбивает строку на команду и аргументы
        String commandName = parts[0];
        String argument;
        if (parts.length > 1)
        {
            argument = parts[1];
        }
        else
        {
            argument = "";
        }

        Command command = commands.get(commandName);
        if (command != null)
        {
            command.execute(argument);
        }
        else
        {
            System.out.println("Команда не найдена.");
        }
    }
}