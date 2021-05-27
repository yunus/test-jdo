package mydomain.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Shirt {

    @PrimaryKey
    @Persistent(valueStrategy= IdGeneratorStrategy.INCREMENT)
    protected long id;

    protected String brand = null;

    protected String size = "M";

    public Shirt(String brand, String size) {
        this.brand = brand;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Shirt{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
