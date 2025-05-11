import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    private Burger burger;

    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunsChangeBunInBurgerTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddIngredientInListTest() {
        burger.addIngredient(firstIngredient);
        assertEquals(true, burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void removeIngredientRemoveIngredientFromListTest() {
        burger.addIngredient(firstIngredient);
        int index = burger.ingredients.indexOf(firstIngredient);
        burger.removeIngredient(index);
        assertEquals(false, burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void moveIngredientMoveIngredientToExpectedPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        int oldIndexFirstIngredient = burger.ingredients.indexOf(firstIngredient);
        burger.moveIngredient(oldIndexFirstIngredient, oldIndexFirstIngredient + 1);
        int newIndexFirstIngredient = burger.ingredients.indexOf(firstIngredient);
        assertEquals(oldIndexFirstIngredient + 1, newIndexFirstIngredient);
    }

    @Test
    public void getPriceReturnCorrectPriceTest() {
        when(bun.getPrice()).thenReturn(50f);
        when(firstIngredient.getPrice()).thenReturn(99.45f);
        when(secondIngredient.getPrice()).thenReturn(100.55f);

        float expected = 300f;

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actual = burger.getPrice();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void getReceiptReturnCorrectReceiptTest() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(50f);

        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("chili sauce");
        when(firstIngredient.getPrice()).thenReturn(99.45f);

        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getPrice()).thenReturn(100.55f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        float expectedPrice = 300f;
        String expected = String.format(
                "(==== black bun ====)%n"
                        +
                        "= sauce chili sauce =%n"
                        +
                        "= filling cutlet =%n"
                        +
                        "(==== black bun ====)%n"
                        +
                        "%nPrice: %f%n", expectedPrice);

        String actual = burger.getReceipt();

        assertEquals(expected, actual);
    }
}
