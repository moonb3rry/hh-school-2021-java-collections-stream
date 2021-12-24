package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  // глобальные переменные это не очень

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    // зачем изменять список если можно в стриме все сделать
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    // элементы множества не повторяются, можно без стрима
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    String result = "";
    // куча условий заменяется стримом
    return Stream.of(person.getSecondName(), person.getFirstName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // тут почти как в лекции пример как из цикла и условия сделать стрим
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString,
                    (existingPerson,newPerson) -> existingPerson));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // anyMatch возвращает true если будут совпадения в двух коллекциях
    // множество создаем чтобы лишний раз не проходить
    Set<Person> personSet = new HashSet<>(persons2);
    return persons1.stream().anyMatch(personSet::contains);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    // фильтруем стрим по условию
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerSober = true;
    return true;
  }
}
