package Externalizable序列化;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person implements Externalizable {

    private String name;
    private int age;

    public Person(String name, int age) {
        System.out.println("有参数的构造器");
        this.name = name;
        this.age = age;
    }

    public Person() {
        System.out.println("无参数的构造器");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
