package com.warehouse.service;

import com.warehouse.dal.WareHouseDB;
import com.warehouse.model.WareHouse;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WareHouseService {
    WareHouseDB wareHouseDB = new WareHouseDB();

    public void add(WareHouse wareHouse) throws Exception{
        wareHouseDB.add(wareHouse);
        wareHouseDB.saveFile();
    }

    public WareHouse find(String maSP){
        return WareHouseDB.wareHouseList.get(maSP);
    }

    public void updateFile() throws Exception{
        wareHouseDB.saveFile();
    }

    public boolean remove(String maSP) throws Exception{
        boolean result = wareHouseDB.remove(maSP);

        return result;
    }

    public void printData(){
        for (Map.Entry<String, WareHouse> entry : WareHouseDB.wareHouseList.entrySet()){
            System.out.println(entry.getKey() + " | " + entry.getValue().toString());
        }
    }

    public void printEXP(){
        for (Map.Entry<String, WareHouse> entry : WareHouseDB.wareHouseList.entrySet()){
            System.out.println(entry.getKey()+ " | " + entry.getValue().toStringEXP());
        }
    }

    public void loadData() throws Exception{
        wareHouseDB.readFile();
    }

    public int size() throws Exception{
       return WareHouseDB.wareHouseList.size();
    }

    public void sortList(){
        wareHouseDB.sort();
    }

    public boolean checkInputDateMonthYear(String dateMonthYear){
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateMonthYear);
        return matcher.find();
    }
}
