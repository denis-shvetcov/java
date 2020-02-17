package package1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Address ad1 = new Address("Ленина","15","33");
        Address ad2 = new Address("Ленина","1","12");
        Address ad3 = new Address("Ленина","15","22");
        Address ad4 = new Address("Кировская","23","1");
        Address ad5 = new Address("Кировская","15","33");
        Address ad6 = new Address("Кировская","2","66");
        Address ad7 = new Address("Кедровая","33","33");
        Address ad8 = new Address("Садовая","15","12");
        Map<String,Address> book = new HashMap<>();
        book.put("Сачин",ad1);
        book.put("Кислевев",ad2);
        book.put("Постин",ad3);
        book.put("Иванов",ad4);
        book.put("Петров",ad5);
        book.put("Адик",ad6);
        book.put("Сома",ad7);
        book.put("Ручкин",ad8);
        AddressBook addressBook = new AddressBook(book);

        Set<String> set = addressBook.getPeople("Ленина","15");
        for (String el: set
             ) {
            System.out.println(el);
        }
        Address newad = new Address("Кукуева","12","33");
        addressBook.changeAddress("Сачин", newad);
        set = addressBook.getPeople("Ленина","15");
        for (String el: set
        ) {
            System.out.println(el);
        }

        Address newad2 = new Address("Кукуева","12","12");
        addressBook.add("Васильков", newad2);
        set = addressBook.getPeople("Кукуева","12");
        for (String el: set
        ) {
            System.out.println(el);
        }
        System.out.println();
        System.out.println();
        addressBook.showAll();
    }

}
