public class Withdraw implements Runnable
{
    private BankAccount ba = null;

    public Withdraw(BankAccount ba)
    {
        if (ba != null)
        {
            this.ba = ba;
        }
    }

    public synchronized void withdrawMoney()
    {

            while (ba.getBalance() == 0)
            {
                try
                {
                    System.out.println(Thread.currentThread().getName() + " is waiting to withdraw");
                    wait(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }


            System.out.println(ba.getBalance() + " " + ba.getCurrency() + " has been withdrawn\n");
            //account balance emptied
            ba.setBalance(0);
            //notify me to deposit more money
            notify();
    }

    @Override
    public void run()
    {
        while (Money.totalAmount() > 0)
            this.withdrawMoney();
    }

}
