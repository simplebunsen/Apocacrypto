package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String action = "enc";
        String message = "";
        int key = 0;
        String outFile = "";

        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            switch (s) {
                case "-mode":
                    action = args[++i];
                    //System.out.println("loaded mode: " + action);
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    //System.out.println("loaded key: " + key);
                    break;
                case "-data":
                    message = args[++i];
                    //System.out.println("loaded date from string");
                    break;
                case "-in":
                    if(message.isEmpty()){
                        String input = "";
                        try {
                            input = new String(Files.readAllBytes(Paths.get(".\\" + args[++i])));
                        } catch (IOException e) {
                            System.out.println("Error: Input cant be read properly");
                            e.printStackTrace();
                        }
                        //System.out.println("we loaded message: " + input);
                        message = input;
                    }
                    break;
                case "-out":
                    outFile = args[++i];
                    //System.out.println("made out file name : " + outFile);
                    break;
            }
        }

        String result;
        switch(action){
            case "enc":
                result = encrypt(message, key);
                break;
            case "dec":
                result = decrypt(message, key);
                break;
            default:
                throw new IllegalStateException("Error: Unexpected value: " + action);
        }

        outputResult(result, outFile);
    }

    private static void outputResult(String result, String outFile) {
        if(outFile.isEmpty()){
            //System.out.println("output file name empty, printing result here");
            System.out.println(result);
            return;
        }
        //System.out.println("we have an output file name, saving to: " + outFile);
        File file = new File(".\\" + outFile);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(result);
            //System.out.println("printed");
        } catch (IOException e) {
            System.out.printf("Error: An exception occurs %s", e.getMessage());
        }
    }

    private static String decrypt(String message, int key) {
        String output = "";

        for(char c : message.toCharArray()){
            //97 - 122 lowercase letters
            //32 - 126 basic symbols
            if(c <= 126 && c >= 32){
                char next;
                if(c - key < 0){
                    next = (char) (c - key + 95);
                } else {
                    next = (char) (c - key);
                }
                output = output.concat(String.valueOf(next));
            }else{
                output = output.concat(String.valueOf(c));
            }
        }
        return output;
    }

    private static String encrypt(String message, int key) {
        String output = "";

        for(char c : message.toCharArray()){
            //97 - 122 lowercase letters
            //32 - 126 basic symbols
            if(c <= 126 && c >= 32){
                char next;
                if(c + key > 126){
                    next = (char) (c + key - 95);
                } else {
                    next = (char) (c + key);
                }
                output = output.concat(String.valueOf(next));
            }else{
                output = output.concat(String.valueOf(c));
            }
        }
        return output;
    }
}
