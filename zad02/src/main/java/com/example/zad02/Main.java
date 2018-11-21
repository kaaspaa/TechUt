package com.example.zad02;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.zad02.domain.Drug;
import com.example.zad02.service.DrugManagerJDBC;

public class Main{
    public static void main(String[] args){
        //DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
        DrugManagerJDBC drg = new DrugManagerJDBC();

        List<Drug> drugList = new ArrayList<>();

        drugList.add(new Drug("Ibuprom",19.99,2020,true));
        drugList.add(new Drug("Wadowicostamlek",5.99,2137,false));
        drugList.add(new Drug("Rutinoscorbit",12.34,2067,true));
        drugList.add(new Drug("Iksdee",86.20,2019,false));
        drugList.add(new Drug("Eutyhrox N50",99.99,2100,true));

        drg.addAllDrugs(drugList);
        System.out.println(drg.getAllDrugs());

        drg.removeDrug(drg.findDrugById(2));
        System.out.println(drg.getAllDrugs());

        DrugManagerJDBC drg2 = new DrugManagerJDBC();
        drg2.addDrug(new Drug("Dekristol 20000",73.99,2200,false));
        System.out.println(drg2.getAllDrugs());

        drg.clearDrugs();
        System.out.println(drg.getAllDrugs());
    }
}