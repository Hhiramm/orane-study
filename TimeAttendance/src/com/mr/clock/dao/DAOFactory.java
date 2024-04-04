package com.mr.clock.dao;

public class DAOFactory {
    public static DAO getDAO(){
        return new DAOMysqlImpl();
    }
}