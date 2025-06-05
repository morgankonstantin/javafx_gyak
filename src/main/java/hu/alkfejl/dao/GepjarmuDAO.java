package hu.alkfejl.dao;

import hu.alkfejl.model.Gepjarmu;

import java.util.List;

public interface GepjarmuDAO {
    boolean add(Gepjarmu jarmu);
    List<Gepjarmu> findAll();
}
