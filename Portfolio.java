//package abushaah_a3;
// For input
import java.util.Scanner;

// For data structures
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
// For files
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
This is the portfolio. It includes functions for user input, error checking values, and includes the arraylists of stocks and investments.
Functions are implemented to traverse these arrays, set, and get values from the stock and mutual fund classes.
*/
public class Portfolio{

    // (1) dynamic array to store stocks and mutualFunds
    private static ArrayList <Investment> investments = new ArrayList<Investment>();
    private static HashMap <String, ArrayList<Integer>> map = new HashMap <String, ArrayList<Integer>>();

    /**
        The buy function is used for owning new investment or add more quantity to an existing investment.
        It will traverse the ArrayList for an investment.
        If any input is invalid, the user is prompt and the function will restore the old values.
        It is the users responsibility that when buying additional investment, they input the correct
        type.
    */
    public static boolean buy(int kind, String symbol, String name, String quantityStr, String priceStr){

        // validity check for quantity, and price
        int quantity = validQuantity(quantityStr);
        if (quantity == -1){
            return false;
        }
        double price = validPrice(priceStr);
        if (price == -1){
            return false;
        }

        boolean isSymbol = false; // symbol not already defined
        boolean valid = false;

        // check if symbol already defined to update price and quantity
        for (Investment anInvestment: investments){

            if (anInvestment.equalsSymbol(symbol)){
                // make sure that the user is buying from a valid investment or else they're buying new investment with duplicate name
                isSymbol = true;
            }
            if (isSymbol == true){ // when it is true
                // if symbol exists, must be the correct type

                if (kind == 1 && !(anInvestment instanceof Stock)){
                    print("Error, the investment symbol already exists in investments");
                    return false;
                }
                if (kind == 2 && !(anInvestment instanceof MutualFund)){
                    print("Error, the investment symbol already exists in investments");
                    return false;
                }

                // if it is not new, must use a mutator to change the value
                try {
                    anInvestment.setQuantity(quantity, 'b');
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
                double prc = anInvestment.getPrice();
                try {
                    anInvestment.setPrice(price);
                }
                catch (Exception e){
                    print(e.getMessage());
                    try{ // FIRST MUST RESTORE OLD VALUES!
                        anInvestment.setQuantity(-quantity, 's');
                        anInvestment.setPrice(prc);
                    }
                    catch(Exception er){
                        print(er.getMessage());
                    }
                    return false;
                }
                anInvestment.buyBookValue(quantity, price); // simplified using late binding
                valid = true; // just updated the investment
                break;
            }
        }

        // symbol is false, does not exist, create new investment
        if (isSymbol == false){
            valid = newInvestment(kind, symbol, name, quantity, price, -1.0); // create a new investment and put in arraylist
        }
        return valid;
    }

    /**
        The sell function is used for reducing some quantity of an existing investment.
        If the invesment does not exist, the user is prompt.
        If any input is invalid, the user is prompt and the function will restore the old values.
        It will traverse the ArrayList for an investment.
        If the investment is sold fully, then it is deleted from the ArrayList.
    */
    public static boolean sell(String symbol, String quantityStr, String priceStr){

        // validity check for quantity, and price
        int quantity = validQuantity(quantityStr);
        if (quantity == -1){
            return false;
        }
        double price = validPrice(priceStr);
        if (price == -1){
            return false;
        }

        boolean isSymbol = false; // symbol not already defined
        boolean isEnough = false; // enough to sell
        boolean isEqual = false;

        // check if the same symbol exists in the system
        for (Investment anInvestment: investments){
            if (anInvestment.equalsSymbol(symbol)){
                isSymbol = true;
            }

            if ((isSymbol == true) && (quantity <= anInvestment.getQuantity())){ // when it is available and the amount is sufficient
                isEnough = true;
                if (quantity == anInvestment.getQuantity()) isEqual = true;
            }

            if (isEnough == true){

                try {
                    anInvestment.setQuantity(-quantity, 's');
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
                double prc = anInvestment.getPrice();
                try {
                    anInvestment.setPrice(price);
                }
                catch (Exception e){
                    print(e.getMessage());
                    try{ // FIRST MUST RESTORE OLD VALUES!
                        anInvestment.setQuantity(quantity, 'b');
                        anInvestment.setPrice(prc);
                    }
                    catch(Exception er){
                        print(er.getMessage());
                    }
                    return false;
                }

                // simplified using late binding
                anInvestment.received(quantity, isEqual);
                anInvestment.sellBookValue(quantity);

                if (isEqual == true){ // delete investment
                    deleteInvestment(anInvestment);
                }
                break;
            }
        }

        if ((isSymbol == true) && (isEnough == false)){
           print("The quantity for the investment is insufficient");
           return false;
        }

        // symbol is false, does not exist, get input for name
        if (isSymbol == false){
            print("Investement does not exist");
            return false;
        }
        return true;
    }

    public static void updateName(){
        // moving onto new investment, update name of variables
        int index = Main.mainPage.i;
        Main.mainPage.symA.setText(investments.get(index).getSymbol());
        Main.mainPage.nameA.setText(investments.get(index).getName());
        Main.mainPage.prcA.setText("" + investments.get(index).getPrice());
    }
    /**
        The update function is used for refreshing the prices of all existing investment in both Stocks and MutualFunds.
        It will traverse both ArrayList for all investments.
        It will also calculate the gain (if the user wants to sell entire investment) after updating the price.
    */
    public static boolean update(){ // will be called when save is clicked
        
        int index = Main.mainPage.i;
        // update prices through a loop for both stocks and mutual funds
        double price = validPrice(Main.mainPage.prcA.getText());
        try {
            investments.get(index).setPrice(price); // update price
        }
        catch (Exception e){
            print(e.getMessage());
            return false;
        }
        investments.get(index).calcGain(); // simplified using late binding
        print(investments.get(index));
        return true;
    }

    /**
        The getGain function is used to compute the total gain of the portfolio by accumulating the gains of all individual investments.
        It will traverse both ArrayList for all investments.
        It will also calculate the sum of all gains.
    */
    public static double getGain(){
        // call function get gain on all investments in a loop for both stocks and mutual funds

        double gain = 0.0;
        for (Investment anInvestment: investments){
            print("Individual gain for " + anInvestment.getSymbol() + " is " + anInvestment.getGain());
            gain += anInvestment.getGain();
        }
        return gain;
    }

    /**
        The search function is used for finding all investments that match the search request and display all attributes of these investments.
        It will intersect multiple HashList for investments.
        If the investments Symbol, name, and price matches the users symbol, keywords, and price range, respectively, then it will output the attributes.
        The function assumes that if only one input is in the "high number", then the user wants that price and higher,
        if only one input is in the "low number", then the user wants that price and lower,
        if both numbers, "high number" and "low number" are given, the user wants prices between the range.
        If a user wants a single number, they must place the same number in BOTH text fields.
    */
    public static void search(String symbol, String name, String lowPrice, String highPrice){

        // if it is empty, print all
        boolean isAllSymbol = false;
        boolean isAllName = false;
        boolean isAllRange = false;

        boolean valid = false;
        boolean greater = false; // if only low price is given
        boolean less = false; // if only high price is given
        double upper = 0.0;
        double lower = 0.0;

        while (valid == false){ // checks the validty of the range or whether there is no range (implying all prices)
            if ((lowPrice.isBlank() == true) && (highPrice.isBlank() == true)){
                isAllRange = true;
                break;
            }
            else if (highPrice.isBlank() == true){
                greater = true;
            }
            else if (lowPrice.isBlank() == true){
                less = true;
            }
            if (less == false){ // the low is not blank
                lower = validPrice(lowPrice);
                if (lower == -1){
                    return;
                }
            }
            if (greater == false){ // the high is not blank
                upper = validPrice(highPrice);
                if (upper == -1){
                    return;
                }
            }
            if ((greater == false && less == false) && (upper < lower)){
                print("Please enter a valid search range");
                valid = false;
                return;
            }
            valid = true;
        }

        // whether all symbols or names are specified
        if (symbol.isBlank()) isAllSymbol = true;
        if (name.isBlank()) isAllName = true;

        String strDelimiters = "[ ]+";
        String[] wordName = name.split(strDelimiters); // get each word in name and place in array

        ArrayList <Integer> indices = new ArrayList<Integer>(); // keeps track of the indices in investment that contain FULL name

        if (isAllName != true){
            for (int i = 0; i < wordName.length; ++i){
                String key = wordName[i].toLowerCase();
                if (map.containsKey(key)){ // key in investment?
                    if (i == 0){ // first word
                        indices.addAll(map.get(key)); // get integers and add to list
                        // add all will point to the same object. in this case, hashmap is not modified so it is harmless
                    }
                    else{ // not first word, get intersection

                        Iterator <Integer> iterator = indices.iterator(); // using iterator since we will be removing items
                        while (iterator.hasNext()){
                            int index = (Integer) iterator.next(); // index in the original arraylist of investments
                            boolean is = false;

                            for (int k = 0; k < map.get(key).size(); ++k){
                                if (index == map.get(key).get(k)){ // find in the indices array
                                    is = true;
                                    break;
                                }
                            }
                            if (is == false){ // if no match, remove
                                iterator.remove();
                            }
                        }

                        /* since the indices array and the key array are always sorted
                           if the array being compared with is smaller, did not compare items after its size
                           (and must remove all items after size)
                        */
                        if (map.get(key).size() < indices.size()){
                            for (int k = map.get(key).size(); k < indices.size(); ++k){
                                indices.remove(k);
                            }
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
        else{ // name == true matches all list items
            for (int i = 0; i < investments.size(); ++i){
                indices.add(i); // add all indices to list
            }
        }

        Iterator <Integer> iterator = indices.iterator(); // using iterator since we will be removing items
        while (iterator.hasNext()){

            boolean isSymbol = isAllSymbol; // reset values
            boolean isRange = isAllRange;
            int index = (Integer) iterator.next(); // index in the original arraylist of investments

            if ((isSymbol == false) && !(investments.get(index).equalsSymbol(symbol))){ // 5a. must already be false to check
                iterator.remove();
                continue; // if symbol doesnt match investment, no need to check other criteria
            }

            if (isRange == false){
                // between a range or number only
                if (greater == true && (investments.get(index).getPrice() >= lower)){
                    isRange = true;
                }
                if (less == true && (investments.get(index).getPrice() <= upper)){
                    isRange = true;
                }
                if ((less == false && greater == false) && ((investments.get(index).getPrice() <= upper) && (investments.get(index).getPrice() >= lower))){
                    isRange = true;
                }
                if (isRange == false){
                    iterator.remove();
                }
            }
        }

        if (indices.size() == 0){ // no match
            print("No match found");
            return;
        }
        for (int i = 0; i < indices.size(); ++i){ // 6.
            int index = indices.get(i);
            print(investments.get(index));
        }

    }

    /**
        The newInvestment function adds an investment to the investments ArrayList. This is to simplify and reduce code,
        since the program adds an investment in the both the loadFile and buy functions.
        Everytime there is a new investment, there is a new name that must be added in the HashMap. This will be done by:
           1. using ' ' delimieters to split name into words
           2. loop through word size of name and
           3. loop through each item in hashmap
           4. put each word in hash map as the key and map it to its index in investement, keys are lower case, words in name are lowercase
           5. if the key already exists, add index in investement to the list of maps
    */
    public static boolean newInvestment(int kind, String symbol, String name, int quantity, double price, double bookValue){

        if (kind == 1){
            Stock newStock;
            if (bookValue != -1){ // given already, do not calculate
                try {
                    newStock = new Stock(symbol, name, quantity, price, bookValue); // create object
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
            }
            else{
                try {
                    newStock = new Stock(symbol, name, quantity, price); // create object
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
                newStock.buyBookValue(quantity, price); // initial book value
            }
            investments.add(newStock); // add object to list
        }
        if (kind == 2){
            MutualFund newMutualFund;
            if (bookValue != -1){
                try {
                    newMutualFund = new MutualFund(symbol, name, quantity, price, bookValue);
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
            }
            else{
                try {
                    newMutualFund = new MutualFund(symbol, name, quantity, price);
                }
                catch (Exception e){
                    print(e.getMessage());
                    return false;
                }
                newMutualFund.buyBookValue(quantity, price);
            }
            investments.add(newMutualFund);
        }

        String delimiters = "[ ]+"; // 1.
        String[] word = name.split(delimiters); // get each word in name and place in array
        for (int i = 0; i < word.length; ++i){ // 2. for each word in the name

            if (!map.containsKey(word[i].toLowerCase())){ // 4. investment word does not exits, create new list of items to map to new key
                ArrayList<Integer> value = new ArrayList<Integer>();
                value.add(investments.size() - 1); // last investment is the one currently added
                map.put(word[i].toLowerCase(), value);
            }
            else { // contains the key, list already defined, muts update
                for (Iterator<Map.Entry<String, ArrayList<Integer>>> iterate = map.entrySet().iterator(); iterate.hasNext(); ){ // 3. for each entry in the hashmap
                    Map.Entry<String, ArrayList<Integer>> entry = iterate.next();

                    String key = entry.getKey(); // get the current key
                    if (key.equalsIgnoreCase(word[i])){
                        ArrayList<Integer> value = entry.getValue();
                        value.add(investments.size() - 1);
                        entry.setValue(value);
                    }
                }
            }
        }
        return true; // success!
    }

    public static void deleteInvestment(Investment anInvestment){

        int index = investments.indexOf(anInvestment);
        String delimiters = "[ ]+";
        String[] word = anInvestment.getName().split(delimiters);

        for (int i = 0; i < word.length; ++i){

            for (Iterator<Map.Entry<String, ArrayList<Integer>>> iterate = map.entrySet().iterator(); iterate.hasNext(); ){
                Map.Entry<String, ArrayList<Integer>> entry = iterate.next();

                String key = entry.getKey();

                ArrayList<Integer> value = entry.getValue();
                if (key.equalsIgnoreCase(word[i])){
                    int indexValue = value.indexOf(index);
                    value.remove(indexValue);
                    if (value.isEmpty()){ // key has no values
                        iterate.remove(); // remove entry
                    }
                    else{ // key still has values
                        entry.setValue(value);
                    }
                }

                // adjust the indices in the hashmap that are after the investment being deleted
                for (int j = 0; j < value.size(); ++j){
                    int hashIndex = value.get(j);
                    if (hashIndex > index){
                        --hashIndex;
                        value.set(j, hashIndex);
                    }
                }

            }
        }

        investments.remove(index); // remove object from list
        print("There are " + investments.size() + " investments left");
    }

    /**
        The loadFile function reads, parses, and adds an investment to the investments ArrayList.
        It reads each line and parses the answer by double quotes.
        Each item is added to a temporary array.
        Since every 6 items correspond to a single investment, a loop goes through the temporary array
        every 6 times to call the function newInvestment with the parameters.
    */
    public static void loadFile(String fileName){

        String delimiters = "[\"]";
        File f = null;
        Scanner scanner = null;

        try{
            f = new File(fileName);
            scanner = new Scanner(f);
        }
        catch (FileNotFoundException e){
            print("File does not exist, will create file at the end of program.");
            return;
        }

        if (f.length() == 0){
            print("File is empty, will write contents at the end of program.");
            scanner.close();
            return;
        }

        ArrayList <String> arr = new ArrayList<String>(); // for the attributes

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();
            String[] parse = line.split(delimiters);
            if (parse.length == 2){ // attribute = "value", parsing by "" means that the value is in parse[1]
                arr.add(parse[1]);
            }
        }
        // place in investment system
        for (int i = 0; i < arr.size(); i = i + 6){ // add current items
            int kind = 0;
            if (arr.get(i).equalsIgnoreCase("stock")) kind = 1;
            else if (arr.get(i).equalsIgnoreCase("mutualfund")) kind = 2;

            boolean valid = newInvestment(kind, arr.get(i + 1), arr.get(i + 2), Integer.parseInt(arr.get(i + 3)), Double.parseDouble(arr.get(i + 4)), Double.parseDouble(arr.get(i + 5)));
            if (valid == false){
                print("An investment in the file was discarded because it did not meet the validity check");
            }

        }
        arr.clear(); // clear array
        scanner.close();
    }

    /**
        The saveFile function opens a file for writing.
        It then loops through the investments and calls the toFile() function
        in the investment class, which returns a string in the file format.
        The string it then written in the file.
    */
    public static void saveFile(String fileName){

        if (investments.isEmpty()) return; // no need to write any contents

        PrintWriter fileWriter = null;
        try{
            fileWriter = new PrintWriter(new FileOutputStream(fileName));
        }
        catch(FileNotFoundException e){
            print("Failed to write");
            System.exit(0);
        }
        for (Investment anInvestment: investments){
            String kind = new String("");
            if (anInvestment instanceof Stock) kind = "stock";
            if (anInvestment instanceof MutualFund) kind = "mutualfund";

            fileWriter.println(anInvestment.toFile(kind));
        }
        fileWriter.close();
    }

    // error check here instead of both constructor and mutator in both classes
    /**
        The validPrice function is used in buy, sell, and update.
        In order to make the porgram more modular, this function prompts for a valid price
        @return a price (of stock or mutualfund)
    */
    public static double validPrice(String priceStr){

        double price = 0;
        try{
            price = Double.parseDouble(priceStr);
        }
        catch (NumberFormatException e){ // valid input
            print("Invalid price, please enter a decimal number");
            return -1;
        }
        return price;
    }

    /**
        The validQuantity function is used in both buy and sell.
        In order to make the porgram more modular, this function prompts for a valid quantity
        @return quantity (of stocks or mutualfunds)
    */
    public static int validQuantity(String quantityStr){

        int quantity = 0;
        try{
            quantity = Integer.parseInt(quantityStr);
        }
        catch (NumberFormatException e){ // valid input
            print("Invalid quantity, please enter an integer");
            return -1;
        }
        return quantity;
    }

    /**
        The println function is used for simplification of printing an investment (polymorphism).
        It will use late binding to find which toString method to use, and prints the object passed to it.
    */
    public static void print(Investment anInvestment){ // for error checking
        Main.mainPage.setToText(anInvestment.toString());
    }

    public static void print(String aString){ // for error checking
        Main.mainPage.setToText(aString);
    }

    public static boolean invEmpty(){
        if (investments.isEmpty()) return true;
        else return false;
    }

    public static int invSize(){
        return investments.size();
    }

    /**
        The printInfo function is used during testing and error checking.
        This function traverses the ArrayLists and prints all stocks and investments.
    */
    public static void printInfo(){ // for error checking
        for (Investment anInvestment: investments){
            System.out.println(anInvestment);
        }
    }
}
