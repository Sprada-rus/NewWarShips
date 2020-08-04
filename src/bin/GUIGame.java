package bin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;

public class GUIGame {
    private JFrame frame;
    private String [] alphaLine = new String[] {"а","б","в","г","д"};
    private Integer [] numLine = new Integer[] {1,2,3,4,5};
    private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
    public static int numRow = 5;
    public static int numColumn = 5;
    public static int howManyShips = 3;
    public JTextField userIn;
    public JPanel sendPanel;
    public JTextField[][] test = new JTextField[numRow][numColumn];
    private int numUsersPunch;
    public ArrayList<Ships> shipsArrayList = new ArrayList<Ships>();
    private CreateShips helper = new CreateShips();
    private JLabel userResult;
    public ArrayList<String> historyUserPunched = new ArrayList<String>();

    public GUIGame(){
        int sizeShip = 1;
        for (int i = 0; i < howManyShips; i++){
            Ships ships = new Ships();
            ships.setName("Корабль" + i);
            shipsArrayList.add(ships);
        }

        for (Ships shipsSet : shipsArrayList){
            ArrayList<String> newLocation = helper.placeShips(sizeShip);
            sizeShip++;
            System.out.println("Проверка локации GUIGame " + newLocation);
            shipsSet.setLocationCells(newLocation);
        }

        for (int i = 0; i < numColumn; i++){
            hashMap.put(alphaLine[i], numLine[i]);
        }
    }

    public void start(){

        frame = new JFrame("Морской бой");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        GridLayout gridAlpha = new GridLayout(1,numColumn);
        gridAlpha.setHgap(100);
        JPanel alphaPanel = new JPanel(gridAlpha);
        for (int i = 0; i < numColumn; i++){
            JLabel labelAlpha = new JLabel(alphaLine[i]);
            alphaPanel.add(labelAlpha);
        }
        alphaPanel.setBorder(BorderFactory.createEmptyBorder(10,80,0,0));

        GridLayout gridNum = new GridLayout(numRow,1);
        gridAlpha.setVgap(5);
        JPanel numPanel = new JPanel(gridNum);
        for (int i = 0; i < numColumn; i++){
            JLabel labelNum = new JLabel(numLine[i].toString());
            labelNum.setSize(2,2);
            numPanel.add(labelNum);
        }
        numPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));

        GridLayout gridTextField = new GridLayout(5,5);
        gridTextField.setVgap(1);
        gridTextField.setHgap(1);
        JPanel battleField = new JPanel(gridTextField);
        for (int i = 0; i < numRow; i++){
            for (int j = 0; j < numColumn; j++) {
                    JTextField textBattle = new JTextField(10);
                    textBattle.setEnabled(false);
                    test[i][j] = textBattle;
                battleField.add(textBattle);
            }
        }
        battleField.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        JLabel inText = new JLabel("Введите свой вариант\t");
        userIn = new JTextField(10);
        JButton sendButton = new JButton("Отправить");
        sendPanel = new JPanel();
        sendPanel.add(inText);
        sendPanel.add(userIn);
        sendPanel.add(sendButton);
        sendButton.addActionListener(new SendButtonItmListener());

        background.add(BorderLayout.SOUTH, sendPanel);
        background.add(BorderLayout.NORTH, alphaPanel);
        background.add(BorderLayout.CENTER, battleField);
        background.add(BorderLayout.WEST, numPanel);
        frame.getContentPane().add(BorderLayout.CENTER, background);

        JPanel resultPanel = new JPanel();
        userResult = new JLabel();
        userResult.setEnabled(false);
        userResult.setSize(400, 400);
        resultPanel.add(userResult);
        frame.getContentPane().add(BorderLayout.SOUTH, resultPanel);

        //Меню окна
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Игра");
        JMenuItem newGameItm = new JMenuItem("Новая Игра");
//        JMenuItem saveGameItm = new JMenuItem("Сохранить игру");
//        JMenuItem loadGameItm = new JMenuItem("Загрузить игру");
//        saveGameItm.addActionListener(new SaveGameItmListener());
//        loadGameItm.addActionListener(new LoadGameItmListener());
        gameMenu.add(newGameItm);
