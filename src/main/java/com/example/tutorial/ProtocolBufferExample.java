package com.example.tutorial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.example.tutorial.PersonProtos.Person;

public class ProtocolBufferExample {
  static public void main(String[] argv) throws Exception {
    Person person1 = Person.newBuilder().setName("make").setId(1).build();
    FileOutputStream out = new FileOutputStream("example.txt");
    person1.writeTo(out);
    FileInputStream in = new FileInputStream("example.txt");
    Person p2 = Person.parseFrom(in);
    System.out.print(p2);
  }
}
