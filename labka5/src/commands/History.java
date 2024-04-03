package commands;

import managers.CollectionManager;

public class History extends AbstractCommand
{
    public History(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("history");
        System.out.println("Последние " + Math.min(collectionManager.getCommandHistory().size(), 13) + " команд:");
        for (String command : collectionManager.getCommandHistory().values())
        {
            System.out.println(command);
        }
    }
}
