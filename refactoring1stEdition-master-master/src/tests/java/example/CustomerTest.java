package example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static example.Movie.MovieType.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Movie rembo;
    private Movie lotr;
    private Movie harryPotter;
    private Customer customer;

    // Створення фільмів різних типів
    @Before
    public void setUp() {
        rembo = new Movie("Rembo", REGULAR);
        lotr = new Movie("Lord of the Rings", NEW_RELEASE);
        harryPotter = new Movie("Harry Potter", CHILDRENS);
    }


    // Оренда для кількох філдльмів
    @Test
    public void testStatementForMultipleRentals() {
        List<Rental> rentals = List.of(
                new Rental(rembo, 1),
                new Rental(lotr, 4),
                new Rental(harryPotter, 5)
        );
        customer = new Customer("John Doe", rentals);

        String expectedStatement = "Rental Record for John Doe\n" +
                "\tRembo\t2.0\n" +
                "\tLord of the Rings\t12.0\n" +
                "\tHarry Potter\t4.5\n" +
                "Amount owed is 18.5\n" +
                "You earned 4 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }


    // Оренда звичайного фільму на 3 дні
    @Test
    public void testRegularMovieRental() {
        List<Rental> rentals = List.of(new Rental(rembo, 3));
        customer = new Customer("Alice", rentals);

        String expectedStatement = "Rental Record for Alice\n" +
                "\tRembo\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }


    // Оренда нового релізу на 2 дні
    @Test
    public void testNewReleaseMovieRental() {

        List<Rental> rentals = List.of(new Rental(lotr, 2));
        customer = new Customer("Bob", rentals);

        String expectedStatement = "Rental Record for Bob\n" +
                "\tLord of the Rings\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }


    // Оренда дитячого фільму на 5 днів
    @Test
    public void testChildrensMovieRental() {
        List<Rental> rentals = List.of(new Rental(harryPotter, 5));
        customer = new Customer("Charlie", rentals);

        String expectedStatement = "Rental Record for Charlie\n" +
                "\tHarry Potter\t4.5\n" +
                "Amount owed is 4.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }



    // Перевірка нарахування бонусів за кілька типів фільмів
    @Test
    public void testFrequentRenterPoints() {
        List<Rental> rentals = List.of(
                new Rental(lotr, 4),
                new Rental(rembo, 1)
        );
        customer = new Customer("David", rentals);

        String expectedStatement = "Rental Record for David\n" +
                "\tLord of the Rings\t12.0\n" +
                "\tRembo\t2.0\n" +
                "Amount owed is 14.0\n" +
                "You earned 3 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }



    // Тест для клієнта без оренд
    @Test
    public void testStatementForNoRentals() {
        List<Rental> rentals = List.of();
        customer = new Customer("Empty Customer", rentals);

        String expectedStatement = "Rental Record for Empty Customer\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }


    // Перевірка імені клієнта
    @Test
    public void testCustomerName() {
        List<Rental> rentals = List.of();
        customer = new Customer("Jane Doe", rentals);

        assertEquals("Jane Doe", customer.getName());
    }

    // Оренда нового релізу на 1 день (мінімальна оренда для нового релізу)
    @Test
    public void testNewReleaseMovieRentalForOneDay() {
        List<Rental> rentals = List.of(new Rental(lotr, 1));
        customer = new Customer("Chris", rentals);

        String expectedStatement = "Rental Record for Chris\n" +
                "\tLord of the Rings\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    // Оренда дитячого фільму рівно на 3 дні (мінімальна плата для дитячого фільму)
    @Test
    public void testChildrensMovieRentalForExactlyThreeDays() {
        List<Rental> rentals = List.of(new Rental(harryPotter, 3));
        customer = new Customer("Oliver", rentals);

        String expectedStatement = "Rental Record for Oliver\n" +
                "\tHarry Potter\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    // Оренда змішаних типів фільмів на короткий термін (перевірка кількох типів оренд)
    @Test
    public void testMixedRentalsForShortTerm() {
        List<Rental> rentals = List.of(
                new Rental(rembo, 1),    // REGULAR фільм на 1 день
                new Rental(lotr, 1),     // NEW_RELEASE фільм на 1 день
                new Rental(harryPotter, 1) // CHILDRENS фільм на 1 день
        );
        customer = new Customer("Mixed Rentals Customer", rentals);

        String expectedStatement = "Rental Record for Mixed Rentals Customer\n" +
                "\tRembo\t2.0\n" +
                "\tLord of the Rings\t3.0\n" +
                "\tHarry Potter\t1.5\n" +
                "Amount owed is 6.5\n" +
                "You earned 3 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    // Оренда звичайного фільму на 10 днів (перевірка тривалого терміну оренди)
    @Test
    public void testRegularMovieRentalForLongTerm() {
        List<Rental> rentals = List.of(new Rental(rembo, 10));
        customer = new Customer("Long Term Customer", rentals);

        String expectedStatement = "Rental Record for Long Term Customer\n" +
                "\tRembo\t14.0\n" +
                "Amount owed is 14.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    // Оренда звичайного фільму рівно на 2 дні
    @Test
    public void testRegularMovieRentalForExactlyTwoDays() {
        List<Rental> rentals = List.of(new Rental(rembo, 2));
        customer = new Customer("Eva", rentals);

        String expectedStatement = "Rental Record for Eva\n" +
                "\tRembo\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }
}
