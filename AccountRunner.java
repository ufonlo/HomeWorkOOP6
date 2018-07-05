package lesson_6;

public class AccountRunner {
    public static void main(String[] args) throws AccountException {
        RandomAccount randomAccount = new RandomAccount();
        ConnectionRetryAccount connectionRetryAccount = new ConnectionRetryAccount((Account) randomAccount, 2);
        connectionRetryAccount.withdraw(1000);
    }
}