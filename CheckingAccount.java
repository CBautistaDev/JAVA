
import java.util.GregorianCalendar;

public class CheckingAccount extends Account {

    public CheckingAccount(String AccountNumber, GregorianCalendar openDate, String CustomerName, double Balance) {
        super(AccountNumber, openDate, CustomerName, Balance);

    }

    public int transferTo(Account account, double amount) {
        if (getBalance() >= CHECKING_BALANCE_THRESHOLD) {
            // transfer unsuccessful because balance is less than transfer amount
            if (!withdraw(amount)) {
                return -2;
            }

            //  transfer successful and without transfer fee
            account.deposit(amount);
            return 0;
        }

        //  transfer unsuccessful because balance is less than transfer amount and transfer fee
        if (!withdraw(amount + TRANFER_FEE)) {
            return -1;
        }

        //  transfer successful and with the transfer fee applied
        account.deposit(amount + TRANFER_FEE);
        return 1;
    }

}
