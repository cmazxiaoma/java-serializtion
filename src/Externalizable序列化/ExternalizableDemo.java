package Externalizable序列化;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalizableDemo {

    public static void main(String[] args) throws IOException {
        File fileName = new File("externalizable.txt");
        FileOutputStream fos = new FileOutputStream(fileName);
        FileInputStream fis = new FileInputStream(fileName);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        ObjectInputStream is = new ObjectInputStream(fis);

        try {
            Person person = new Person("cmazxiaoma", 21);
            os.writeObject(person);
            os.writeObject(person);
            Person newPerson = (Person) is.readObject();
            System.out.println(newPerson);
            System.out.println("两个person对象引用是否相等 :" + person == newPerson + "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(is);
            close(os);
            close(fis);
            close(fos);
        }
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
