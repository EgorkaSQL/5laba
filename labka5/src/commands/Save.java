package commands;

import managers.CollectionManager;
import managers.XmlManager;

import java.io.IOException;

public class Save extends AbstractCommand
{
    private XmlManager xmlManager;
    private String filePath;

    public Save(CollectionManager collectionManager, XmlManager xmlManager, String filePath)
    {
        super(collectionManager);
        this.xmlManager = xmlManager;
        this.filePath = filePath;
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("save");
        try
        {
            xmlManager.writeToFile(collectionManager.getOrganizations(), filePath);
            System.out.println("Коллекция успешно сохранена в файл " + filePath);
        }
        catch (IOException e)
        {
            System.out.println("Произошла ошибка при записи коллекции в файл " + filePath);
        }
    }
}
