package bin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class GUIGameMain {
    JFrame mainFrame;
    GUIGameMain game;
    private int numColumn;
    private int numRow;
    private int howManyShips;



    public static void main(String[] args) {
        GUIGameMain game = new GUIGameMain();
        game.firstWindow();
    }


    public void firstWindow() {
        mainFrame = new JFrame("Добро пожаловать в Морской Бой");
        mainFrame.setIconImage(getMainImage());
        JPanel imgPanel = new JPanel();
        Image image = getMainImage();
        JLabel imageLabel = new JLabel((Icon) image);
        imgPanel.add(imageLabel);
        imgPanel.setBackground(Color.BLACK);
        mainFrame.getContentPane().add(BorderLayout.CENTER, imgPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        JButton startGame = new JButton("Начать играть!");
        JButton loadGame = new JButton("Загрузка игры");
        JButton exitGame = new JButton("Выход");
        buttonPanel.add(startGame);
//        buttonPanel.add(loadGame);
        buttonPanel.add(exitGame);
        startGame.addActionListener(new StartGameListener());
//        loadGame.addActionListener(new LoadGameListener());
        exitGame.addActionListener(new ExitGameListener());
        mainFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setSize(800,550);
        mainFrame.setLocationRelativeTo(null);
    }

    protected static Image getMainImage(){
        URL imageURL = GUIGameMain.class.getResource("C:\\Users\\ilyas\\Desktop\\MyCodeJava\\NewWarShips\\src\\bin\\mainPicture.jpg");
        if (imageURL != null){
            return new ImageIcon(imageURL).getImage();
        } else {
            return null;
        }
    }

    public class StartGameListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            GUIGame startGame = new GUIGame();
            startGame.start();
        }
    }
//
//    public class LoadGameListener implements ActionListener{
//        public void actionPerformed(ActionEvent a){
//
//        }
//    }
    public class ExitGameListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            System.exit(0);
        }
    }
}
