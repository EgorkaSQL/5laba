package commands;

import managers.CollectionManager;

public abstract class AbstractCommand implements Command
{
    protected CollectionManager collectionManager;

    public AbstractCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
}