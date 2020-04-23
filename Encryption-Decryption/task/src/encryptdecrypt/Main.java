package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String action = sc.nextLine();

        String message = sc.nextLine();
        int key = sc.nextInt();

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
