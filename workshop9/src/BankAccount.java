
public class BankAccount
{
    private double balance;
    private String currency;

    public BankAccount(double balance)
    {
        if (balance >= 0.0)
        {
            this.balance = balance;
            this.currency = null;
        }
    }

    public void setCurrency(String currency)
    {
        if ( currency.equals("Dollar") || currency.equals("Euro") || currency.equals("Pound")
        && !currency.equals(this.getCurrency()))
        {
            this.currency = currency;
        }
    }

    public String getCurrency()
    {
        return this.currency;
    }

    public void setBalance(double amount)
    {
        if (amount >= 0)
        {
            this.balance = amount;
        }
    }

    public double getBalance()
    {
        return this.balance;
    }
}
