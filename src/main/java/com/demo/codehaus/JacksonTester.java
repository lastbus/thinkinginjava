package com.demo.codehaus;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;


import java.io.IOException;
import java.util.Iterator;

/**
 * @author make
 * @creare 26/09/2018
 */
public class JacksonTester {
    public static void main(String args[]){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        //map json to student
        try {
            Student student = mapper.readValue(jsonString, Student.class);
            System.out.println(student);
            mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(student);
            System.out.println(jsonString);

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
