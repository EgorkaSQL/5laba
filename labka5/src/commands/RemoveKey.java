package commands;

import managers.CollectionManager;

public class RemoveKey extends AbstractCommand
{
    public RemoveKey(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("remove_key");
        try
        {
            long id = Long.parseLong(argument);
            collectionManager.removeKey(id);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Аргумент должен быть числом (ключ организации). Попробуйте еще раз.");
        }
    }
}
