package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String localPart = parts[0];
        String domainPart = parts[1];

        //Checking local part
        if (localPart.length() == 0 || localPart.length() > 64) {
            return false;
        }

        //Checking domain part
        if (domainPart.length() == 0 || domainPart.length() > 255) {
            return false;
        }

        //Checking for valid characters in local and domain part
        String validLocalChars = "!#$%&'*+/=?^_`{|}~-";
        String validDomainChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.";

        for (char ch : localPart.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && validLocalChars.indexOf(ch) == -1) {
                return false;
            }
        }
        for (char ch : domainPart.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && validDomainChars.indexOf(ch) == -1) {
                return false;
            }
        }

        //Checking for consecutive dots or leading/trailing dots in domain part
        if (domainPart.startsWith(".") || domainPart.endsWith(".") || domainPart.contains("..")) {
            return false;
        }

        return true;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