//        gameMenu.add(saveGameItm);
//        gameMenu.add(loadGameItm);
        menuBar.add(gameMenu);

        newGameItm.addActionListener(new NewGameItmListener());

        frame.setJMenuBar(menuBar);

        //Задаем параметры окна
        frame.setVisible(true);
        frame.setSize(800,1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
//
//    public void saveNewFile(File nameFileField, File nameFileShips){
//        JTextField[][] saveField = new JTextField[numRow][numColumn];
//        HashMap<String, String> shipHashMap = new HashMap<String, String>();
//
//        for (int i = 0; i < numRow; i++){
//            for (int j = 0; j < numColumn; j++) {
//                if (test[i][j].getForeground() == Color.RED) {
//                    saveField[i][j].setForeground(Color.RED);
//                } else if (test[i][j].getForeground() == Color.BLACK) {
//                    saveField[i][j].setForeground(Color.BLACK);
//                }
//            }
//        }
//
//        for (int i = 0; i < howManyShips; i++){
//            Ships testShips = shipsArrayList.get(i);
//
//            for (int j = 0; j < testShips.getCountCells(); j++){
//                shipHashMap.putIfAbsent(testShips.getName(), testShips.getLocationCells(j));
//            }
//
//        }
//
//        for (Map.Entry entry : shipHashMap.entrySet()){
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//
//        try{
//            FileOutputStream fosField = new FileOutputStream(new File(String.valueOf(nameFileField)));
//            ObjectOutputStream oos = new ObjectOutputStream(fosField);
//            oos.writeObject(saveField);
//            oos.close();
//            FileOutputStream fosShips = new FileOutputStream(new File(String.valueOf(nameFileShips)));
//            ObjectOutputStream oosShips = new ObjectOutputStream(fosShips);
//            oosShips.writeObject(arrayShips);
//            oosShips.close();
//        } catch (Exception ex){
//            ex.printStackTrace();
//            System.out.println("Error save file");
//        }
//
//    }
//
//    public void loadFile(File nameFileField, File nameFileShips){
//        JTextField[][] loadField = new JTextField[numRow][numColumn];
//        Ships[] arrayShips = new Ships[howManyShips];
//
//        frame.setVisible(false);
//        GUIGame newGame = new GUIGame();
//        newGame.start();
//
//        try{
//            FileInputStream fisField = new FileInputStream(new File(String.valueOf(nameFileField)));
//            ObjectInputStream ois = new ObjectInputStream(fisField);
//            loadField = (JTextField[][]) ois.readObject();
//            ois.close();
//        } catch (Exception ex){
//            ex.printStackTrace();
//            System.out.println("Error load file");
//        }
//
//        try {
//            FileInputStream fisShips = new FileInputStream(new File(String.valueOf(nameFileShips)));
//            ObjectInputStream oisShips = new ObjectInputStream(fisShips);
//            arrayShips = (Ships[]) oisShips.readObject();
//            oisShips.close();
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        for (int i = 0; i < numRow; i++){
//            for (int j = 0; j < numColumn; j++){
//                test[i][j] =  loadField[i][j];
//            }
//        }
//
//        for (int i = 0; i < howManyShips; i++){
//            shipsArrayList.add(arrayShips[i]);
//        }
//    }

    //Классы слушателей
    public class NewGameItmListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i < numRow; i++){
                for (int j = 0; j < numColumn; j++){
                    test[i][j].setBackground(Color.WHITE);
                    userResult.setText("");
                }
            }
            frame.setVisible(false);
            GUIGame newGame = new GUIGame();
            newGame.start();
        }
    }
//
//    public class SaveGameItmListener implements ActionListener{
//        public void actionPerformed(ActionEvent actionEvent) {
//            File fileName = new File("saveResultWarShip.ser");
//            File fileShips = new File("saveShipsWarShip.ser");
//            saveNewFile(fileName, fileShips);
//        }
//    }
//
//    public class LoadGameItmListener implements ActionListener{
//        public void actionPerformed(ActionEvent actionEvent) {
//            File fileName = new File("saveResultWarShip.ser");
//            File fileShips = new File("saveShipsWarShip.ser");
//            loadFile(fileName, fileShips);
//        }
//    }

    public class SendButtonItmListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            numUsersPunch++;
            String guess = null;
            String result = "Мимо";
            String firsSym = null;
            userResult.setText("");
            Color red = new Color(255,36,0);
            Color black = new Color(0);
            guess = userIn.getText().toLowerCase();
            firsSym = String.valueOf(guess.charAt(0));
            int numTabColumn = hashMap.get(firsSym) - 1;
            int numTabRow = Integer.parseInt(String.valueOf(guess.charAt(1))) - 1;
//            System.out.println("numTabColumn " + numTabColumn + " numTabRow " + numTabRow);
            for (Ships shipsListTest: shipsArrayList){
                result = shipsListTest.checkPunch(guess);
                if (result.equals("Попал")){
                    test[numTabRow][numTabColumn].setBackground(red);
                    userResult.setForeground(red);
                    userResult.setText("Ты попал, продолжай!\n");
                    historyUserPunched.add(userIn.getText());
                    break;
                } else if (result.equals("Потопил")){
                    shipsArrayList.remove(shipsListTest);
                    test[numTabRow][numTabColumn].setBackground(red);
                    howManyShips--;
                    userResult.setText("Ты потопил корабль, осталось " + howManyShips + "\n");
                    historyUserPunched.add(userIn.getText());
                    break;
                } else if (result.equals("Мимо") && !historyUserPunched.contains(userIn.getText())){
                    test[numTabRow][numTabColumn].setBackground(black);
                    userResult.setText("Мимо, но всё ещё впереди!\n");
                } else if (result.equals("Мимо") && historyUserPunched.contains(userIn.getText())){
                    userResult.setText("Ты видимо промахнулся в кординатах, потому-что, ты уже сюда стрелял");
                }
            }
            if (howManyShips != 0) {
                userIn.setText("");
                numUsersPunch++;
            } else {
                System.exit(0);// Дальше будет выпадать окно с результатом игрока
            }
        }
    }
}
