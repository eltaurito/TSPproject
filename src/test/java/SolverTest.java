import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class SolverTest {

    @Test(timeout=181000)
    public void ch130() {
        Main main=new Main();
        String[] strings={"ch130.tsp","1556841779225","522"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void d198() {
        Main main=new Main();
        String[] strings={"d198.tsp","1556841789649","128"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void eil76() {
        Main main=new Main();
        String[] strings={"eil76.tsp","1556841800005","1280"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void fl1577() {
        Main main=new Main();
        String[] strings={"fl1577.tsp","1556919736598","25"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void kroA100() {
        Main main=new Main();
        String[] strings={"kroA100.tsp","1556841829980","518"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void lin318() {
        Main main=new Main();
        String[] strings={"lin318.tsp","1556934163454","1492"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void pcb442() {
        Main main=new Main();
        String[] strings={"pcb442.tsp","1556919283957","683"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void pr439() {
        Main main=new Main();
        String[] strings={"pr439.tsp","1556841861579","21"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void rat783() {
        Main main=new Main();
        String[] strings={"rat783.tsp","1556923962991","165"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout=181000)
    public void u1060() {
        Main main=new Main();
        String[] strings={"u1060.tsp","1556941483195","70"};
        try {
            main.main(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

