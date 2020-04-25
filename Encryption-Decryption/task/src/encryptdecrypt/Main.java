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
        String algorithm = "shift";
        int key = 0;
        String outFile = "";

        Encrypter encrypter = new Encrypter();

        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            switch (s) {
                case "-alg":
                    algorithm = args[++i];
                    break;
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

        switch (algorithm){
            case "shift":
                encrypter.setMethod(new ShiftEncryptingMethod());
                break;
            case "unicode":
                encrypter.setMethod(new UnicodeEncryptingMethod());
                break;
            default:
                throw new IllegalStateException("Error: Unexpected value for algorithm: " + algorithm);
        }

        String result;
        switch(action){
            case "enc":
                result = encrypter.encrypt(message, key);
                break;
            case "dec":
                result = encrypter.decrypt(message, key);
                break;
            default:
                throw new IllegalStateException("Error: Unexpected value for enc/dec action: " + action);
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


}
