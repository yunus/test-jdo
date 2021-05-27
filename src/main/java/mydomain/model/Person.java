package mydomain.model;

import javax.jdo.annotations.*;
import java.util.HashSet;
import java.util.Set;

@PersistenceCapable(detachable="true")
public class Person
{
    @PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    Long id;

    String name;

    Integer age=0;

    protected Set<Shoe> shoes = new HashSet<>();

    protected Set<Shirt> shirts = new HashSet<>();

    public Person(long id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Set<Shoe> getShoes() {
        return shoes;
    }

    public Set<Shirt> getShirts() {
        return shirts;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", shoes=" + shoes +
                ", shirts=" + shirts +
                '}';
    }
}
