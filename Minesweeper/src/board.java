import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // package of classes for handling GUI Events
import java.sql.Array;
import java.util.ArrayList;

public class board implements MouseListener, ActionListener {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel bottomPanel;
    private JButton[][] buttons;
    private JLabel gameText;
    private JLabel description;
    private JLabel description1;
    private JLabel description2;
    private JButton easy = new JButton();
    private JButton medium = new JButton();
    private JButton hard = new JButton();

    private int flagCount = 10;
    private ArrayList<JButton> arr = new ArrayList<JButton>();
    private int spot1;
    private int spot2;
    public static int counter = 0;
    private ImageIcon redFlag;
    private ImageIcon scaledRedFlag;
    private boolean status = true;
    int boardRow = 10;
    int boardCol = 10;
    public board() {
        redFlag = new ImageIcon("/Users/ethanli/Downloads/flag.png");
        frame = new JFrame(); // main window/container of the program
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the program when the window is closed
        frame.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(229, 255, 204));
        westPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(200,500));
        eastPanel.setPreferredSize(new Dimension(200,500));

        topPanel.setPreferredSize(new Dimension(800, 100));
        frame.add(topPanel, BorderLayout.NORTH);
        gameText = new JLabel();
        description = new JLabel();
        description1 = new JLabel();
        description2 = new JLabel();

        gameText.setText("Minesweeper");
        description.setText("Flags");
        description1.setText("Left: ");
        description2.setText(Integer.toString(flagCount));
        gameText.setFont(new Font("Ink Free", Font.PLAIN, 75));
        description.setFont(new Font("Ink Free", Font.PLAIN, 65));
        description1.setFont(new Font("Ink Free", Font.PLAIN, 65));
        description2.setFont(new Font("Ink Free", Font.PLAIN, 65));

        topPanel.add(gameText);

        westPanel.add(description);
        westPanel.add(description1);
        westPanel.add(description2);

        easy.addActionListener(this);
        easy.setActionCommand("easy");
        easy.setPreferredSize(new Dimension(150,75));
        easy.setText("Easy Level");

        medium.addActionListener(this);
        medium.setActionCommand("medium");
        medium.setPreferredSize(new Dimension(150,75));
        medium.setText("Medium Level");


        hard.addActionListener(this);
        hard.setActionCommand("hard");
        hard.setPreferredSize(new Dimension(150,75));
        hard.setText(("Hard Level"));


        eastPanel.add(easy);
        eastPanel.add(medium);
        eastPanel.add(hard);


        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(10, 10));
        buttons = new JButton[10][10];
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setName("");
                buttons[row][col].setText("");
//                buttons[row][col].setBorderPainted(false);
                buttons[row][col].setBackground(new Color(204, 255, 153)); // sets the background color of the button
//                buttons[row][col].setBackground(new Color(255, 229, 204)); // sets the background color of the button

                buttons[row][col].setOpaque(true);
//                buttons[row][col].setForeground(Color.GRAY);
                buttons[row][col].setFont(new Font("MV Boli", Font.BOLD, 50)); //sets the font of the button text
                buttons[row][col].setFocusable(false); // removes the thin box around the text
                buttons[row][col].addMouseListener(this); // takes a instance of a class that implements ActionListener
                buttons[row][col].setActionCommand("Play");
                centerPanel.add(buttons[row][col]);
            }

        }
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.setVisible(true);

//        int buttonWidth = buttons[0][0].getWidth();
//        int buttonHeight = buttons[0][0].getHeight();
        Image scaledFlag = redFlag.getImage().getScaledInstance(100, 100,Image.SCALE_SMOOTH);
        scaledRedFlag = new ImageIcon(scaledFlag);

    }
    public void boardSetup(int r, int c, int f){
        boardRow = r;
        boardCol = c;
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                centerPanel.remove(buttons[row][col]);
            }
        }
        frame.remove(centerPanel);
