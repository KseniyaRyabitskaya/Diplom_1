import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {
                        IngredientType.SAUCE,
                        "hot sauce",
                        100.55f
                },
                {
                        IngredientType.FILLING,
                        "sausage",
                        300.12f
                }
        };
    }

    @Before
    public void createIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceReturnCorrectPriceTest() {
        assertEquals(price, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameReturnCorrectNameTest() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getTypeReturnCorrectTypeTest() {
        assertEquals(type, ingredient.getType());
    }

}
