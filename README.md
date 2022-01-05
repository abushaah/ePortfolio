# ePortfolio
ePortfolio using Java OOP concepts and GUI

Part 3 - November to December 2021

Part 3 README and test plan for GUI, polymorphism, exception handling

1 TEXT AREAS

1.1 The general problem you are trying to solve;

    - Have the text area align on new lines

1.2 What are the assumptions and limitations of your solution;

    - Text area size do not have to look exactly like demo on assignment 3 description

1.3 How can a user build and test your program (also called the user guide);

    1.3.1 Run the program, click any option from the menu given

1.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    1.4.1 Run the program, click any option from the menu given

1.5 What possible improvements could be done if you were to do it again or have extra time available

    1.5.1 Text box sizing (in particular, the get gain) should be smaller and proportionate to the page and its sizing

2 SUBMIT PANEL

2.1 The general problem you are trying to solve;

    - Align the submit panel on the EAST side of the layout
    - Align the buttons on the center vertically and horizontally inside the submit panel

2.2 What are the assumptions and limitations of your solution;

    - The button alignment does not have to look exactly like demo on assignment 3 description

2.3 How can a user build and test your program (also called the user guide);

    2.3.1 Run the program, click any option from the menu given (other than get gain)

2.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    2.4.1 Run the program, click any option from the menu given (other than get gain)

2.5 What possible improvements could be done if you were to do it again or have extra time available

    2.5.1 Align the 'BUY', 'SELL', 'RESET', 'SAVE', 'PREV', 'NEXT', 'SEARCH' buttons on the EAST panel 
    to be centered horizontally, cenetered vertically, and each to placed on a new line

3 FILES ARE SAVED UPON BOTH INPUT AND QUIT

3.1 The general problem you are trying to solve;

    - Call ExitListener upon both exit and 'X' componnents
    - This is done using a Window listener and overriding the windowClosing()

3.2 What are the assumptions and limitations of your solution;

    - N/A

3.3 How can a user build and test your program (also called the user guide);

    3.3.1 Run the program with a file as a command line parameter
    3.3.2 Click button 'quit' from the menu given or 'X'

3.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    3.4.1 Run program with a file that has contents
    3.4.2 Add or change investments in the file
    3.4.3 Exit using 'quit' button and view updated file contents
    3.4.4 Repeat 3.4.1 - 3.4.2
    3.4.5 Exit using 'X' and view updated file contents
    3.4.6 Both methods should update the file

3.5 What possible improvements could be done if you were to do it again or have extra time available

    - N/A

4 EXCEPTION HANDLING REJECTS INPUT AND SPECIFIES ERROR

4.1 The general problem you are trying to solve;

    - Throw an exception when buying or selling negative quantities or at negative prices
    - Throw an exception when name and symbol input is blank or when quantity and price are not integer and doubles, respectively

4.2 What are the assumptions and limitations of your solution;

    - N/A

4.3 How can a user build and test your program (also called the user guide);

    4.3.1 Run the program, click button 'buy', 'sell', or 'update' from the menu given

4.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    4.4.1 Enter an invalid quantity or price, or leave a name or symbol blank
    4.4.2 Should give an error message and reject the input

4.5 What possible improvements could be done if you were to do it again or have extra time available

    - Add more meaningful error messaging

5 PREVIOUS AND NEXT FUNCTIONS TRAVERSE THROUGH INVESTMENTS

5.1 The general problem you are trying to solve;

    - Traverse through arraylist of investment based on the 'previous' and 'next' buttons
    - Disable previous and next when appropriate

5.2 What are the assumptions and limitations of your solution;

    - N/A

5.3 How can a user build and test your program (also called the user guide);

    5.3.1 Run the program, click button 'update' from the menu given

5.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    5.4.1 A counter called i is placed to keep track of the index in the arraylist, which increases when
    the user clicks 'next' button and decreases when user clicks 'previous' button
    5.4.2 When i is 0, previous button is disabled, else when i greater than 0, previous button is enabled
    5.4.3 When i is array size - 1, next button is disabled, else when i less than array size - 1, next button is enabled
    5.4.4 To test, traverse the investments by clicking the 'next' button until the last element and then clicking the
    'previous' button until the first element is reached

5.5 What possible improvements could be done if you were to do it again or have extra time available

    - N/A

Part 2 - October - November 2021

Part 2 README and test plan for hash tables and inheritance

Assume the following Stock investments are in the ArrayList which will be used in testing:

(0)
Symbol: bit
Name: bank in toronto
Quantity: 10
Price: 150.0
Bookvalue: 1009.99

(1)
Symbol: rb
Name: royal bank
Quantity: 10
Price: 20.0
Bookvalue: 209.99

(2)
Symbol: bt
Name: bank toronto
Quantity: 10
Price: 20.0
Bookvalue: 209.99

