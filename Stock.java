//package ePortfolio;

/**
The stock class is used to store the attributes of a stock object. It includes methods specific for calculations to stock investments.
*/

public class Stock extends Investment{

    /**
        When selling and buying investments, the user must pay a commission for Stocks.
    */
    public static final double COMMISSION = 9.99;

    /**
        The constructor will assume that the values given are already checked.
        It will assign the appropriate values when creating a new Stock object.
    */
    public Stock(String symbol, String name, int quantity, double price) throws Exception{
        super(symbol, name, quantity, price);
    }

    public Stock(String symbol, String name, int quantity, double price, double bookValue) throws Exception{
        super(symbol, name, quantity, price);
        if (bookValue <= 0){
            throw new Exception ("Fatal error: bookvalue is not valid");
        }
        this.bookValue = bookValue;
    }

    /**
        The buy book value mutator will assume that the values given are already checked, since quantity and price
        have already been updated before the book value is updated.
        It will increment or decrement the book value when buying an investment based on the stock book value formula.
        @param quantity
        @param price
    */
    @Override
    public void buyBookValue(int quantity, double price){ // upate when purchasing shares
        this.bookValue += ((quantity * price) + COMMISSION); // commission is $9.99 EACH time we buy or sell shares
    }

    /*
        There are important and commonly used methods throughout the program.
        They are specific for calculating the gain, finding an equal value, and giving access to the attributes of an object.
    */

    /**
        The calcGain method calculates the gain if we were to sell the investment at its current, updated price.
        Since there is a commission fee when selling, it is included.
    */
    @Override
    public void calcGain(){
        gain = ((quantity * price - COMMISSION) - bookValue);
    }

    /**
        The amount received when selling a Stock investment is calculated here. This function will always be called
        after the quantity is already updated, therefore, the quantity is valid.
    */
    @Override
    public void received(int quantity, boolean full){ // when selling share
        double received = (price * quantity - COMMISSION);

        if (full == false){ // partial
            gain = received - (bookValue * ((double)quantity / (double)(quantity + this.quantity))); // when selling, recalculate the gain on investment
        }
        else{ // full == true
            gain = (((quantity + this.quantity) * price - COMMISSION) - bookValue);
        }
        Main.mainPage.setToText("The payment received is " + received + " and the amount gained on the investment sold is " + gain);
    }

}
