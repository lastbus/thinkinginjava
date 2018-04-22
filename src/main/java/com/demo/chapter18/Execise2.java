package com.demo.chapter18;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author make
 * @creare 21/04/2018
 */
public class Execise2 {

    public static void main(String[] args) {

        SortedDirList sortedDirList = new SortedDirList(".");

        String[] a = sortedDirList.list();
        for (String s : a) {
            System.out.println(s);
        }
        System.out.println("----------");

        String[] b = sortedDirList.list("pom.*");

        for (String s : b) {
            System.out.println(s);
        }


    }

}

class SortedDirList {
    private String dirname;
    private String[] dirItems;

    public SortedDirList(String dirname) {
        this.dirname = dirname;
        File f = new File(dirname);
        if (f.isDirectory()) {
            dirItems = f.list();
            Arrays.sort(dirItems);
        } else {
            dirItems = new String[]{dirname};
        }
    }

    public String[] list() {
        return dirItems;
    }

    public String[] list(String regex) {
        Pattern pattern = Pattern.compile(regex);
        List<String> arrays = new LinkedList<>();
        for (String s : dirItems) {
            if (pattern.matcher(s).matches()) {
                arrays.add(s);
            }
        }
        return arrays.toArray(new String[arrays.size()]);
    }
}
