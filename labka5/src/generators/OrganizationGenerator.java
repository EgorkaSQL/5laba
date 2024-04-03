package generators;
import mycollection.*;

import java.util.*;

public class OrganizationGenerator extends AbstractGenerator
{
    private AddressGenerator addressGenerator = new AddressGenerator();
    private CoordinateGenerator coordinatesGenerator = new CoordinateGenerator();
    private OrganizationTypeGenerator organizationTypeGenerator = new OrganizationTypeGenerator();

    public  Hashtable<Long, Organization> getInputOrganizations()
    {
        Hashtable<Long, Organization> organizations = new Hashtable<>();
        System.out.println("Введите подробности об организации:");

        String name = getValidatedString("Введите имя организации: ", 200);

        Coordinates coordinates = coordinatesGenerator.createCoordinatesFromUserInput();

        int annualTurnover = getValidatedInt("Введите годовой оборот: ", 1, null);

        OrganizationType type = organizationTypeGenerator.inputOrganizationType();

        Address officialAddress = addressGenerator.createAddressFromUserInput();

        Organization organization = new Organization(name, coordinates, annualTurnover, type, officialAddress);

        try
        {
            coordinates.check();
            officialAddress.getTown().check();
            officialAddress.check();
            organization.check();
            organizations.put(organization.getId(), organization);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Неверный ввод: " + e.getMessage());
        }
        return organizations;
    }
}