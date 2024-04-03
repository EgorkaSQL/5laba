package commands;

import managers.CollectionManager;
import mycollection.Organization;

public class RemoveGreater extends AbstractCommand
{
    public RemoveGreater(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("remove_greater");
        try
        {
            long id = Long.parseLong(argument);
            Organization org = collectionManager.getOrganizations().get(id);
            if (org != null)
            {
                collectionManager.removeGreater(org);
                System.out.println("Элементы c AnnualTurnover больше, чем " + org.getAnnualTurnover() + ", удалены из коллекции.");
            }
            else
            {
                System.out.println("Нет элемента с ID " + id + " в коллекции.");
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Аргумент должен быть числом (ID организации). Пожалуйста, попробуйте еще раз.");
        }
    }
}