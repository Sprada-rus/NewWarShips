package bin;

import java.util.ArrayList;

public class Ships {
    private ArrayList<String> locationCells;
    private String name;

    public void setName(String nameShip){
        name = nameShip;
    }

    public String getName(){
        return name;
    }

    public void setLocationCells(ArrayList<String> location){
        locationCells = location;
    }

    public String getLocationCells(int i){
        return locationCells.get(i);
    }

    public int getCountCells(){
        int x = 0;
        for (String word : locationCells){
            x++;
        }
        return x;
    }

    public String checkPunch(String userInput){
        String result = "Мимо";
        int index = locationCells.indexOf(userInput);
        System.out.println(index);
        if (index >= 0){
            locationCells.remove(index);

            if (locationCells.isEmpty()){
                result = "Потопил";
                System.out.println("Ура! Вы потопили " + name + " :)");
            } else {
                result = "Попал";
            }
        }
        return result;
    }
}
