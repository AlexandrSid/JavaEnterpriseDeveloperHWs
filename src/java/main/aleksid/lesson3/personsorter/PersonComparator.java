package java.main.aleksid.lesson3.personsorter;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    // первые идут мужчины
    // выше в списке тот, кто более старший
    // имена сортируются по алфавиту
    @Override
    public int compare(Person o1, Person o2) {
        int sexDifference = o2.getSex().getNumerical() - o1.getSex().getNumerical();
        if (sexDifference != 0) {
            return sexDifference;
        } else {
            int ageDifference = o2.getAge() - o1.getAge();
            if (ageDifference != 0) {
                return ageDifference;
            } else {
                int nameDifference = o1.getName().compareTo(o2.getName());
                if (nameDifference != 0) {
                    return nameDifference;
                }
            }
            try {
                throw new EqualPersonException(o1);
            } catch (EqualPersonException e) {
//                e.printStackTrace();
                System.err.println(e.o1);
                return 0;
            }
            //Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
            //но не в компараторе же, что это за компаратор, который выбрасывает исключение, если объекты равны
            //UPD: блин, а куда тогда, в метод сортировки и перехватывать его там? И должна ли ломаться сортировка в случае наличия одинаковых людей(по-моему нет)
            //Оставлю здесь, тут же буду перехватывать и возвращать 0, но тогда в некоторых алгоритмах на одну пару одинаковых значений будет нескольо раз вылезать исключение
        }
    }

    private class EqualPersonException extends RuntimeException {
        Person o1;
        public EqualPersonException(Person o1) {
            this.o1 = o1;
        }
    }
}
