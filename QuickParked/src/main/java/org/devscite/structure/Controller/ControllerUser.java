package org.devscite.structure.Controller;

import org.devscite.Aplication.aggregates.UserDAOImpl;
import org.devscite.Aplication.persistence.IUserDAO;

public class ControllerUser {
    private IUserDAO iUserDAO = new UserDAOImpl();

    public IUserDAO getiUserDAO() {
        return iUserDAO;
    }

    public void setiUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }
}
