package org.koketjo;

import java.awt.*;

public class UserAccount extends Component {

    private static double bankBalance = 1500.55;

    public static void setBankBalance(double amount)
    {
        bankBalance = amount;
    }

    public static double getBankBalance()
    {
        return bankBalance;
    }
}