1 SEARCH FUNCTION ALGORITHM AND USER INPUT (IN PARTICULAR, THE HASHMAP FUNCTION)

1.1 The general problem you are trying to solve;

    - Generate proper list of indices
    Testing condition problems:
    - the given element is not on the list;
    - the given element is at the start of the list;
    - the given element is at the end of the list;
    - the given element is somewherebetween the two endsin the list.

1.2 What are the assumptions and limitations of your solution;

    - N/A

1.3 How can a user build and test your program (also called the user guide);

    1.3.1 Run the program, input option 6 or enter 'search' from the menu given
    1.3.2 Enter (or leave blank) a symbol, name or keywords, or range (-number, number-, number-number)

1.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    HashMap from the newInvestment function is created, with these contents: {bank=[0, 1, 2], in=[0], toronto=[0, 2], royal=[1]}
    Upon entry to search function, an additional arrayList of the indices that match the search is made.
    Therefore, it does not matter where the item is in the Investement array.

    1.4.1 Single key word
        Test: bank
        Result: Should print all arraylist at indices in bank, [0, 1, 2]
    1.4.2 Intersection of multiple arrays with larger size, then smaller size
        Test: Toronto bank in
        Result: Should print arraylist at index [0]
    1.4.3 Empty input for name
        Test:
        Result: Should print arraylist at all indices
    1.4.4 Input for symbol and/or price range
        Test:
            Name: Toronto Bank
            Price Range: 100-
        Result: Should print arraylist at index [0] only (NOT index [2])
    1.4.1 Name does not exist/match
        Test: star
        Result: Should print 'no match found'

1.5 What possible improvements could be done if you were to do it again or have extra time available

    - Make the intersection more modular/efficient
    - Use iterators to traverse through all data structures / concrete classes

2 LOAD AND SAVE FILE FUNCTIONS

1.1 The general problem you are trying to solve;

    - Load and store values in the beginning and end of the program

1.2 What are the assumptions and limitations of your solution;

    - No duplicate symbol investments in the file
    - The file contents contain valid input, including:
        - bookValue is properly calculated for stock/mutualfund
        - Price and Wuantity are not negative
    - Assuming that if the ArrayList is empty, the program will not save anything to the file upon termination of the program

1.3 How can a user build and test your program (also called the user guide);

    1.3.1 Compile the program with a file (while in directory before ePortfolio folder):
        javac ePortfolio/Portfolio.java
        java ePortfolio/Portfolio fileName.txt
    1.3.2 Compile the program without file (while in directory before ePortfolio folder):
        javac ePortfolio/Portfolio.java
        java ePortfolio/Portfolio

1.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    When compiling the program:
    1.4.1 With a file
        Test: Run 1.3.1
        Result: Should store all values from file into ArrayList, and in the end of the program, save all contents back into the same file
    1.4.1 With a file and upon termination, no contents in ArrayList
        Test: Run 1.3.1
        Result: Should store all values from file into ArrayList, and in the end of the program, NOT save items into file
    1.4.1 Without a file and contents in ArrayList
        Test: Run 1.3.2, enter investments using the buy function, and enter option 'quit'
        Result: Should ask the user for a filename to store the contents in
    1.4.1 Without a file and no contents in ArrayList
        Test: Run 1.3.2, then enter option 'quit'
        Result: Should not ask the user for a filename (since ArrayList is empty), and instead terminate the program
    1.4.1 With a file that does not exist (when compiling or input at the end of program)
        Test: Run 1.3.1 or 1.3.2 with a non existant or empty file
        Result: Should warn the user that the file is non existant or empty, and in the end of the program, save all contents back into the same file

1.5 What possible improvements could be done if you were to do it again or have extra time available

    - N/A

Part 1 - September to October

Part 1 README and test plan for initial functions

Assume the following Stock investments are in the ArrayList which will be used in testing:

(0)
Symbol: AAPL
Name: Apple Inc.
Price: 110.08
Quantity: 500
BookValue: 55,049.99

(1)
Symbol: BT
Name: Bank of Toronto
Price: 130.70
Quantity: 500
BookValue: 65,359.99

-----------------------------------------------------------------------------------------------------

1 MAIN FUNCTION'S USER INPUT

1.1 The general problem you are trying to solve;

    - User input for the options in the program must take into account the reasonable input values

1.2 What are the assumptions and limitations of your solution;

     - The reasonable values that the user may enter include numerical values, the first letter of the word, and the word with case insensitivity
     - The cases are accounted for in the if else statement on line 52-68. It takes the input word and checks for:
     1.2.1 Case insensitive exact word as presented above
     1.2.2 The first letter of the word matches (except for search and sell)
     1.2.3 The corresponding number of the option (ie, 1-6)

