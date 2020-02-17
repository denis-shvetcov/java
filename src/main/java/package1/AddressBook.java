package package1;


import java.util.*;

public class AddressBook {
    private Map<String, Address> book;
    private Map<String, Map<String, Set<String>>> inversedbook = new HashMap<>();

    AddressBook(Map<String, Address> bookNew) {
        book = new HashMap<>();
        book.putAll(bookNew);
        for (Map.Entry<String, Address> pair : bookNew.entrySet()) {
            addToInversed(pair.getValue(),pair.getKey());
//            inversedbook.getOrDefault(pair.getValue().street, new HashMap<>()).getOrDefault(pair.getValue().home, new HashSet<>()).add(pair.getKey());
        }
    }

    AddressBook() {
        book = new HashMap<>();
    }

    private void addToInversed(Address address,String surname) {
        if (inversedbook.get(address.street) == null) inversedbook.put(address.street, new HashMap<>());
        if (inversedbook.get(address.street).get(address.home) == null) inversedbook.get(address.street).
                put(address.home, new HashSet<>());
        inversedbook.get(address.street).get(address.home).add(surname);
    }

    private void removeFromInversed(Address address,String surname) {
        inversedbook.get(address.street).get(address.home).remove(surname);
    }
    public void add(String surname, Address address) {
        book.put(surname, address);
        addToInversed(address,surname);
    }


    public void remove(String surname) {
        Address address = book.get(surname);
        book.remove(surname);
        removeFromInversed(address,surname);
    }

    public void changeAddress(String surname, Address address) {
        Address change = book.get(surname);
        book.replace(surname, address);
        removeFromInversed(change,surname);
        addToInversed(address,surname);
    }

    public Address getAddress(String surname) {
        return book.get(surname);
    }

    public Set<String> getPeople(String street) {
        Set<String> set = new HashSet<>();
        Map<String, Set<String>> map = new HashMap<>();
        map.putAll(inversedbook.get(street));
        for (Map.Entry<String, Set<String>> pair : map.entrySet()) {
            set.addAll(pair.getValue());
        }
        return set;
    }

    public Set<String> getPeople(String street, String homeNum) {
        Set<String> set = new HashSet<>();
        Map<String, Set<String>> map = new HashMap<>();
        map.putAll(inversedbook.get(street));
        for (Map.Entry<String, Set<String>> pair : map.entrySet()) {
            if (pair.getKey().equals(homeNum)) set.addAll(pair.getValue());
        }
        return set;
    }


    //    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj instanceof AddressBook) {
//            AddressBook other = (AddressBook) obj;
//            if (other.book.size() != this.book.size()) return false;
//            for (Map.Entry<String,String[]> entry: other.book.entrySet())
//                if (!(this.book.get(entry.getKey()) != null && Arrays.equals(this.book.get(entry.getKey()), entry.getValue()))) {
//                    return false;
//                }
//            return true;
//        }
//        return false;
//    }
    public void showAll() {
        System.out.println("Нормальная книга");
        for (Map.Entry<String, Address> pair : book.entrySet()
        ) {
            System.out.println("Фамилия: " + pair.getKey() + ", улица: " + pair.getValue().street + ", дом: "
                    + pair.getValue().home + ", квартира: " + pair.getValue().appart);
        }
        System.out.println();
        System.out.println();
        System.out.println("Обратная книга");
        for (Map.Entry<String, Map<String, Set<String>>> pair : inversedbook.entrySet()) {
            System.out.println("Улица: " + pair.getKey() + "Дом: " + pair.getValue());
        }
    }

}