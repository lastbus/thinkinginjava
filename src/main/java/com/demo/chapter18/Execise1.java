package com.demo.chapter18;

import net.mindview.util.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @author make
 * @creare 21/04/2018
 */
public class Execise1 {

    public static void main(String[] args) {

        File file = new File(".");
        String[] list;
        if (args.length == 0) {
            list = file.list();
        } else {
            list = file.list(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    File f = new File(dir + File.separator + name);

                    if (f.isDirectory()) {
                        return false;
                    }

                    TextFile textFile = new TextFile(name);
                    Iterator<String> itr = textFile.iterator();
                    while (itr.hasNext()) {
                        String t = itr.next();
                        for (String arg : args) {
                            if (t.contains(arg)) {
                                return true;
                            }
                        }
                    }

                    textFile.clear();
                    return false;
                }
            });
        }

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
