package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Вспомогательный класс для обёртывания списка адресатов в XML

//Определяет имя корневого элемента
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;
// Необязательное имя, которое мы можем задать для элемента
    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}