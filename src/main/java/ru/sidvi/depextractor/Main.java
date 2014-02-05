package ru.sidvi.depextractor;

import java.io.File;

/**
 * Created by sidvi on 05.02.14.
 */
public class Main {

    static String processDirectory(String path) {
        String result = "";

        File dir = new File(path);
        File[] jars = dir.listFiles();
        if(jars == null) {
            return result;
        }
        for (File jar : jars) {
            if (jar.getName().endsWith(".jar")) {
                JarProcessor processJar = new JarProcessor(jar.getAbsolutePath()).extract();
                Info info = processJar.getInfo();

                InfoFormatter formatter = new MavenFormatter();
                result += formatter.format(info);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(processDirectory(args[1]));
    }
}
