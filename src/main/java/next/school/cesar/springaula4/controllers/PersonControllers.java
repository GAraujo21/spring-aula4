package next.school.cesar.springaula4.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import next.school.cesar.springaula4.entities.Person;

@RestController
@RequestMapping("/person")
public class PersonControllers {
    
    ArrayList<Person> listPerson = new ArrayList<Person>();
    int contador = 1;

    @GetMapping
    public ResponseEntity<List<Person>> getListPerson(){
        if(listPerson.size() <= 0){
            return new ResponseEntity<List<Person>>(this.listPerson, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<Person>>(this.listPerson, HttpStatus.OK);
        }
    }

    @PostMapping //Created
    public ResponseEntity<Person> create(@RequestBody Person p){
        this.listPerson.add(p);
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // /person/12
    public ResponseEntity<Person> update(
            @RequestBody Person p,
            @PathVariable(value = "id") int id) {
        for (int i = 0; i < listPerson.size(); i++) {
            if (listPerson.get(i).getId() == id) {
                listPerson.get(i).setName(p.getName());
                listPerson.get(i).setAge(p.getAge());
                return new ResponseEntity<Person>(listPerson.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}") // /person/12
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        for (int i = 0; i < listPerson.size(); i++) {
            if (listPerson.get(i).getId() == id) {
                this.listPerson.remove(this.listPerson.get(i));
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
