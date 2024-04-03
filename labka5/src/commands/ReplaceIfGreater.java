package commands;

import generators.OrganizationGenerator;
import managers.CollectionManager;
import managers.XmlManager;
import mycollection.*;

import java.util.Hashtable;

public class ReplaceIfGreater extends AbstractCommand
{
    private XmlManager xmlManager;
    private OrganizationGenerator organizationBuilder;

    public ReplaceIfGreater(CollectionManager collectionManager, XmlManager xmlManager, OrganizationGenerator organizationBuilder)
    {
        super(collectionManager);
        this.xmlManager = xmlManager;
        this.organizationBuilder = organizationBuilder;
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("replace_if_greater");
        try
        {
            long id = Long.parseLong(argument);

            Hashtable<Long, Organization> organizationHashtable = organizationBuilder.getInputOrganizations();
            Organization newOrganization = organizationHashtable.values().iterator().next();

            if (collectionManager.containsId(id))
            {
                Organization existingOrganization = collectionManager.getOrganizations().get(id);

                if (newOrganization.compareTo(existingOrganization) > 0)
                {
                    collectionManager.update(id, newOrganization);
                    System.out.println("Значение по ключу " + id + " было заменено на новое.");
                }
                else
                {
                    System.out.println("Новое значение не больше старого. Замена не произведена.");
                }
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
