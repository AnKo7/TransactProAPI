import java.util.Random;
import java.util.UUID;

public class Helper {

    public String getValidPass(int length){

        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String pass = sb.toString();
        System.out.println("Password="+pass);
        return pass;
    }

    public String getValidEmail(int length){

        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String email = sb.toString()+"@exam.com";
        System.out.println("Email="+email);
        return email;
    }

    public String getValidPhone(int length){

        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String phone = "+"+sb.toString();
        System.out.println("Phone="+phone);
        return phone;
    }

    public String getValidUsername(int length){

        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String name = sb.toString();
        System.out.println("Username="+name);
        return name;
    }

}