1.3 How can a user build and test your program (also called the user guide);

    - Run the program, input an option from the menu given

1.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    1.4.1 Main while loop for the user options
        Test: Random letters or numbers that do not match the correspond to the option or are out of range 1-6
        Result: Should re-prompt the user to enter again
        Test: Enter first letter of word (other than 's') or corresponding number position of word
        Result: Should prompt the correct function (1 or buy, 2 or sell, 3 or update, 4 or get gain, 5 or search, 6 or quit)
        Test: Enter 's'
        Result: Must reprompt since 's' is first letter of both sell and search
    1.4.2 Numerical values before calling constructors and mutators
        Test: Assigning a string or assigning a double to an integer value
        Result: Should re-prompt the user to enter again
        Test: Mismatch exception errors are handled
        Result: Should re-prompt the user to enter again

1.5 What possible improvements could be done if you were to do it again or have extra time available

    - Use regex to match user input

-----------------------------------------------------------------------------------------------------

2 BUY FUNCTION

2.1 The general problem you are trying to solve;

    - Test the algorithm for correctness

2.2 What are the assumptions and limitations of your solution;

    - The kind is entered using an integer number (1) or (2), not a string
    - Assuming the values entered are complete and correct. If not, the user will be re-prompt to enter a quantity or price

2.3 How can a user build and test your program (also called the user guide);

    2.3.1 Run the program, input option 1 or enter 'buy' or 'b' from the menu given
    2.3.2 Enter an investment kind, a symbol, price, quantity, and, if prompt, a name

2.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    2.4.1 Successfully creates investment with appropriate values
        Test: Creates an object with user input of Symbol, Name, Price, Quantity, and calculation of the book value above
        Result: Prints investments with proper values as shwon above
    2.4.1 Successfully updates an investments if buying new stock with the same symbol
        Test: An existing investments quantity, price, and book value is updated
        Result: Purchasing 100 more shares of AAPL at $108.90 should change AAPL to:
            Price: $108.90
            Quantity: 600
            Book value: 55,049.99 + (100 * 108.90 + 9.99) = 65,949.98

2.5 What possible improvements could be done if you were to do it again or have extra time available

    - Reduce redundancy with inheritance

-----------------------------------------------------------------------------------------------------

3 SELL FUNCTION

3.1 The general problem you are trying to solve;

    - Test the algorithm for correctness

3.2 What are the assumptions and limitations of your solution;

    - The kind is entered using an integer number (1) or (2), not a string
    - Assuming the values entered are complete and correct. If not, the user will be re-prompt to enter a quantity or price

3.3 How can a user build and test your program (also called the user guide);

    3.3.1 Run the program, input option 2 or enter 'sell' from the menu given
    3.3.2 Enter an investment kind, a symbol, price, and quantity
    
3.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    3.4.1 When partially selling stock, must display the amount received, update the selling price, the quantity, and the book value
        Test: Selling 200 shares of AAPL at $142.23
        Result:
        The result will print the appropriate investment and the payment received will be 200 × 142.23 – 9.99 = $28,436.01
        The quantity will be reduced to 300, and bookValue will be adjusted to 55,049.99 × 300/500 = $33,029.99
    3.4.2 When fully selling a stock, must display the amount gained
        Test: Sell 500 shares of AAPL at $142.23
        Result: Gain is (500 x 142.23 - 9.99) - 55,049.99 = 16,055.02
    3.4.3 Removes investment after selling full
        Test: Printing all investments available in the ArrayList
        Result: The investment is not present nor can still be accessed

3.5 What possible improvements could be done if you were to do it again or have extra time available

    - Reduce redundancy with inheritance

-----------------------------------------------------------------------------------------------------

4 & 5 UPDATE AND GET GAIN FUNCTIONS

4 & 5.1 The general problem you are trying to solve;

    - Test the algorithm for correctness

4 & 5.2 What are the assumptions and limitations of your solution;

    - The program will display the name and current price of the investment
    - It will then prompt for a new price, assuming the numerical values entered in update are complete and correct
    - If the user does not enter a numerical value, they will be re-prompt to enter a price

4 & 5.3 How can a user build and test your program (also called the user guide);

    4 & 5.3.1 Run the program, input option 4 or enter 'update' or 'u' or input option 5 or enter 'get gain' or 'g' from the menu given
    4 & 5.3.2 Enter an investment kind, a symbol, price, and quantity
    
4 & 5.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    4 & 5.4.1 Update function updates all prices
        Test: Update all prices to
            AAPL: $142.23
            BT: $100.80
        Result: Upon printing the investments, all prices are updated (and nothing else)
    4 & 5.4.2 The gain attribute for each investment should be calculated upon updating each stock price
        Test: Print investments
        Result: Gain attribute for
            AAPL: Gain is (500 x 142.23 - 9.99) - 55,049.99 = 16,055.02
            BT: Gain is (500 x 100.80 - 9.99) - 65,359.99 = -14,969.98            
    4 & 5.4.3 Gain function has calculated sum of all gain using appropriate formula
        Test: Run the function getGain
        Result: The sum of gain should be calculated as 16,055.02 + -14,969.98 = 1,085.04

