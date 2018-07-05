package lesson_6;

public class ConnectionRetryAccount implements Account {
    private final Account account;
    private final int retries;
    private int retriesCounter = 1;

    public ConnectionRetryAccount(Account account, int retries) {
        this.account = account;
        this.retries = retries;
    }

    @Override
    public void withdraw(long money) throws AccountException {
        if (retriesCounter <= retries) {
            try {
                account.withdraw(money);
            } catch (AccountException e) {
                if (e instanceof AccountConnectionException && retriesCounter == retries) {
                    throw e;
                }
                if (e instanceof AccountConnectionException) {
                    retriesCounter++;
                    withdraw(money);
                }
                if (e instanceof NotEnoughFundsException) {
                    System.out.println(e.getMessage());
                }
                if (e instanceof AccountExpiredException) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}