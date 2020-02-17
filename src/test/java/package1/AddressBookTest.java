package package1;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AddressBookTest {

    @Test
    public void add() {
        Map<String, Address> book = new HashMap<>();
        Address add1 = new Address("Ленина", "12", "33");
        Address add2 = new Address("Кировская", "1", "123");
        Address add3 = new Address("Коломенская", "65", "42");
        book.put("Савичев", add1);
        book.put("Радищев", add2);
        book.put("Рулев", add3);
        AddressBook addressBook = new AddressBook(book);
        assertEquals(add1, addressBook.getAddress("Савичев"));
        assertEquals(add2, addressBook.getAddress("Радищев"));
        assertEquals(add3, addressBook.getAddress("Рулев"));
    }

    @Test
    public void remove() {
        AddressBook addressBook = new AddressBook();
        Address add1 = new Address("Ленина", "12", "33");
        Address add2 = new Address("Кировская", "1", "123");
        Address add3 = new Address("Коломенская", "65", "42");
        addressBook.add("Савичев", add1);
        addressBook.add("Радищев", add2);
        addressBook.add("Рулев", add3);
        addressBook.remove("Савичев");
        assertNull(addressBook.getAddress("Савичев"));
        addressBook.remove("Радищев");
        assertNull(addressBook.getAddress("Радищев"));
        addressBook.remove("Рулев");
        assertNull(addressBook.getAddress("Рулев"));
    }

    @Test
    public void changeAddress() {
        Address addBefore1 = new Address("Кедровая", "112", "22");
        Address addAfter1 = new Address("Вишневая", "52", "15");
        Address addBefore2 = new Address("Рижская", "64", "74");
        Address addAfter2 = new Address("Победы", "11", "95");
        Address addBefore3 = new Address("Лужская", "42", "63");
        Address addAfter3 = new Address("Пролетарская", "64", "79");
        AddressBook addressBook = new AddressBook();
        addressBook.add("Хаиров", addBefore1);
        addressBook.add("Зубов", addBefore2);
        addressBook.add("Мазалов", addBefore3);
        addressBook.changeAddress("Хаиров", addAfter1);
        assertEquals(addAfter1, addressBook.getAddress("Хаиров"));
        addressBook.changeAddress("Зубов", addAfter2);
        assertEquals(addAfter2, addressBook.getAddress("Зубов"));
        addressBook.changeAddress("Мазалов", addAfter3);
        assertEquals(addAfter3, addressBook.getAddress("Мазалов"));
    }

    @Test
    public void getAddress() {
        AddressBook addressBook = new AddressBook();
        Address add1 = new Address("Кедровая", "55", "12");
        Address add2 = new Address("Строителей", "156", "89");
        Address add3 = new Address("Попова", "16", "92");
        addressBook.add("Гуща", add1);
        addressBook.add("Ощепков", add2);
        addressBook.add("Иванов", add3);

        assertEquals(add1, addressBook.getAddress("Гуща"));
        assertEquals(add2, addressBook.getAddress("Ощепков"));
        assertEquals(add3, addressBook.getAddress("Иванов"));
    }

    @Test
    public void getPeople() {
        AddressBook addressBook = new AddressBook();

        addressBook.add("Иванов", new Address("Кедровая", "8", "105"));
        addressBook.add("Часовских", new Address("Кедровая", "1", "16"));
        addressBook.add("Ашин", new Address("Кедровая", "8", "15"));
        addressBook.add("Гуща", new Address("Победы", "1", "33"));
        addressBook.add("Ощепков", new Address("Победы", "1", "16"));
        addressBook.add("Кайгородова", new Address("Победы", "4", "84"));

        Set<String> kedrovaya = new HashSet<>();
        kedrovaya.add("Иванов");
        kedrovaya.add("Ашин");
        kedrovaya.add("Часовских");
        Set<String> kedrovayaHome = new HashSet<>();
        kedrovayaHome.add("Иванов");
        kedrovayaHome.add("Ашин");

        Set<String> pobedi = new HashSet<>();
        pobedi.add("Гуща");
        pobedi.add("Кайгородова");
        pobedi.add("Ощепков");
        Set<String> pobediHome = new HashSet<>();
        pobediHome.add("Ощепков");
        pobediHome.add("Гуща");


        assertTrue(kedrovaya.containsAll(addressBook.getPeople("Кедровая")));
        assertTrue(kedrovayaHome.containsAll(addressBook.getPeople("Кедровая", "8")));
        assertTrue(pobedi.containsAll(addressBook.getPeople("Победы")));
        assertTrue(pobediHome.containsAll(addressBook.getPeople("Победы", "1")));

    }

}