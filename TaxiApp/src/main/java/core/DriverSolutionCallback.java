package core;

@FunctionalInterface
public interface DriverSolutionCallback {

    void result(PersonInfo personInfo, boolean solution);

}
