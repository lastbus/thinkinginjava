package com.demo.codehaus;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author make
 * @creare 26/09/2018
 */
public class JacksonTester {
    public static void main(String args[]){
        JacksonTester tester = new JacksonTester();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
            System.out.println(jsonString);
            JsonNode rootNode = mapper.readTree(jsonString);

            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: "+ nameNode.getTextValue());

            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.getIntValue());

            JsonNode verifiedNode = rootNode.path("verified");
            System.out.println("Verified: " + (verifiedNode.getBooleanValue() ? "Yes":"No"));

            JsonNode marksNode = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode.getElements();
            System.out.print("Marks: [ ");
            while (iterator.hasNext()) {
                JsonNode marks = iterator.next();
                System.out.print(marks.getIntValue() + " ");
            }
            System.out.println("]");
            System.out.println("---------------------------------");
            Iterator<String> it = rootNode.getFieldNames();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println("++++++++++++++++++++++++++++++++++");
            Iterator<JsonNode> nodes = rootNode.getElements();
            while (nodes.hasNext()) {
                JsonNode j = nodes.next();
                System.out.println(j.toString());
                System.out.println("array : " + j.isArray());
                System.out.println("contains node: " + j.isContainerNode());
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Student {
    private String name;
    private int age;
    public Student(){}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return "Student [ name: "+name+", age: "+ age+ " ]";
    }
}