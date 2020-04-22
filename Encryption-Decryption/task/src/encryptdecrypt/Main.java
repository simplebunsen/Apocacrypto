package encryptdecrypt;

public class Main {
    public static void main(String[] args) {

        String message = "we found a treasure!";
        String output = "";
        for(char c : message.toCharArray()){
            //97 - 122 lowercase letters
            if(c <= 122 && c >= 97){
                int diff = c - 97;
                char next = (char) (122 - diff);
                output = output.concat(String.valueOf(next));
            }else{
                output = output.concat(String.valueOf(c));
            }
        }
        System.out.println(output);

    }
}
