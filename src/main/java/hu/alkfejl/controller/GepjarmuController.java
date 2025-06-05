package hu.alkfejl.controller;

import hu.alkfejl.dao.GepjarmuDAO;
import hu.alkfejl.dao.GepjarmuSQLite;
import hu.alkfejl.model.Gepjarmu;

import java.util.List;


public class GepjarmuController {
    private GepjarmuDAO dao;
    private static GepjarmuController instance = null;


    private GepjarmuController() {
        try {
            dao = new GepjarmuSQLite();

            Class c = Class.forName("org.sqlite.JDBC");
            dao = (GepjarmuDAO) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static GepjarmuController getInstance() {
        if (instance == null) {
            synchronized (GepjarmuSQLite.class) {
                if (instance == null)
                    instance = new GepjarmuController();
            }
        }

        return instance;
    }

    boolean add(Gepjarmu jarmu) {
        return dao.add(jarmu);
    }

    List<Gepjarmu> findAll() {
        return dao.findAll();
    }
}
