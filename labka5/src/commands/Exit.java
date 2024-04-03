package commands;

import managers.CollectionManager;

public class Exit extends AbstractCommand
{
    public Exit(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("exit");
        collectionManager.exit();
    }
}