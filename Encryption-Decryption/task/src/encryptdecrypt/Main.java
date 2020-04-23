package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String action = "enc";
        String message = "";
        int key = 0;

        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            switch (s) {
                case "-mode":
                    action = args[++i];
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    break;
                case "-data":
                    message = args[++i];
                    break;
            }
        }


        switch(action){
            case "enc":
                encrypt(message, key);
                break;
            case "dec":
                decrypt(message, key);
                break;
        }



    }

    private static void decrypt(String message, int key) {
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
        System.out.println(output);

    }

    private static void encrypt(String message, int key) {
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
        System.out.println(output);
    }
}
