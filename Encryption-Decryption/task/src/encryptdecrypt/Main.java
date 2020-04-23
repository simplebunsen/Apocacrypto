package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();
        String output = "";
        int key = sc.nextInt();
        for(char c : message.toCharArray()){
            //97 - 122 lowercase letters
            if(c <= 122 && c >= 97){
                char next;
                if(c + key > 122){
                    next = (char) (c + key - 26);
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
