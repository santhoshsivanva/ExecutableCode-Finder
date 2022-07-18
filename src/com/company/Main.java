package com.company;
import java.io.*;
import java.util.Scanner;
public class Main {
    String path;
    File temp;
    Main(String path){
        this.path = path;
    }
    Main(){
        File temp = new File("output-temp.txt");
    }
    void appStart(){
        Find fn = new Find(path);
        fn.numberFinder();
    }
}
class Find {
    static int count = 0;
    static int sequence =1;
    static final String[] arr = {"final","//","/*","import", "package", "void", "{", "}", "class", "public", "protected", "private"};
    File file;
    File temp = new File("output-temp.txt");
    Find(String ftr) {
        this.file = new File(ftr);
    }
    Find(){
    }
    int countLine() throws FileNotFoundException {
        int count=0;
        Scanner in = new Scanner(this.file);
        while(in.hasNextLine()){
            in.nextLine();
            count++;
        }
        return count;
    }
    void numberFinder() {
        try {
            if(temp.createNewFile()){
                System.out.print("outfile created");
            }
            if (this.file.exists()) {
                //BufferedReader extends FileReader
                Scanner sc = new Scanner(this.file);
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));
                String str;
                int value = countLine();
                System.out.println(value);
                while (sc.hasNextLine() && (count!=value)) {
                    str = sc.nextLine();
                    if (isvalid(str)) {
                       bw.append(String.format("%d| %s",sequence,str));
                       bw.newLine();
                       sequence +=1;
                    }
                    else {
                        bw.append(String.format("x| %s",str));
                        bw.newLine();}
//
//                        bw.append(String.format("%d| %s",sequence,str));
//                        bw.newLine();
//                    }
                    count +=1;
                }
                sc.close();
                bw.close();
            } else {
                System.out.println("File doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("The number of executable lines are :"+sequence);
        }
    }
    boolean isvalid(String in) {
        char delimiter = ';';
        boolean bol = true;
        char i = in.charAt(in.length() - 1);
        if(in.contains("//") || in.contains("/*")){
            return false;
        }
//        if ( in.contains("if") || in.contains("for") || in.contains("else") || in.contains("while") || in.contains("switch") || in.contains("case") || in.contains("default") || in.contains("do")) {
//            return true;
//        }
        System.out.println(i);
        for (String temp : arr) {
            System.out.println(in);
            System.out.println(temp);
            if ((in.contains(temp))) {
                if (i==';' || i=='{' || i=='}') {
                    bol= false;
                    break;
                }
            }
            else{
                    bol=true;
            }
        }
        return bol;
    }
}