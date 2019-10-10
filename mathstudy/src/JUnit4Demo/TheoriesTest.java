package JUnit4Demo;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/*

Parameterized的改良版;
将所有可能的结果都进行测试;

 */

@RunWith(Theories.class)
public class TheoriesTest{

    @DataPoints
    public static String[] names = {"Tony", "Jim"};
    // 等同于:
    // @DataPoint
    // public static String nameT = "Tony";
    // @DataPoint
    // public static String nameJ = "Jim";

    @DataPoints
    public static int[] values = {10, 20};

    @Theory
    public void testMethod(String name, int age){
        System.out.println(String.format("%s's age is %s.", name, age));
    }
}
