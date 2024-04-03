package commands;
import generators.OrganizationGenerator;
import managers.CollectionManager;
import mycollection.*;

import java.util.Hashtable;

public class Update extends AbstractCommand
{

    private OrganizationGenerator organizationBuilder;

    public Update(CollectionManager collectionManager, OrganizationGenerator organizationBuilder)
    {
        super(collectionManager);
        this.organizationBuilder = organizationBuilder;
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("update");
        try
        {
            long id = Long.parseLong(argument);

            if (collectionManager.containsId(id))
            {
                Hashtable<Long, Organization> organizationHashtable = organizationBuilder.getInputOrganizations();
                Organization updatedOrganization = organizationHashtable.values().iterator().next();

                updatedOrganization.setId(id); // Set the id of the updatedOrganization to the existing id
                collectionManager.update(id, updatedOrganization);
            }
            else
            {
                System.out.println("Нет элемента с ID " + id + " в коллекции.");
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Аргумент должен быть числом (ID организации). Попробуйте еще раз.");
        }
    }
}