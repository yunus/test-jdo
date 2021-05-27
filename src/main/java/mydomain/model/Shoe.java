package mydomain.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Shoe {
    @PrimaryKey
    @Persistent(valueStrategy= IdGeneratorStrategy.INCREMENT)
    protected long id;

    protected String brand = null;


    protected int size = 5;

    public Shoe(String brand, int size) {
        this.brand = brand;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", size=" + size +
                '}';
    }
}