//        JPanel mediumPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(r, c));
        buttons = new JButton[r][c];
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setName("");
                buttons[row][col].setText("");
                buttons[row][col].setBorderPainted(true);
                buttons[row][col].setBackground(new Color(204, 255, 153)); // sets the background color of the button

                buttons[row][col].setOpaque(true);
//                buttons[row][col].setForeground(Color.GRAY);
                buttons[row][col].setFont(new Font("MV Boli", Font.BOLD, f)); //sets the font of the button text
                buttons[row][col].setFocusable(false); // removes the thin box around the text
                buttons[row][col].addMouseListener(this); // takes a instance of a class that implements ActionListener
                buttons[row][col].setActionCommand("Play");
                centerPanel.add(buttons[row][col]);

            }
        }
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void easyMode(){
        reset();
        boardSetup(10,10,50);
        flagCount = 10;
        description2.setText(Integer.toString(flagCount));
        Image scaledFlag = redFlag.getImage().getScaledInstance(100, 100,Image.SCALE_SMOOTH);
        scaledRedFlag = new ImageIcon(scaledFlag);


    }
    public void mediumMode(){
        reset();
        boardSetup(14,18,25);
        flagCount = 40;
        description2.setText(Integer.toString(flagCount));
        Image scaledFlag = redFlag.getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH);
        scaledRedFlag = new ImageIcon(scaledFlag);
    }
    public void hardMode(){
        reset();
        boardSetup(20,24,15);
        flagCount = 99;
        description2.setText(Integer.toString(flagCount));
        Image scaledFlag = redFlag.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
        scaledRedFlag = new ImageIcon(scaledFlag);
    }

    public void reset(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setText("");
                gameText.setText("Minesweeper");
                buttons[row][col].setName("");
            }
        }
        counter = 0;
        status = true;
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("easy")){
            easyMode();
        }
        if(e.getActionCommand().equals("medium")){
            mediumMode();
        }
        if(e.getActionCommand().equals("hard")){
            hardMode();
        }
    }
    public void mousePressed(MouseEvent e) {
            // this method is called when a button is pressed
        if (status) {
            for (int row = 0; row < buttons.length; row++) {
                for (int col = 0; col < buttons[row].length; col++) {
                    if (e.getSource() == buttons[row][col]) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                                if (buttons[row][col].getIcon() == null) {
                                    if(flagCount>0) {
                                        buttons[row][col].setIcon(scaledRedFlag);
                                        flagCount--;
                                        description2.setText(Integer.toString(flagCount));
                                    }
                                }
                                else {
                                    buttons[row][col].setIcon(null);
                                    flagCount++;
                                    description2.setText(Integer.toString(flagCount));

                                }

                        }
                        else if (counter == 0) {
                            spot1 = row;
                            spot2 = col;
                            setMines();
                            buttons[row][col].setText(Integer.toString(checkSurrounding(row, col)));
                            counter++;
                            checkSafe(row, col);
                            colorValue();

                        }
                        else if (buttons[row][col].getName().equals("Mine")) {
                            gameText.setText("Game Over.");
                            System.out.println("Game Over.");
                            status = false;
                            for (int x = 0; x < buttons.length; x++) {
                                for (int y = 0; y < buttons[row].length; y++) {
                                    if(buttons[x][y].getName().equals("Mine")){
                                        buttons[x][y].setText("O");
                                    }

                                }
                            }
                        } else {
                            buttons[row][col].setText(Integer.toString(checkSurrounding(row, col)));
                            checkSafe(row, col);
                            colorValue();
                        }
                        if (checkWin()) {
                            System.out.println("You win");
                            gameText.setText("You Win!");
                            status = false;
                        }

                    }
                }
            }
        }

    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void setMines(){
        int z = 0;
        while(z<flagCount){
            int x = (int)(Math.random()*boardRow);
            int y = (int)(Math.random()*boardCol);
            if(!buttons[x][y].getName().equals("Mine") && x!=spot1 && x!=spot1-1 && x!=spot1+1 && y!=spot2 && y!=spot2-1 && y!=spot2+1){
                buttons[x][y].setName("Mine");
                z++;
            }
        }

    }
    public int checkSurrounding(int a, int b){
        int count = 0;
        for(int i = -1; i<=1; i++){
            for(int j = -1; j<=1; j++){
                if(a+i>=0 && a+i<boardRow && b+j>=0 && b+j<boardCol){
                    if(buttons[a+i][b+j].getName().equals("Mine")){
                        count++;
                    }

                }
            }
        }
        return count;


    }

    public void colorValue(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {

                if(buttons[row][col].getText().equals("0")){
                    buttons[row][col].setForeground(new Color(255, 229, 204));
                    buttons[row][col].setBorderPainted(false);

                }
                if(buttons[row][col].getText().equals("1")){
                    buttons[row][col].setForeground(new Color(51, 153, 255));
                }
                if(buttons[row][col].getText().equals("2")){
                    buttons[row][col].setForeground(new Color(0, 204, 0));
                }
                if(buttons[row][col].getText().equals("3")){
                    buttons[row][col].setForeground(new Color(255, 51, 51));
                }
                if(buttons[row][col].getText().equals("4")){
                    buttons[row][col].setForeground(new Color(153, 51, 255));
                }
                if(buttons[row][col].getText().equals("5")){
                    buttons[row][col].setForeground(new Color(255, 153, 51));
                }
                if(buttons[row][col].getText().equals("6")){
                    buttons[row][col].setForeground(new Color(102, 255, 255));
                }
                if(buttons[row][col].getText().equals("7")){
                    buttons[row][col].setForeground(new Color(255, 153, 204));
                }
                if(buttons[row][col].getText().equals("8")){
                    buttons[row][col].setForeground(new Color(192, 192, 192));
                }
                if (!buttons[row][col].getText().isEmpty()){
                    buttons[row][col].setBackground(new Color(255, 229, 204));
//                    buttons[row][col].setBorderPainted(false);
                }
            }
        }

    }

    public void checkSafe(int a, int b){
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();

        if(checkSurrounding(a, b) == 0) {
            arr1.add(a);
            arr2.add(b);

//            for(int i = -1; i<=1; i++) {
//                for (int j = -1; j <= 1; j++) {
//                    arr1.add(a+i);
//                    arr2.add(b+j);
//                    while(checkSurrounding(c,d) ==0){
//                        for(int s = -1; s<=1; s++) {
//                            for (int t = -1; t <= 1; t++) {
//                                buttons[c+i][d+j].setText(Integer.toString(checkSurrounding(c+i, d+j)));
//                                c = c+i;
//                                d = d+j;
//                            }
//                        }
//                    }


//                }
//            }
            while(!arr1.isEmpty()){
                buttons[arr1.get(0)][arr2.get(0)].setText(Integer.toString(checkSurrounding(arr1.get(0), arr2.get(0))));
                a = arr1.get(0);
                b = arr2.get(0);
                if(checkSurrounding(arr1.get(0), arr2.get(0)) == 0){
                    for(int i = -1; i<=1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if(a+i>=0 && a+i<boardRow && b+j>=0 && b+j<boardCol) {

//                            buttons[a+i][b+j].setText(Integer.toString(checkSurrounding(arr1.get(0), arr2.get(0))));
                                if (buttons[a + i][b + j].getText().isEmpty()) {
                                    //                            if(checkSurrounding(a+i, b+j) == 0){
                                    arr1.add(a+i);
                                    arr2.add(b + j);


                                }
                            }

                        }
                    }
                }
                arr1.remove(0);
                arr2.remove(0);

            }
        }
    }

    public boolean checkWin(){
        boolean check=true;
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                if(!buttons[row][col].getName().equals("Mine") && buttons[row][col].getText().equals("")){
                    check = false;
                }
            }
        }
        return check;
    }



    public static void main(String[] args) {
        board t = new board();


    }

}
