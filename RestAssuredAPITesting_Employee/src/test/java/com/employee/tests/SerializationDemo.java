package com.employee.tests;

import java.io.*;

/**
 * @author vloparevich
 **/
public class SerializationDemo implements Serializable {
    int i = 10;
    int j = 20;


}

class SerializationAndDeserialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SerializationDemo firstObject = new SerializationDemo();

        // Serialization
        FileOutputStream fos = new FileOutputStream("test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(firstObject);

        //Deserialization
        File file = new File("test.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializationDemo secondObject = (SerializationDemo) ois.readObject();
        System.out.println(secondObject.i + " " + secondObject.j);
    }
}
