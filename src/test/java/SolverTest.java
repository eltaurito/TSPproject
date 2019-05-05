import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class SolverTest {

    @Test(timeout=181000)
    public void ch130() {
        Main main=new Main();
        String[] strings={"ch130.tsp","1556841779225"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void d198() {
        Main main=new Main();
        String[] strings={"d198.tsp","1557011312335"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void eil76() {
        Main main=new Main();
        String[] strings={"eil76.tsp","1557009903311"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void fl1577() {
        Main main=new Main();
        String[] strings={"fl1577.tsp","1557013800558"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void kroA100() {
        Main main=new Main();
        String[] strings={"kroA100.tsp","1557010080744"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void lin318() {
        Main main=new Main();
        String[] strings={"lin318.tsp","1557012099088"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void pcb442() {
        Main main=new Main();
        String[] strings={"pcb442.tsp","1557013305716"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void pr439() {
        Main main=new Main();
        String[] strings={"pr439.tsp","1557010753407"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void rat783() {
        Main main=new Main();
        String[] strings={"rat783.tsp","1557012483023"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void u1060() {
        Main main=new Main();
        String[] strings={"u1060.tsp","1557013756846"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

