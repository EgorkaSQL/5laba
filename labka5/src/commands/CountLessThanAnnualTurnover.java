package commands;

import managers.CollectionManager;

public class CountLessThanAnnualTurnover extends AbstractCommand
{
    public CountLessThanAnnualTurnover(CollectionManager collectionManager)
    {
        super(collectionManager);
    }

    @Override
    public void execute(String argument)
    {
        collectionManager.addToHistory("count_less_than_annual_turnover");
        try
        {
            int annualTurnover = Integer.parseInt(argument);
            int count = collectionManager.countLessThanAnnualTurnover(annualTurnover);
            System.out.println("Количество элементов со значением annualTurnover меньше " + annualTurnover + ": " + count);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Аргумент должен быть числом. Пожалуйста, попробуйте еще раз.");
        }
    }
}
