package JUnit4Demo;

/*
需求:如果SquareRoot()方法,需要使用不同的参数,测试多次,需要提供多个@Test方法;

JUnit4创建不同参数测试只需要五个步骤:
        创建一个不含参数的通用测试;
        创建一个返回Collection类型的static feeder 方法, 并用@Parameters注释加以修饰;
为在步骤1中定义的通用方法所要求的参数类型创建类成员;
        创建一个持有这些参数类型的构造函数,并把这些参数类型和步骤3中定义的类成员相应地联系起来;
        通过@RunWith注释,指定测试用例和Parameterized类一起运行;
*/

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculatorTest2 {

    private Calculator cal = new Calculator();
    private int param;
    private int result;

    // 构造函数,对变量进行初始化
    public CalculatorTest2(int param, int result){
        this.param = param;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                {2, 4},
                {0, 0},
                {-3, 9}
        });
    }

    // 不含参数的通用测试
    @Test
    public void squareTest(){
        int temp = cal.square(param);
        Assert.assertEquals(result, temp);
    }

}