4 & 5.5 What possible improvements could be done if you were to do it again or have extra time available

    - N/A

-----------------------------------------------------------------------------------------------------

6 SEARCH FUNCTION ALGORITHM AND USER INPUT

6.1 The general problem you are trying to solve;

    - Parsing the input and then matching the input with an investment in either stocks or mutual funds

6.2 What are the assumptions and limitations of your solution;

    Below is the algorithm and it's assumptons:
    6.2.1 get user input for symbol, name, price range
    6.2.2 if a user input is empty, set to value to true
        a. we will assume that an empty string is one where isBlank() returns true (ie, if it is empty or null)
    6.2.3 the name will be parsed into an array of words to compare whether they are ALL present in the list
        a. we will assume that the words in the name are separated by any number of spaces (ie, Bank*of*Toronto will be treated as one word)
    5.2.4 the range will be first check where the '-' symbol is, then parsed based on the '-' symbol
        a. there MUST be numbers present in the string in order to perform the below parsing
            i. this will be checked used regex .match() to make sure there is a number in the string. if not, user is repromt
            ii. we will assume that the number must be surrounded by only the '-' symbol in order to parse (ie, there is no 120.99+90.98 or 120.99 190.70 input)
        b. depending on where it is found, it will be converted into 2 numerical values, the lower bound and the upper bound
        c. if there is no '-' then it is treated as a single number (stored in lower bound while upper bound is 0)
        d. if the '-' symbol is in the middle, then lower bound is smaller number and upper bound is higher number
        e. if the '-' symbol is first, then the lower bound is MIN_VALUE and the upper bound is the number
        f. if the '-' symbol is last, then the lower bound is the number, and the upper bound is MAX_VALUE
    6.2.5 traverse through both lists to match:
        a. the exact symbol given, in case insensitive search
        b. each word in the name must be present, in case insensitive search
        c. check whether price it is in the numerical range of the upper and lower bound
    6.2.6 if all the above matches give true, print the investment

6.3 How can a user build and test your program (also called the user guide);

    6.3.1 Run the program, input option 6 or enter 'search' from the menu given
    6.3.2 Enter (or leave blank) a symbol, name or keywords, or range (-number, number-, number-number)

6.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    Stock investment we want to search for: BT
    6.4.1 Invalid input for numerical values
        Test: "hello"
        Result: Should re-prompt the user to enter again
    6.4.2 The ranges
        Test: -number: -110.08
        Result: Should not print the investment
        Test: Number-: 110.08-
        Result: Should print the investment
        Test: Number-number: 110.08 - 200.08
        Result: Should print the investment
        Test: Number-number: 200.08 - 300.08
        Result: Should not print the investment
    6.4.3 Blank input
        Test: All blank
        Result: Should print all investments
    6.4.4 One of symbol / name / price range is inputted 
        Test: Blank symbol and blank name and a price range of 100.00-
        Result: Should print all investments above
        Test: Blank price range
        Result: Should print investment that matches name or symbol
    6.4.5 Case insensitivity
        Test: bank instead of Bank
        Result: Should print the investment
    6.4.6 Word matching
        Test: Banking instead of Bank
        Result: Should not print the investment
    6.4.7 All words are present in the investment
        Test: Name input of “Toronto Bank”
        Result: Should print investment Bank of Toronto

6.5 What possible improvements could be done if you were to do it again or have extra time available

    - In the future, I would be more specific with the parsing rules (ie, let the user enter spaces in the numbers 100.00 - 200.00)
    - The number also only works if there is one instance of '-'. Either before OR after a single number, or between two numbers
    - Reduce redundancy with inheritance

7 ePortfolio

7.1 The general problem you are trying to solve;

    - Creating the ePortfolio with proper user feedback/output/interface

7.2 What are the assumptions and limitations of your solution;

    - The user inputs correct information:
        - 1 or stock and 2 or mutualfund
        - The name and symbol is a valid string (ie, no blanks. If the user enters blanks the program will assume that is the correct symbol or name of the investment)

7.3 How can a user build and test your program (also called the user guide);

    - Compilation (while in directory before ePortfolio folder):
        javac ePortfolio/Portfolio.java
        java ePortfolio/Portfolio

7.4 How is the program tested for correctness (i.e., the test plan should be part of the README file); and

    - Follow the above test plan outline in sections 1.4 to 6.4

7.5 What possible improvements could be done if you were to do it again or have extra time available

    - Use inheritance to reduce redundancy
