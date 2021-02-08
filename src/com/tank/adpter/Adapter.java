package com.tank.adpter;

import java.io.*;

public class Adapter {

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\user\\IdeaProjects\\tank\\out\\production\\tank\\com\\tank\\adpter\\a.txt");

            //此时 isr 就是一个adapter 将 stream->reader
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str=br.readLine())!=null) {
                System.out.println(str);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
