package practice.lab6;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadFiles {

    private static void readZip(String path) throws IOException {
        int count = 0;
        InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
        InputStream inputStream2 = new BufferedInputStream(new FileInputStream(path));
        Charset gbk = Charset.forName("gbk");
        ZipInputStream zin = new ZipInputStream(inputStream, gbk);
        ZipInputStream zin2 = new ZipInputStream(inputStream2, gbk);
        ZipEntry zipEntry;

        while ((zipEntry = zin.getNextEntry()) != null) {
            if (zipEntry.toString().contains("java/io")
                || zipEntry.toString().contains("java/nio")) {
                count++;
            }
        }
        System.out.println("# of .java files in java.io/java.nio: " + count);

        while ((zipEntry = zin2.getNextEntry()) != null) {
            if (zipEntry.toString().contains("java/io")
                || zipEntry.toString().contains("java/nio")) {
                System.out.println(zipEntry.getName());
            }
        }
    }

    private static void readJar(String path) throws IOException {
        int count = 0;
        JarFile jarFile = new JarFile(new File(path));
        JarFile jarFile2 = new JarFile(new File(path));
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String file = jarEntry.getName();
            if (file.contains("java/io") || file.contains("java/nio")) {
                count++;
            }
        }
        System.out.println("# of .class files in java.io/java.nio: " + count);

        Enumeration<JarEntry> entries2 = jarFile2.entries();
        while (entries2.hasMoreElements()) {
            JarEntry jarEntry = entries2.nextElement();
            String file = jarEntry.getName();
            if (file.contains("java/io") || file.contains("java/nio")) {
                System.out.println(file);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String zipPath = "F:\\Sustech-CS209-java2\\src\\practice\\lab6\\src.zip";
        readZip(zipPath);

        System.out.println();

        String jarPath = "F:\\Sustech-CS209-java2\\src\\practice\\lab6\\rt.jar";
        readJar(jarPath);

    }
}
