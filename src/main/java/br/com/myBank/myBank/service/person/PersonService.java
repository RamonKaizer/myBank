package br.com.myBank.myBank.service.person;

import br.com.myBank.myBank.domain.person.Person;
import br.com.myBank.myBank.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person savePerson(Person person) {

        person.getBirth();
        return null;
    }
}
