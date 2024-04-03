package commands;

import managers.CollectionManager;

public class Help extends AbstractCommand
{
    public Help(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("help");
        collectionManager.help();
    }
}
