//package ePortfolio;

/**
The investment class is used to store the common attributes of a stock and mutualfund object.
*/

public abstract class Investment{

    // attributes
    protected String symbol;
    protected String name;
    protected int quantity = 0;
    protected double price = 0.0;
    protected double bookValue = 0.0;
    protected double gain = 0.0;

    /**
        The constructor will assign the appropriate values when creating a new Investment object.
        It will check for validity, and throw an exception when name or symbol is not defined,
        or when price and quantity is not valid.
    */
    public Investment(String symbol, String name, int quantity, double price) throws Exception{
        super();
        // valditity checking in constructor
        if (name.isBlank()){
            throw new Exception ("Fatal error: name is not identified, please try again");
        }
        if (symbol.isBlank()){
            throw new Exception ("Fatal error: symbol is not identified, please try again");
        }
        if (price <= 0){
            throw new Exception ("Fatal error: price is non-existant, please try again");
        }
        if (quantity < 0){
            throw new Exception ("Fatal error: the quantity cannot be negative, please try again");
        }
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
        The quantity mutator will increment or decrement the quantity attribute.
        It will throw an exception if the user is buying and the amount decreases
        or if the user is selling and the amount increases.
    */
    public void setQuantity(int quantity, char kind) throws Exception{
        if (kind == 's' && quantity > 0){ // cannot add shares when selling
            throw new Exception ("Fatal error: the quantity cannot be negative, please try again");
        }
        else if (kind == 'b' && quantity < 0){ // cannot remove shares when buying
            throw new Exception ("Fatal error: the quantity cannot be negative, please try again");
        }
        this.quantity += quantity;
    }

    /**
        The price mutator will update the price attribute, and throw an exception if the price is invalid.
    */
    public void setPrice(double price) throws Exception{
        if (price <= 0){
            throw new Exception ("Fatal error: price is non-existant, please try again");
        }
        this.price = price;
    }
    /**
        The abstract mutator function buy book value will be defined in the subclasses since
        each investment has a different way of computing the book value.
        @param quantity
        @param price
    */
    public abstract void buyBookValue(int quantity, double price);
    /**
        The sell book value mutator will assume that the values given are already checked since it will always be called
        after the quantity is already updated, therefore, the quantity is valid.
        It will adjust the book value when selling an investment based on the remaining book value and quantity.
        @param quantity
    */
    public void sellBookValue(int quantity){ // update when selling partial shares
        this.bookValue = this.bookValue - (this.bookValue * ((double)(quantity) / (double)(quantity + this.quantity)));
    }

    /**
        The accessors will provide values that are private and cannot be accessed.
    */
    public double getGain(){
        return gain;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getBookValue(){
        return bookValue;
    }
    public String getName(){
        return name;
    }
    public String getSymbol(){
        return symbol;
    }

    /*
        There are important and commonly used methods throughout the program.
        They are specific for calculating the gain, finding an equal value, and giving access to the attributes of an object.
    */

    /**
        The equalsSymbol function is commonly used to check whether a given string matches the stock symbol.
    */
    public boolean equalsSymbol(String symbol){ // to check equality for symbol only
        if (symbol == null) return false;
        return ((this.symbol).equalsIgnoreCase(symbol)); // case insensitive comparison
    }

    public String toString(){
        return ("Symbol: " + symbol + "\nName: " + name + "\nQuantity: " + quantity + "\nPrice: " + price + "\nBookvalue: " + bookValue);
    }

    /**
        The nameString function is used during update to show the user which stock they are updating.
    */
    public String nameString(){ // for the update function
        return (symbol + ": " + name + "'s current price is " + price);
    }

    public String toFile(String kind){
        return ("type = \"" + kind + "\"" +
                "\nsymbol = \"" + this.symbol + "\"" +
                "\nname = \"" + this.name + "\"" +
                "\nquantity = \"" + this.quantity + "\"" +
                "\nprice = \"" + this.price + "\"" +
                "\nbookValue = \"" + this.bookValue + "\"");
    }
    /**
        The abstract function get gain will be defined in the subclasses since
        each investment has a different way of computing the gain.
    */
    public abstract void calcGain();
    /**
        The abstract function received will be defined in the subclasses since
        each investment has a different way of computing the amount received when
        selling an investment.
        @param quantity
        @param full
    */
    public abstract void received(int quantity, boolean full);
}
