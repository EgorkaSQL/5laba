/**
 * Класс команды "info".
 * Описание команды: вывести в стандартный поток вывода информацию о коллекции (только CollectionManager)
 */
package commands;

import managers.CollectionManager;

public class Info extends AbstractCommand
{
    public Info(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("info");
        collectionManager.info();
    }
}