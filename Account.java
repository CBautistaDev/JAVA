
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public abstract class Account implements AccountConstants {

    private String AccountNumber;
    private String CustomerName;
    private double Balance;
    private GregorianCalendar openDate;

    public Account(String AccountNumber, GregorianCalendar openDate, String CustomerName, double Balance) {
        this.AccountNumber = AccountNumber;
        this.CustomerName = CustomerName;
        this.Balance = Balance;
        this.openDate = openDate;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;

    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setCustomerName(String AccountName) {
        this.CustomerName = AccountName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }

    public double getBalance() {
        return Balance;
    }

    public GregorianCalendar getopenDate() {
        return openDate;
    }

    public void setOpenDate() {
        this.openDate = openDate;
    }

    public String getDateFomart() {

        String s = "";
        GregorianCalendar gc = getopenDate();
        Date d = gc.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.TAIWAN);
        s = df.format(d);
        return s;
    }

    public boolean deposit(double amount) {

        boolean result = false;

        if (amount <= 0) {
            return result;
        } else {
            Balance += amount;
            result = true;
        }

        return result;

    }

    public boolean withdraw(double amount) {
        boolean result = false;
        if (amount <= 0 || amount > Balance) {
            return result;
        } else {

            Balance -= amount;
            result = true;
        }
        return result;

    }

    public abstract int transferTo(Account account, double amount);
}
