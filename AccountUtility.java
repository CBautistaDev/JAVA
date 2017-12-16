
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class AccountUtility {

    public static void readAccountFile(ArrayList<String> ListAccounts, LinkedHashMap<String, Account> Accounts) {

        String columns[], AccountNumber, openDate, CustomerName, Balance;

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader("accounts.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                columns = line.split("<>");

                AccountNumber = columns[0];
                openDate = columns[1];
                CustomerName = columns[2];
                Balance = columns[3];

                CheckingAccount a = new CheckingAccount(AccountNumber, validateDate(openDate), CustomerName, Double.parseDouble(Balance)) ;
                
                
                
                ListAccounts.add(AccountNumber);
                Accounts.put(AccountNumber, a);

            }

            in.close();
        } //end of while loop 
        catch (IOException ioe) {
            System.out.println(ioe);
        }

    }

    public static String getDateFormat(Account a) {
        String s = "";
        GregorianCalendar gc = a.getopenDate();
        Date d = gc.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.TAIWAN);
        s = df.format(d);
        return s;

    }
//validates the date 

    private static GregorianCalendar validateDate(String date) {
        String[] theDate = date.split("/");

        return new GregorianCalendar(Integer.parseInt(theDate[0]), Integer.parseInt(theDate[1]) - 1, Integer.parseInt(theDate[2]));
    }

//finds the account number 
    public static Account findAccount(String number, LinkedHashMap<String, Account> Accounts) {

        for (Map.Entry account : Accounts.entrySet()) {
            Account a = (Account) account.getValue();
            if (a.getAccountNumber().equals(number)) 
            {
                return a;
            } 
            
        }
        return null ;

    }

    //saves file to txt
    static void saveFile(LinkedHashMap<String, Account> Accounts) {

        try {
            PrintWriter File = new PrintWriter(new FileWriter("accounts.txt"));

            for (Map.Entry account : Accounts.entrySet()) {
                Account a = (Account) account.getValue();
                File.println(a.getAccountNumber() + "<>" + a.getDateFomart() + "<>" + a.getCustomerName() + "<>" + a.getBalance());
            }

            File.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
