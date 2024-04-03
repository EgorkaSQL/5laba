package commands;

import managers.CollectionManager;

public class Clear extends AbstractCommand
{
    public Clear(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("clear");
        collectionManager.clear();
    }
}
