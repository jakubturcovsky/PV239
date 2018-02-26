package cz.pv239.seminar2;

import java.util.ArrayList;
import java.util.List;

public class UserDb extends ArrayList<User> {

    public UserDb() {
        init();
    }

    public void init() {
        add(new User("Kuba", "Turcovsky"));
        add(new User("Marek", "Sedlak"));
        add(new User("Bara", "Buhnova"));
        add(new User("Milos", "Zeman"));
        add(new User("Andrej", "Babis"));
        add(new User("Tomio", "Okamura"));
        add(new User("Emma", "Watson"));
        add(new User("Katie", "Holmes"));
        add(new User("Alexis", "Texas"));
        add(new User("Sasha", "Grey"));
        add(new User("Tim", "Cook"));
        add(new User("Elon", "Musk"));
        add(new User("Mark", "Zuckerberg"));
        add(new User("Bill", "Gates"));
        add(new User("Barrack", "Obama"));
        add(new User("Leonardo", "DiCaprio"));
    }

    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        for (User user : this) {
            list.add(user.getName());
        }

        return list;
    }
}
