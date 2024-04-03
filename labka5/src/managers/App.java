package managers;
import mycollection.*;

import java.util.Hashtable;
import java.util.Scanner;
public class App
{
    private CollectionManager collectionManager;
    private CommandManager commandManager;

    public App()
    {
        Hashtable<Long, Organization> organizations = new Hashtable<>();
        String filePath = System.getenv("ORGANIZATIONS_FILE");

        if (filePath == null || filePath.isEmpty())
        {
            filePath = "organizations.xml";
            System.out.println("Переменная окружения ORGANIZATIONS_FILE не установлена. Используется файл по умолчанию: " + filePath);
        }
        else
        {
            System.out.println("Используется файл, указанный в переменной окружения ORGANIZATIONS_FILE: " + filePath);
        }
        collectionManager = new CollectionManager(organizations, filePath);
        commandManager = new CommandManager(collectionManager);
    }

    public void run()
    {
        System.out.println("help - вывод всех команд");

        try (Scanner scanner = new Scanner(System.in))
        {
            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                commandManager.executeCommand(line);
            }
        }
        catch (IllegalStateException e)
        {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}
