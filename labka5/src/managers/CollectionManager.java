package managers;

import mycollection.Organization;
import mycollection.OrganizationType;

import java.time.LocalDate;
import java.util.*;

public class CollectionManager
{
    private Hashtable<Long, Organization> organizations;
    private LocalDate creationDate;
    private String filePath;

    public CollectionManager(Hashtable<Long, Organization> organizations,  String filePath)
    {
        this.organizations = organizations;
        this.creationDate = LocalDate.now();
        this.filePath = filePath;
        loadData(filePath);
    }

    private void loadData(String fileName)
    {
        XmlManager xmlManager = new XmlManager();
        organizations = xmlManager.readFromFile(fileName);
        System.out.println("Коллекция загружена из файла " + fileName);
    }

    public String getFilePath()
    {
        return filePath;
    }

    public boolean containsId(long id)
    {
        return organizations.containsKey(id);
    }

    public void help()
    {
        System.out.println("Введите одну из следующих команд:\n" +
                "help : вывести справку по доступным командам\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null {element} : добавить новый элемент с заданным ключом\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "history : вывести последние 13 команд (без их аргументов)\n" +
                "replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого\n" +
                "remove_all_by_type type : удалить из коллекции все элементы, значение поля type которого эквивалентно заданному\n" +
                "count_less_than_annual_turnover annualTurnover : вывести количество элементов, значение поля annualTurnover которых меньше заданного\n" +
                "filter_by_annual_turnover annualTurnover : вывести элементы, значение поля annualTurnover которых равно заданному");
    }

    public Hashtable<Long, Organization> getOrganizations()
    {
        return organizations;
    }

    public void info()
    {
        System.out.println("Тип коллекции: " + organizations.getClass().getName() + "\nДата инициализации: " + creationDate + "\nКоличество элементов: " + organizations.size());
    }

    public void show()
    {
        if(organizations.isEmpty())
        {
            System.out.println("Коллекция пуста.");
        }
        else
        {
            for(Organization organization : organizations.values())
            {
                System.out.println(organization);
            }
        }
    }

    public void insert(Organization organization)
    {
        {
            long id = 1;
            while (organizations.containsKey(id))
            {
                id += 1;
            }
            organization.setId(id);
            organizations.put(id, organization);
            System.out.println("Добавлен элемент с ID " + id);
        }
    }

    public void update(Long id, Organization organization)
    {
        if (organizations.containsKey(id))
        {
            organizations.put(id, organization);
            System.out.println("Элемент с ID " + id + " обновлен.");
        }
        else
        {
            System.out.println("Элемент с ID " + id + " не найден. Обновление невозможно.");
        }
    }

    public void removeKey(Long id)
    {
        if (organizations.containsKey(id))
        {
            organizations.remove(id);
            System.out.println("Элемент с ID " + id + " удален.");
        }
        else
        {
            System.out.println("Элемент с ID " + id + " не найден. Удаление невозможно.");
        }
    }

    public void clear()
    {
        organizations.clear();
        System.out.println("Коллекция была очищена.");
    }

    public void exit()
    {
        System.out.println("Выход из программы.");
        System.exit(0);
    }

    public void removeGreater(Organization organization)
    {
        Comparator<Organization> comparator = Organization.getAnnualTurnoverComparator();

        for (Iterator<Map.Entry<Long, Organization>> iterator = organizations.entrySet().iterator(); iterator.hasNext();)
        {
            Map.Entry<Long, Organization> entry = iterator.next(); // Получаем следующую запись из Iterator. Запись представляет собой пару ключ-значение.
            if (comparator.compare(entry.getValue(), organization) > 0)
            {
                iterator.remove();
            }
        }
    }

    private final Map<Integer, String> commandHistory = new LinkedHashMap<>();
    private int commandCounter = 0;

    public void addToHistory(String command)
    {
        if (commandCounter >= 13)
        {
            Integer firstKey = commandHistory.keySet().iterator().next();
            commandHistory.remove(firstKey);
        }
        commandHistory.put(commandCounter++, command);
    }

    public Map<Integer, String> getCommandHistory()
    {
        return commandHistory;
    }

    public void removeAllByType(OrganizationType type)
    {
        int sizeBefore = organizations.size();
        organizations.values().removeIf(organization -> organization.getType().equals(type));
        int sizeAfter = organizations.size();

        if (sizeBefore > sizeAfter)
        {
            System.out.println("Были удалены элементы. Размер коллекции уменьшился на " + (sizeBefore - sizeAfter));
        }
        else
        {
            System.out.println("Удаление элементов не произошло. Размер коллекции не изменился.");
        }
    }

    public int countLessThanAnnualTurnover(int annualTurnover)
    {
        int count = 0;
        for (Organization organization : organizations.values())
        {
            if (organization.getAnnualTurnover() < annualTurnover)
            {
                count++;
            }
        }
        return count;
    }

    public void filterByAnnualTurnover(int annualTurnover)
    {
        for (Organization organization : organizations.values())
        {
            if (organization.getAnnualTurnover() == annualTurnover)
            {
                System.out.println(organization);
            }
        }
    }
}
