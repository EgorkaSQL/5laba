package commands;

import managers.CollectionManager;
import mycollection.OrganizationType;

public class RemoveAllByType extends AbstractCommand
{
    public RemoveAllByType(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("remove_all_by_type");
        try
        {
            OrganizationType type = OrganizationType.valueOf(argument.toUpperCase().trim());
            collectionManager.removeAllByType(type);
            System.out.println("Все организации типа " + type + " были удалены из коллекции.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Неверное значение для OrganizationType. Пожалуйста, введите корректное значение.");
        }
    }
}
