//package ePortfolio;

/**
The mutual fund class is used to store the attributes of a mutual fund object. It includes methods specific for calculations to mutual fund investments.
All constructors, accessors, mutators, attributes, and methods used in the stock class are also used in the mutual fund class.
There is, however, a difference in the constants and formulas.
*/

public class MutualFund extends Investment{

    /**
        When selling investments, the user must pay a redemption fee for Mutual Funds.
    */
    public static final double REDEMPTIONFEE = 45.00;

    /**
        The constructor will assume that the values given are already checked.
        It will assign the appropriate values when creating a new MutualFund object.
    */
    public MutualFund(String symbol, String name, int quantity, double price) throws Exception{
        super(symbol, name, quantity, price);
    }

    public MutualFund(String symbol, String name, int quantity, double price, double bookValue) throws Exception{
        super(symbol, name, quantity, price);
        if (bookValue <= 0){
            throw new Exception ("Fatal error: bookvalue is not valid");
        }
        this.bookValue = bookValue;
    }

    /**
        The buy book value mutator will assume that the values given are already checked since quantity and price
        have already been updated before the book value is updated.
        It will increment or decrement the book value when buying an investment based on the mutual fund book value formula.
        @param quantity
        @param price
    */
    @Override
    public void buyBookValue(int quantity, double price){ // upate when purchasing shares
        this.bookValue += (quantity * price);
    }

    // methods
    /**
        The calcGain method calculates the gain if we were to sell the investment at its current, updated price.
        Since there is redemption fee when selling, it is included.
    */
    @Override
    public void calcGain(){
        gain = ((quantity * price - REDEMPTIONFEE) - bookValue);
    }

    /**
        The amount received when selling a Stock investment is calculated here. This function will always be called
        after the quantity is already updated, therefore, the quantity is valid.
    */
    @Override
    public void received(int quantity, boolean full){ // when selling share
        double received = (price * quantity - REDEMPTIONFEE);

        if (full == false){ // partial
            gain = received - (bookValue * ((double)quantity / (double)(quantity + this.quantity))); // when selling, recalculate the gain on investment
        }
        else{ // full == true
            gain = (((quantity + this.quantity) * price - REDEMPTIONFEE) - bookValue); // recalculate the gain
        }
        Main.mainPage.setToText("The payment received is " + received + " and the amount gained on the investment sold is " + gain);
    }

}
