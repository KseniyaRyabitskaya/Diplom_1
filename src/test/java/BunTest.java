import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {
                        "black bun",
                        100.52f
                },
                {
                        "red bun",
                        300.13f
                }
        };
    }

    @Before
    public void createIngredient() {
        bun = new Bun(name, price);
    }

    @Test
    public void getPriceReturnCorrectPriceTest() {
        assertEquals(price, bun.getPrice(), 0);
    }

    @Test
    public void getNameReturnCorrectNameTest() {
        assertEquals(name, bun.getName());
    }
}
