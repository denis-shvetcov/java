package package1;


import java.util.*;

public class AddressBook {
    private Map<String, Address> book;
    private Map<String, Map<String, Set<String>>> inversedBook = new HashMap<>();

    AddressBook(Map<String, Address> bookNew) {
        book= new HashMap<>(bookNew);
        for (Map.Entry<String, Address> pair : bookNew.entrySet()) {
            addToInversed(pair.getValue(),pair.getKey());
        }
    }

    AddressBook() {
        book = new HashMap<>();
    }

    private void addToInversed(Address address,String surname) {
        inversedBook.computeIfAbsent(address.street, key -> new HashMap<>()).
                computeIfAbsent(address.home, key->new HashSet<>()).add(surname);
    }

    private void removeFromInversed(Address address,String surname) {
        inversedBook.get(address.street).get(address.home).remove(surname);
    }
    public void add(String surname, Address address) {
        book.put(surname, address);
        addToInversed(address,surname);
    }


    public void remove(String surname) {
        removeFromInversed(book.remove(surname),surname);
    }

    public void changeAddress(String surname, Address address) {
        removeFromInversed(Objects.requireNonNull(book.replace(surname, address)),surname);
        addToInversed(address,surname);
    }

    public Address getAddress(String surname) {
        return book.get(surname);
    }

    public Set<String> getPeople(String street) {
        Set<String> set = new HashSet<>();
        for (Set<String> pair : inversedBook.get(street).values()) {
            set.addAll(pair);
        }
        return set;
    }

    public Set<String> getPeople(String street, String homeNum) {
        return new HashSet<>(inversedBook.get(street).get(homeNum));
    }


}