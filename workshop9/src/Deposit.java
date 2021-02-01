
public class Deposit implements Runnable
{
    private BankAccount ba = null;

    public Deposit(BankAccount ba)
    {
        if (ba != null)
        {
            this.ba = ba;
        }
    }


    public synchronized void depositMoney()
    {

            //wait for friend to withdraw
            while (ba.getBalance() > 0)
            {
                try
                {
                    System.out.println(Thread.currentThread().getName() + " is waiting to deposit");
                    wait(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            if (!Money.isEmpty("Dollar"))
            {
                ba.setCurrency("Dollar");
                ba.setBalance(Money.depositOne("Dollar"));
            }
            else if (!Money.isEmpty("Euro"))
            {
                ba.setCurrency("Euro");
                ba.setBalance(Money.depositOne("Euro"));
            }
            else
            {
                ba.setCurrency("Pound");
                ba.setBalance(Money.depositOne("Pound"));
            }

         notify();

    }

    @Override
    public void run()
    {
        while(Money.totalAmount() > 0)
        {
            this.depositMoney();
        }

    }

}
