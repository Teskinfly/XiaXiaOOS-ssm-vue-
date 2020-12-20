package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransationTest {
    @Test
    public void f() {
        for (int i = 0; i < 10; i++)
            System.out.println(Math.random()*3);
    }
}
