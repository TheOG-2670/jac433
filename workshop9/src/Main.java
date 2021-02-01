

public class Main
{

    public static void main(String[] args)
    {
        BankAccount ba = new BankAccount(0);
        Money.initializeMoney();

        Thread me = new Thread(new Deposit(ba), "me");
        Thread friend = new Thread(new Withdraw(ba), "friend");

        me.start();
        friend.start();
    }
}
