package practicePackage;

import org.testng.annotations.Test;

public class groups {

    @Test(groups = {"Sanity"})
    public void M1() {
        System.out.println("Sanity");
    }

    @Test(groups = {"Regression"})
    public void M2() {
        System.out.println("Regression");
    }

    @Test(groups = {"Sanity"})
    public void M3() {
        System.out.println("Sanity");
    }

    @Test(groups = {"Sanity"})
    public void M4() {
        System.out.println("Sanity");
    }
}
