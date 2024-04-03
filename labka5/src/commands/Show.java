package commands;

import managers.CollectionManager;

public class Show extends AbstractCommand
{
    public Show(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("show");
        collectionManager.show();
    }
}
