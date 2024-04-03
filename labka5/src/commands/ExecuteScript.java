package commands;

import managers.CollectionManager;
import managers.CommandManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class ExecuteScript extends AbstractCommand
{
    private CommandManager commandManager;
    private Hashtable<String, Boolean> scriptsBeingExecuted = new Hashtable<>(); // Список выполняемых скриптов

    public ExecuteScript(CollectionManager collectionManager, CommandManager commandManager)
    {
        super(collectionManager);
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("execute_script");
        File file = new File(argument);
        if (!file.exists())
        {
            System.out.println("Файл " + argument + " не существует!");
            return;
        }

        if (scriptsBeingExecuted.containsKey(argument)) //Проверка присутствует ли argument ключ в scriptsBeingExecuted
        {
            System.out.println("Ошибка: обнаружена попытка рекурсивного вызова execute_script " + argument);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            scriptsBeingExecuted.put(argument, true);

            String line;
            while ((line = reader.readLine()) != null)
            {
                commandManager.executeCommand(line);
            }
        }
        catch (IOException e)
        {
            System.out.println("Произошла ошибка при чтении или записи файла " + argument);
        }
        finally
        {
            scriptsBeingExecuted.remove(argument);
        }
    }
}