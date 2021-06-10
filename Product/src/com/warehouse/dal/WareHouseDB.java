package com.warehouse.dal;

import com.warehouse.model.WareHouse;

import java.io.*;
import java.util.*;

public class WareHouseDB {
    public static Map<String, WareHouse> wareHouseList;


    static {
        wareHouseList = new HashMap<>();
    }

    public void saveFile() throws Exception{
                FileOutputStream fos = new FileOutputStream("warehouse.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                    for (Map.Entry<String, WareHouse> entry : wareHouseList.entrySet()){
                        bos.write(entry.getValue().toStringCSV().getBytes());
                    }
                bos.flush();
                bos.close();
                fos.close();
            }


    public void add(WareHouse wareHouse){
        wareHouseList.put(wareHouse.getMaSP(), wareHouse);
    }

    public void readFile() throws Exception{
        File file = new File("warehouse.txt");
        if (!file.exists()){
            return;
        }else {
            FileReader fileReader = new FileReader("warehouse.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] str = line.split(",");
                WareHouse wareHouse = new WareHouse(str[0],str[1],Integer.parseInt(str[2]),Double.parseDouble(str[3]),str[4],str[5],str[6],Long.parseLong(str[7]));
                add(wareHouse);
            }
        }
    }

    public void sort(){
        List<Map.Entry<String,WareHouse>> list = new ArrayList<Map.Entry<String, WareHouse>>();
        list.addAll(wareHouseList.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, WareHouse>>() {
            @Override
            public int compare(Map.Entry<String, WareHouse> o1, Map.Entry<String, WareHouse> o2) {
                return o1.getValue().getName().compareTo(o2.getValue().getName());
            }
        });
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getValue().toString());
        }
    }

    public boolean remove(String maSP) throws Exception{
        boolean result = wareHouseList.remove(maSP) == null;
        return result;
    }
}
