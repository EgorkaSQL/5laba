package commands;

import managers.CollectionManager;

public class FilterByAnnualTurnover extends AbstractCommand
{
    public FilterByAnnualTurnover(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("filter_by_annual_turnover");
        try
        {
            int annualTurnover = Integer.parseInt(argument);
            collectionManager.filterByAnnualTurnover(annualTurnover);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Аргумент должен быть числом. Пожалуйста, попробуйте еще раз.");
        }
    }
}
