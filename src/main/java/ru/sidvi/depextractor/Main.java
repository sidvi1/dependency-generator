package ru.sidvi.depextractor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sidvi on 05.02.14.
 */
public class Main {

    static int count = 0;
    static int countSuccess = 0;
    static Map<String,String> notFullyProcessed = new HashMap<String, String>();

    static String processDirectory(String path) {
        String result = "";

        File dir = new File(path);
        File[] jars = dir.listFiles();
        if(jars == null) {
            return result;
        }
        for (File jar : jars) {
            if (jar.getName().endsWith(".jar")) {
                count++;
                JarProcessor processJar = new JarProcessor(jar.getAbsolutePath()).extract();
                Info info = processJar.getInfo();
                if(info.isFullFilled()){
                    countSuccess++;
                }else{
                    //TODO: replace NotFound
                    notFullyProcessed.put(jar.getName(),"Not found");
                }

                InfoFormatter formatter = new MavenFormatter();
                result += formatter.format(info);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Укажите путь к jar файлам.");
        }

        System.out.println(processDirectory(args[0]));

        System.out.println("Founded: "+ count);
        System.out.println("Successfully extracted: "+ countSuccess);

        System.out.println("List of not fully extracted: ");
        for (String file: notFullyProcessed.keySet()) {
            System.out.println(FormatterUtils.pad(1) + file + " : " + notFullyProcessed.get(file));
        }


    }
}
