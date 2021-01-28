package Tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.ref.PhantomReference;

@DatabaseTable(tableName = "city")
public class City {

    @DatabaseField(generatedId = true)
    private int id;
    public static final String ID = "ID";

    @DatabaseField(columnName = NAME, defaultValue = "empty")
    private String Name;
    public static final String NAME = "Name";

    @DatabaseField(columnName = COUNTRYCODE, defaultValue = "empty")
    private String countryCode;
    public static final String COUNTRYCODE = "Countrycode";

    @DatabaseField(columnName = DISTRICT, defaultValue = "empty")
    private String district;
    public static final String DISTRICT = "District";

    @DatabaseField(columnName = POPULATION, defaultValue = "0")
    private String population;
    public static final String POPULATION = "Population";

    public City(String name, String countryCode, String district, String population) {
        Name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public City(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getPopulation() {
        return population;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(String population) {
        this.population = population;
    }


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}


