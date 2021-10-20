import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class FortuneTellerFrame extends JFrame {
    
    JPanel MainPanel;
    
    //Panel to show fortune-teller and title
    JPanel ImgPanel;
    JLabel TellerInfo;
    ImageIcon TellerImg;
    
    //Panel to display fortunes
    JPanel TextPanel;
    JTextArea FortuneDisplay;
    JScrollPane Scroll;
    
    //Panel to have buttons for getting fortune and quitting
    JPanel ButtonPanel;
    JButton QuitBtn;
    JButton GetFortune = new JButton("Get your fortune!");

    //create the array of fortunes
    static ArrayList<String> Fortunes = new ArrayList();
        static {
            Fortunes.add("You are going to have a cheesy day.");
            Fortunes.add("You will buy pants tomorrow.");
            Fortunes.add("A large smooth rock is in your future.");
            Fortunes.add("There are many frogs in your future.");
            Fortunes.add("The path to success holds many pit stops");
            Fortunes.add("You will pass away in the next 1000 years");
            Fortunes.add("Don't trust people named Gerald");
            Fortunes.add("You will meet someone.");
            Fortunes.add("God will not be so forgiving tomorrow.");
            Fortunes.add("You will meet Jack Black in your lifetime.");
            Fortunes.add("This fortune can't be understood by mortals");
            Fortunes.add("Buy a 5kg bag of rice.");
        }
    //variables used to store the last displayed fortune in the fortune list
    int SavedValue = -1;
    int value;


    public FortuneTellerFrame() throws HeadlessException {

        MainPanel = new JPanel();

        //construct the sub-panels using their individual functions
        createImgPanel();
        createTextPanel();
        createButtonPanel();

        //add the sub-panels to the main panel and use boxlayout to orient them vertically
        MainPanel.add(ImgPanel);
        MainPanel.add(TextPanel);
        MainPanel.add(ButtonPanel);
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        add(MainPanel);
    }

    private void createImgPanel() {
        TellerImg = new ImageIcon("src/TESTIMAGE.png");
        TellerInfo = new JLabel("Testy the Fortune Teller", TellerImg, JLabel.CENTER);
        TellerInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        TellerInfo.setHorizontalTextPosition(JLabel.CENTER);
        TellerInfo.setVerticalTextPosition(JLabel.NORTH);
        ImgPanel = new JPanel();
        ImgPanel.add(TellerInfo);
    }

    private void createTextPanel() {
        TextPanel = new JPanel();
        FortuneDisplay = new JTextArea("",8,50);
        FortuneDisplay.setEditable(false);
        FortuneDisplay.setFont(new Font("Serif", Font.ITALIC, 16));
        Scroll = new JScrollPane(FortuneDisplay);
        TextPanel.add(Scroll);
    }

    private void createButtonPanel() {

        ButtonPanel = new JPanel();
        QuitBtn = new JButton("Quit");
        QuitBtn.addActionListener((ActionEvent ae) -> {System.exit(0);});
        GetFortune = new JButton("Read My Fortune!");
        GetFortune.addActionListener(new FortuneListener());
        ButtonPanel.add(GetFortune);
        ButtonPanel.add(QuitBtn);


    }

    public class FortuneListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            //randomly pick a number 0 - 11 to call from the list of fortunes
            Random rand = new Random();

            //do while loop will only end if the number chosen is not the same as the last number chosen
            do{
               value = rand.nextInt(11);
            }while(SavedValue == value);

            //Save the index value for the last fortune so that it can't be used again and display the chosen fortune
            SavedValue = value;
            FortuneDisplay.append(Fortunes.get(value) + "\n");
        }
    }
}
