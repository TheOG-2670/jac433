import java.util.HashMap;
import java.util.Map;

public class Money
{
    private static Map<String, Integer> money = new HashMap<>();
    public Money()
    {
        //
    }

    public static void initializeMoney()
    {
        money.put("Dollar", 1);
        money.put("Euro", 2);
        money.put("Pound", 3);
    }

    public static int depositOne(String currencyName)
    {
        switch(currencyName)
        {
            case "Dollar":
                money.replace("Dollar", money.get("Dollar")-1);
                System.out.println("1 Dollar has been deposited: " + money.get("Dollar") + " Dollars remaining");
                break;
            case "Euro":
                money.replace("Euro", money.get("Euro")-1);
                System.out.println("1 Euro has been deposited: "  + money.get("Euro") + " Euros remaining");
                break;
            case "Pound":
                money.replace("Pound", money.get("Pound")-1);
                System.out.println("1 Pound has been deposited: "  + money.get("Pound") + " Pounds remaining");
        }
        return 1;
    }

    public static boolean isEmpty(String currencyName)
    {
        return money.get(currencyName) == 0;
    }

    public static int totalAmount()
    {
        return money.get("Dollar") + money.get("Euro") + money.get("Pound");
    }
}
