package br.com.myBank.myBank.rest;

import br.com.myBank.myBank.domain.person.Person;
import br.com.myBank.myBank.service.person.PersonService;
import jar.presentation.PersonApi;
import jar.presentation.representation.CreatedPersonResponse;
import jar.presentation.representation.PersonRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class PersonApiController implements PersonApi {

    private ModelMapper modelMapper;
    private PersonService service;

    @Override
    public ResponseEntity<CreatedPersonResponse> createPerson(PersonRequest request) {
        log.info("### Start register person: {}", request);

        service.savePerson(modelMapper.map(request, Person.class));
        return null;
    }
}
