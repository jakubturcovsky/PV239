package cz.pv239.seminar2

import java.util.*

class UserDb : ArrayList<User>() {

    val names: List<String>
        get() = map { it.name }.toList()

    init {
        add(User("Kuba", "Turcovsky"))
        add(User("Marek", "Sedlak"))
        add(User("Bara", "Buhnova"))
        add(User("Milos", "Zeman"))
        add(User("Andrej", "Babis"))
        add(User("Tomio", "Okamura"))
        add(User("Emma", "Watson"))
        add(User("Katie", "Holmes"))
        add(User("Alexis", "Texas"))
        add(User("Sasha", "Grey"))
        add(User("Tim", "Cook"))
        add(User("Elon", "Musk"))
        add(User("Mark", "Zuckerberg"))
        add(User("Bill", "Gates"))
        add(User("Barrack", "Obama"))
        add(User("Leonardo", "DiCaprio"))
    }
}
