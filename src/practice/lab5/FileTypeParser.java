package practice.lab5;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;


public class FileTypeParser {

    public static String getHeader(String path) throws IOException {
        String[] hex = new String[4];
        try (FileInputStream inputStream = new FileInputStream(path)) {
            byte[] bytes = new byte[4];
            int byteNum = inputStream.read(bytes);
            // bytes to hex
            String tmp;
            for (int i = 0; i < byteNum; i++) {
                tmp = Integer.toHexString(bytes[i] & 0xFF).toLowerCase();
                if (tmp.length() < 2) {
                    hex[i] = "0" + tmp;
                } else {
                    hex[i] = tmp;
                }
            }
            inputStream.close();
            return Arrays.toString(hex);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String file = args[0];
        System.out.println("Filename: " + file);
        String header = getHeader(file);
        String type = "";
        if (header.equals("[89, 50, 4e, 47]")) {
            type = "png";
        } else if (header.equals("[50, 4b, 03, 04]")) {
            type = "zip or jar";
        } else if (header.equals("[ca, fe, ba, be]")) {
            type = "class";
        }
        System.out.println("File Header(HEX): " + header);
        System.out.println("File Type: " + type);
    }

}
