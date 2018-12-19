package com.example.zad02.service;

import com.example.zad02.domain.Drug;

import java.sql.Connection;
import java.util.List;

public interface DrugManager {

        public int addDrug(Drug drug);
        void deleteDrug(Drug drug);
        public List<Drug> getAllDrugs();
        Connection getConnection();
        public void clearDrugs();

        /* batch insert - transactional */
        public void addAllDrugs(List<Drug> drugs);
        Drug findDrugById(long id);
        Drug findDrugByName(String name);
        Drug removeDrug(Drug drug);


}

