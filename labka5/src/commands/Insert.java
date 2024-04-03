package commands;
import generators.OrganizationGenerator;
import managers.CollectionManager;
import managers.XmlManager;
import mycollection.*;

import java.util.Hashtable;
import java.util.Map;

public class Insert extends AbstractCommand
{
    private XmlManager xmlManager;
    private OrganizationGenerator organizationBuilder;

    public Insert(CollectionManager collectionManager, XmlManager xmlManager, OrganizationGenerator organizationBuilder)
    {
        super(collectionManager);
        this.xmlManager = xmlManager;
        this.organizationBuilder = organizationBuilder;
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("insert");
        Hashtable<Long, Organization> organizationHashtable = organizationBuilder.getInputOrganizations();
        for ( Map.Entry<Long, Organization> entry : organizationHashtable.entrySet())
        {
            collectionManager.insert(entry.getValue());
        }
    }
}