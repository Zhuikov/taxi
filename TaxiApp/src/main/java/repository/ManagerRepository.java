package repository;

import core.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {

    private List<Manager> managers = new ArrayList<>();

    public void addManager(Manager manager) {
        managers.add(manager);
    }

    public List<Manager> getAll() {
        return managers;
    }

}
