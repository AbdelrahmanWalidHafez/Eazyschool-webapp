package com.eazybytes.eazyschool.service;
import com.eazybytes.eazyschool.constants.Constants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        person.setRoles(rolesRepository.getByRoleName(Constants.STUDENT_ROLE));
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}