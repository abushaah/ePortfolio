//package abushaah_a3;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class Layout extends JFrame{ // inheritance, share methods in JFrame and add more, override methods

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 650;

    private JPanel buyPanel;
    private JPanel sellPanel;
    private JPanel updatePanel;
    private JPanel ggPanel;
    private JPanel searchPanel;
    private JPanel submit;
    private JPanel outputPanel;
    private JPanel input;

    private JComboBox kindSM;

    private JScrollPane scrolledText;
    protected JTextArea symA;
    protected JTextArea nameA;
    private JTextArea qtyA;
    protected JTextArea prcA;
    private JTextArea kwA;
    private JTextArea lowA;
    private JTextArea highA;
    private JTextArea gainA;
    private JTextArea output;
    
    private JLabel typeL;
    private JLabel symL;
    private JLabel nameL;
    private JLabel qtyL;
    private JLabel prcL;
    private JLabel kwL;
    private JLabel lowL;
    private JLabel highL;
    private JLabel gainL;
    private JLabel message;

    private JLabel descLabel;
    private JLabel buying;
    private JLabel selling;
    private JLabel updating;
    private JLabel getting;
    private JLabel searching;

    private JButton subBButton;
    private JButton subSButton;
    private JButton resetButton;
    private JButton prevButton;
    private JButton nextButton;
    private JButton saveButton;
    private JButton findButton;

    protected int i = 0; // for update

    private static int kind = 1; // default kind is Stock!
    private static String symbol;
    private static String name;
    private static String quantity;
    private static String price;
    private static String price2;

    Font myFont = new Font("Times New Roman", Font.PLAIN, 15);
    Color myWhite = new Color(255, 255, 255);
    Color myOWhite = new Color(239, 234, 230);
    Color myLGreen = new Color(223, 242, 241);
    Color myGreen = new Color(179, 214, 211);
    Color myPurple = new Color(222, 207, 221);
    Color myLPink = new Color(242, 199, 205);
    Color myPink = new Color(225, 162, 171);
    Color myGrey = new Color(105, 117, 134);

    /**
        The Layout constructor has all the necessary components for each panel. When a component is used,
        it will be linked to an actio listener class and perform the necessary calculations.
    */
    public Layout(){

        super("ePortfolio");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter(){ // window listener is only used here
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
                new ExitListener();
                e.getWindow().dispose();
            }
        });

        // 1. set the border layout
        setLayout(new BorderLayout());
        getContentPane().setBackground(myWhite);

        // 2. north region has command buttons      
        JPanel menu = new JPanel();
        menu.setBackground(myWhite);
        menu.setLayout(new FlowLayout());

        // 3. commands
        JButton buyButton = new JButton("BUY"); // this is the component
        buyButton.setFont(myFont);
        buyButton.setBackground(myGreen);
        buyButton.addActionListener(new BuyListener()); // this is the anonymous listener class which links the component to the event
        
        JButton sellButton = new JButton("SELL");
        sellButton.setFont(myFont);
        sellButton.setBackground(myLGreen);
        sellButton.addActionListener(new SellListener());

        JButton updateButton = new JButton("UPDATE");
        updateButton.setFont(myFont);
        updateButton.setBackground(myOWhite);
        updateButton.addActionListener(new UpdateListener());

        JButton ggButton = new JButton("GET GAIN");
        ggButton.setFont(myFont);
        ggButton.setBackground(myPurple);
        ggButton.addActionListener(new GgListener());

        JButton searchButton = new JButton("SEARCH");
        searchButton.setFont(myFont);
        searchButton.setBackground(myGrey);
        searchButton.addActionListener(new SearchListener());

        JButton exitButton = new JButton("QUIT");
        exitButton.setFont(myFont);
        exitButton.setBackground(myWhite);
        exitButton.addActionListener(new ExitListener());

        // 4. adding the commands to the north region
        menu.add(buyButton); // adds to menu
        menu.add(sellButton);
        menu.add(updateButton);
        menu.add(ggButton);
        menu.add(searchButton);
        menu.add(exitButton);
        add(menu, BorderLayout.NORTH);

        // 5. the south region has the output, and the center region is the input
        outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBackground(myWhite);

        message = new JLabel();
        message.setFont(myFont);

        output = new JTextArea(10, 95);
        output.setLineWrap(true);
        output.setEditable(false); // only output, no input
        output.setFont(myFont);
        
        scrolledText = new JScrollPane(output);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        outputPanel.add(message, BorderLayout.NORTH);
        outputPanel.add(scrolledText, BorderLayout.SOUTH);
        outputPanel.setVisible(false);
        add(outputPanel, BorderLayout.SOUTH);

        // 7. input panel is center
        input = new JPanel();
        input.setBackground(myWhite);
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

        descLabel = new JLabel("<html>Welcome to ePortfolio.<br/><br/><br/><br/>Choose a command from the \"Commands\" menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program.</html>");
        descLabel.setHorizontalAlignment(JLabel.CENTER);
        descLabel.setVisible(true); // should be true until the user clicks a button
        input.add(descLabel);

        buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(5, 2));
        buyPanel.setBackground(myWhite);
        buyPanel.setVisible(false);
        buying = new JLabel("BUYING AN INVESTMENT");
        buying.setVisible(false);
        input.add(buying);

        sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(3, 2));
        sellPanel.setBackground(myWhite);
        sellPanel.setVisible(false);
        selling = new JLabel("SELLING AN INVESTMENT");
        selling.setVisible(false);
        input.add(selling);

        updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(3, 2));
        updatePanel.setBackground(myWhite);
        updatePanel.setVisible(false);
        updating = new JLabel("UPDATING AN INVESTMENT");
        updating.setVisible(false);
        input.add(updating);

        ggPanel = new JPanel();
        ggPanel.setLayout(new GridLayout(1, 1));
        ggPanel.setBackground(myWhite);
        ggPanel.setVisible(false);
        getting = new JLabel("GETTING TOTAL GAIN");
        getting.setVisible(false);
        input.add(getting);

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(4, 2));
        searchPanel.setBackground(myWhite);
        searchPanel.setVisible(false);
        searching = new JLabel("SEARCHING INVESTMENTS");
        searching.setVisible(false);
        input.add(searching);

        // the section below will have the user input textfield and labels
        typeL = new JLabel("TYPE", SwingConstants.CENTER);
        typeL.setVisible(false);
        
        String[] options = {"Stock", "Mutual Fund"};
        kindSM = new JComboBox(options);
        kindSM.setBackground(myWhite);
        kindSM.setVisible(false);
        kindSM.setSelectedIndex(0);
        kindSM.addActionListener(new KindListener());

        symL = new JLabel("SYMBOL", SwingConstants.CENTER);
        symL.setVisible(false);

        symA = new JTextArea("", 1, 2);
        symA.setLineWrap(true);
        symA.setEditable(true);
        symA.setVisible(false);
        symA.setBorder(new LineBorder(Color.BLACK, 2));

        nameL = new JLabel("NAME", SwingConstants.CENTER);
        nameL.setVisible(false);

        nameA = new JTextArea("", 1, 2);
        nameA.setLineWrap(true);
        nameA.setEditable(true);
        nameA.setVisible(false);
        nameA.setBorder(new LineBorder(Color.BLACK, 2));

        qtyL = new JLabel("QUANTITY", SwingConstants.CENTER);
        qtyL.setVisible(false);

        qtyA = new JTextArea("", 1, 2);
        qtyA.setLineWrap(true);
        qtyA.setEditable(true);
        qtyA.setVisible(false);
        qtyA.setBorder(new LineBorder(Color.BLACK, 2));

        prcL = new JLabel("PRICE", SwingConstants.CENTER);
        prcL.setVisible(false);

        prcA = new JTextArea("", 1, 2); // used for both price and low price
        prcA.setLineWrap(true);
        prcA.setEditable(true);
        prcA.setVisible(false);
        prcA.setBorder(new LineBorder(Color.BLACK, 2));

        kwL = new JLabel("NAME KEYWORDS", SwingConstants.CENTER);
        kwL.setVisible(false);

        kwA = new JTextArea("", 1, 2); // used for both name and name keyword
        kwA.setLineWrap(true);
        kwA.setEditable(true);
        kwA.setVisible(false);
        kwA.setBorder(new LineBorder(Color.BLACK, 2));

        lowL = new JLabel("LOW PRICE", SwingConstants.CENTER);
        lowL.setVisible(false);

        lowA = new JTextArea("", 1, 2); // used for both name and name keyword
        lowA.setLineWrap(true);
        lowA.setEditable(true);
        lowA.setVisible(false);
        lowA.setBorder(new LineBorder(Color.BLACK, 2));

        highL = new JLabel("HIGH PRICE", SwingConstants.CENTER);
        highL.setVisible(false);

        highA = new JTextArea("", 1, 2); // used for both name and name keyword
        highA.setLineWrap(true);
        highA.setEditable(true);
        highA.setVisible(false);
        highA.setBorder(new LineBorder(Color.BLACK, 2));   

        gainL = new JLabel("TOTAL GAIN", SwingConstants.CENTER);
        gainL.setVisible(false);            

        gainA = new JTextArea("", 1, 2);
        gainA.setLineWrap(true);
        gainA.setEditable(false);
        gainA.setVisible(false);
        gainA.setBorder(new LineBorder(Color.BLACK, 2));
        
        add(input, BorderLayout.CENTER);

        // 8. the button panel is east region
        submit = new JPanel();
        submit.setBackground(myWhite);
        submit.setLayout(new BoxLayout(submit, BoxLayout.PAGE_AXIS));
        submit.setPreferredSize(new Dimension(300, 100));
        
        subBButton = new JButton("BUY");
        subBButton.setFont(myFont);
        subBButton.setBackground(myWhite);
        subBButton.setVisible(false);
        subBButton.addActionListener(new SubBListener());

        subSButton = new JButton("SELL");
        subSButton.setFont(myFont);
        subSButton.setBackground(myWhite);
        subSButton.setVisible(false);
        subSButton.addActionListener(new SubSListener());

        resetButton = new JButton("RESET");
        resetButton.setFont(myFont);
        resetButton.setBackground(myWhite);
        resetButton.setVisible(false);
        resetButton.addActionListener(new ResetListener());

        prevButton = new JButton("PREVIOUS");
        prevButton.setFont(myFont);
        prevButton.setBackground(myWhite);
        prevButton.setVisible(false);
        prevButton.addActionListener(new PrevListener());

        nextButton = new JButton("NEXT");
        nextButton.setFont(myFont);
        nextButton.setBackground(myWhite);
        nextButton.setVisible(false);
        nextButton.addActionListener(new NextListener());

        saveButton = new JButton("SAVE");
        saveButton.setFont(myFont);
        saveButton.setBackground(myWhite);
        saveButton.setVisible(false);
        saveButton.addActionListener(new SubUListener());

        findButton = new JButton("SEARCH");
        findButton.setFont(myFont);
        findButton.setBackground(myWhite);
        findButton.setVisible(false);
        findButton.addActionListener(new SubSearchListener());

        submit.add(subBButton);
        submit.add(subSButton);
        submit.add(resetButton);
        submit.add(prevButton);
        submit.add(nextButton);
        submit.add(saveButton);
        submit.add(findButton);
        add(submit, BorderLayout.EAST);
    }

    /**
        The BuyListener class is used when the user clicks the BUY option. It sets up the interface for buying an investment,
        by adding the necessary text fields, labels, and button options.
    */
    private class BuyListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            buyPanel.setVisible(true);
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            ggPanel.setVisible(false);
            searchPanel.setVisible(false);

            removeInput();
            buyPanel.add(typeL);
            buyPanel.add(kindSM);
            buyPanel.add(symL);
            buyPanel.add(symA);
            buyPanel.add(nameL);
            buyPanel.add(nameA);
            buyPanel.add(qtyL);
            buyPanel.add(qtyA);
            buyPanel.add(prcL);
            buyPanel.add(prcA);
            buyPanel.revalidate();
            input.add(buyPanel);            
            inputVisible();
            buyVisible();
            buying.setVisible(true);
            subBButton.setVisible(true);
            resetButton.setVisible(true);

        }
    } //End of BuyListenerinner class

    /**
        The SubBListener class is used when the user clicks the BUY button within the buy interface.
        It calls the buy function in the Portfolio class to add an investment.
        The buy function in the portfolio class will print any error messages onto the output screen.
    */
    private class SubBListener implements ActionListener { // WHEN THE BUTTON IS CLICKED FOR SUBMIT, CALL THE FUNCTION!
        public void actionPerformed(ActionEvent e){
            symbol = symA.getText();
            name = nameA.getText();
            quantity = qtyA.getText();
            price = prcA.getText();
            boolean valid = Portfolio.buy(kind, symbol, name, quantity, price);
            if (valid == true){
                setToText("Successful purchase!");
            }
        }
    }

    /**
        The SellListener class is used when the user clicks the SELL option. It sets up the interface for selling an investment,
        by adding the necessary text fields, labels, and button options.
    */
    private class SellListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            sellPanel.setVisible(true);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            ggPanel.setVisible(false);
            searchPanel.setVisible(false);

            removeInput(); // reset all components
            sellPanel.add(symL);
            sellPanel.add(symA);
            sellPanel.add(qtyL);
            sellPanel.add(qtyA);
            sellPanel.add(prcL);
            sellPanel.add(prcA);   
            sellPanel.revalidate();
            input.add(sellPanel);
            inputVisible();
            selling.setVisible(true);
            subSButton.setVisible(true);
            resetButton.setVisible(true);
        }
    } //End of SellListenerinner class

    /**
        The SubSListener class is used when the user clicks the SELL button within the sell interface.
        It calls the buy function in the Portfolio class to add an investment.
        The buy function in the portfolio class will print any error messages onto the output screen.
    */
    private class SubSListener implements ActionListener { // WHEN THE BUTTON IS CLICKED FOR SUBMIT, CALL THE FUNCTION!
        public void actionPerformed(ActionEvent e){
            symbol = symA.getText();
            quantity = qtyA.getText();
            price = prcA.getText();

            boolean valid = Portfolio.sell(symbol, quantity, price);
            if (valid == true){
                setToText("Successfully sold!");
            }
        }
    }

    /**
        The UpdateListener class is used when the user clicks the UPDATE option. It sets up the interface for updating investments,
        by adding the necessary text fields, labels, and button options. If no investments exits, then the user will be prompt.
    */
    private class UpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            updatePanel.setVisible(true);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            ggPanel.setVisible(false);
            searchPanel.setVisible(false);

            removeInput();
            message.setText("UPDATING INVESTMENTS");
            updatePanel.add(symL);
            updatePanel.add(symA);
            updatePanel.add(nameL);
            updatePanel.add(nameA);
            updatePanel.add(prcL);
            updatePanel.add(prcA);
            updatePanel.revalidate();
            input.add(updatePanel);
            updateVisible();
            updating.setVisible(true);
            saveButton.setVisible(true);
            prevButton.setVisible(true);
            nextButton.setVisible(true);
            symA.setEditable(false);
            nameA.setEditable(false);
            if (Portfolio.invEmpty() == true){
                nextButton.setEnabled(false);
                saveButton.setEnabled(false);
                prcA.setEditable(false);
                setToText("There are no investments to update");
                return;
            }
            if (i == (Portfolio.invSize() - 1)){
                nextButton.setEnabled(false);
            }
            Portfolio.updateName(); // print starting contents (at i = 0) since the list is not empty, these contents should be present
        }
    } //End of UpdateListenerinner class

    /**
        The SubUListener class is used when the user clicks the SAVE button within the update interface.
        It calls the update function in the Portfolio class to update the price of an investment.
        The update function in the portfolio class will print any error messages onto the output screen.
    */
    private class SubUListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            boolean valid = Portfolio.update();
            if (valid == false){
                setToText("Could not update price, please try again");
            }
        }
    }

    /**
        The PrevListener class is used when the user clicks the PREVIOUS button within the update interface.
        It decrements the counter, thereby going to the previous investment, and calls the updateName function
        in the Portfolio class to update textfields of the investment. When the user enters a new price, the save
        function will use the now updated counter to update the price of the investment shown.
        It will enable and disable the next and previous buttons if the counter has reached the start or end of the
        list of investments.
    */
    private class PrevListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            --i;
            if (i < (Portfolio.invSize() - 1)){
                nextButton.setEnabled(true);
            }
            if (i == 0){
                prevButton.setEnabled(false);
            }
            else{
                prevButton.setEnabled(true);
            }
            Portfolio.updateName();
        }
    }

    /**
        Similar to the PrevListener class, the NextListener class is used when the user clicks the NEXT button
        within the update interface. It increments the counter, thereby going to the next investment, and calls
        the updateName function in the Portfolio class to update textfields of the investment.
        When the user enters a new price, the save function will use the now updated counter to update the price
        of the investment shown. It will enable and disable the next and previous buttons if the counter has
        reached the start or end of the list of investments.
    */
    private class NextListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ++i;
            if (i > 0){
                prevButton.setEnabled(true);
            }
            if (i == (Portfolio.invSize() - 1)){
                nextButton.setEnabled(false);
            }
            else{
                nextButton.setEnabled(true);
            }
            if (i < Portfolio.invSize()){
                Portfolio.updateName();
            }
        }
    }

    /**
        The GgListener class is used when the user clicks the GET GAIN option. It sets up the interface for getting the gain of the
        investments, by adding the necessary text fields, labels, and button options.
    */
    private class GgListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ggPanel.setVisible(true);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            searchPanel.setVisible(false);

            removeInput();
            message.setText("INDIVIDUAL GAINS");
            ggPanel.add(gainL);
            ggPanel.add(gainA);
            ggPanel.revalidate();
            input.add(ggPanel);
            gainVisible();
            submit.setVisible(false);
            getting.setVisible(true);
            double gain = Portfolio.getGain();
            gainA.setText("" + gain);
        }
    } //End of GgListenerinner class

    /**
        The SearchListener class is used when the user clicks the SEARCH option. It sets up the interface for searching for an
        investments, by adding the necessary text fields, labels, and button options.
    */
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            searchPanel.setVisible(true);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            ggPanel.setVisible(false);

            removeInput();
            message.setText("SEARCH RESULTS");
            searchPanel.add(symL);
            searchPanel.add(symA);
            searchPanel.add(kwL);
            searchPanel.add(kwA);
            searchPanel.add(lowL);
            searchPanel.add(lowA);   
            searchPanel.add(highL);
            searchPanel.add(highA);   
            searchPanel.revalidate();
            input.add(searchPanel);
            searchVisible();
            searching.setVisible(true);
            findButton.setVisible(true);
            resetButton.setVisible(true);
        }
    } //End of SearchListenerinner class

    /**
        The SubSearchListener class is used when the user clicks the SEARCH button within the search interface.
        It calls the search function in the Portfolio class to search for an investment.
        The search function in the portfolio class will print any error messages onto the output screen.
    */
    private class SubSearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            setToText(""); // clear output for new results
            symbol = symA.getText();
            name = kwA.getText();
            price = lowA.getText();
            price2 = highA.getText();
            Portfolio.search(symbol, name, price, price2);
        }
    }

    /**
        The ExitListener class is used when the user clicks the EXIT option. It saves all file contents by calling the
        saveFile funciton in the Portfolio class, and then terminates the program.
    */
    public class ExitListener implements ActionListener{ // can be multiple listeners, for ex window listener
        public void actionPerformed (ActionEvent e){ // an event handler
            Portfolio.saveFile(Main.fileName); // save contents from a file
            System.exit(0); // either through this button or the 'x' exit
        }
    }

    /**
        The ResetListener class is used when the user clicks the RESET button within the interfaces.
        It clears all text fields, and places the type menu back to the default choice.
    */
    private class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            kindSM.setSelectedIndex(0);
            kind = 1;
            symA.setText("");
            nameA.setText("");
            qtyA.setText("");
            prcA.setText("");
            kwA.setText("");
            highA.setText("");
            lowA.setText("");
        }
    }

    /**
        The KindListener class is used when the user chooses an option within the menu in the buy interface.
        It determines whether a stock or mutualfund is chosen, and based of that, sets the corresponding integer value.
    */
    private class KindListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

            JComboBox cb = (JComboBox)e.getSource();
            String buttonString = (String)cb.getSelectedItem();
            // assigns the kind in Portfolio
            if (buttonString.equals("Stock")){
                kind = 1;
            }
            else if (buttonString.equals("Mutual Fund")){
                kind = 2;
            }
        }
    }

    /**
        The setToText function is used throughout the classes to display a message onto the output screen.
    */
    public void setToText(String text){
        if (text.equals("")){
            output.setText(text);
        }
        else{
            output.setText(output.getText() + "\n" + text);
        }
    }

    /**
        The removeInput function is used every time a new interface is opened
        It removes all components from all panels, sets all components back to their default settings,
        and resets the update counter to 0.
    */
    public void removeInput(){ // removes all input fields and buttons
        
        setToText("");
        message.setText("MESSAGES");
        outputPanel.setVisible(true);

        buyPanel.removeAll();
        sellPanel.removeAll();
        updatePanel.removeAll();
        ggPanel.removeAll();
        searchPanel.removeAll();

        descLabel.setVisible(false);
        kindSM.setVisible(false);
        symA.setVisible(false);
        nameA.setVisible(false);
        qtyA.setVisible(false);
        prcA.setVisible(false);
        kwA.setVisible(false);
        highA.setVisible(false);
        lowA.setVisible(false);
        typeL.setVisible(false);
        symL.setVisible(false);
        nameL.setVisible(false);
        qtyL.setVisible(false);
        prcL.setVisible(false);
        kwL.setVisible(false);
        highL.setVisible(false);
        lowL.setVisible(false);
        subBButton.setVisible(false);
        subSButton.setVisible(false);
        resetButton.setVisible(false);
        prevButton.setVisible(false);
        nextButton.setVisible(false);
        saveButton.setVisible(false);
        findButton.setVisible(false);
        buying.setVisible(false);
        selling.setVisible(false);
        updating.setVisible(false);
        getting.setVisible(false);
        searching.setVisible(false);
        // during update, these variables are changed
        symA.setEditable(true);
        nameA.setEditable(true);
        prcA.setEditable(true);
        prevButton.setEnabled(false);
        nextButton.setEnabled(true);
        i = 0;
        kind = 1;
        // get gain changed this element
        submit.setVisible(true);
    }

    /**
        The buyVisible function is used to display the text fields and labels for the buy interface.
    */
    public void buyVisible(){
        typeL.setVisible(true);
        kindSM.setVisible(true);
        nameL.setVisible(true);
        nameA.setVisible(true);
    }

    /**
        The inputVisible function is used to display the text fields and labels for the buy and sell interface.
    */
    public void inputVisible(){ // sets symbol, quantity, price to true (since they are commonly used together)
        symL.setVisible(true);
        symA.setVisible(true);
        qtyL.setVisible(true);
        qtyA.setVisible(true);
        prcL.setVisible(true);
        prcA.setVisible(true);
    }

    /**
        The updateVisible function is used to display the text fields and labels for the update interface.
    */
    public void updateVisible(){
        symL.setVisible(true);
        symA.setVisible(true);
        nameL.setVisible(true);
        nameA.setVisible(true);
        prcL.setVisible(true);
        prcA.setVisible(true);
    }

    /**
        The gainVisible function is used to display the text fields and labels for the gain interface.
    */
    public void gainVisible(){
        gainL.setVisible(true);
        gainA.setVisible(true);
    }

    /**
        The searchVisible function is used to display the text fields and labels for the search interface.
    */
    public void searchVisible(){
        symL.setVisible(true);
        symA.setVisible(true);
        kwL.setVisible(true);
        kwA.setVisible(true);
        lowL.setVisible(true);
        lowA.setVisible(true);
        highL.setVisible(true);
        highA.setVisible(true);
    }
}