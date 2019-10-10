package JUnit4Demo;

import org.junit.*;

// 单元测试类
public class CalculatorTest{

    private Calculator cal = new Calculator();

    @BeforeClass
    public static void before(){
        System.out.println("类加载之前....");
    }

    @AfterClass
    public static void after(){
        System.out.println("类被销毁之前...");
    }

    // @BeforeClass和@AfterClass都需要被 static 修饰

    @Before
    public void setUp() throws Exception{
        System.out.println("测试方法开始之前...");
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("测试方法结束!");
    }

    @Test
    @Ignore // 忽略测试
    public void testAdd(){
        int result = cal.add(1, 3);
        System.out.println("测试加法++++");
        Assert.assertEquals(4, result);
    }

    @Test
    public void testMinus(){
        int result = cal.minus(5, 2);
        System.out.println("测试减法----");
        Assert.assertEquals(3, result);
    }

    @Test
    public void testMultiply(){
        int result = cal.multiply(4, 2);
        System.out.println("测试乘法****");
        Assert.assertEquals(8, result);
    }

    // 超时测试, 单位毫秒
    // 若测试运行超过 1000毫秒时,测试失败
    @Test(timeout = 1000)
    public void testSquareRoot(){
        System.out.println("测试平方根");
        cal.squareRoot(4);
    }

    // 异常测试
    @Test(expected = ArithmeticException.class)
    public void testDivide() throws Exception{
        System.out.println("测试除法...");
        cal.divide(4, 0);
    }
}
