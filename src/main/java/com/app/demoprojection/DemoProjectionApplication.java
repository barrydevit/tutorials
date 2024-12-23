package com.app.demoprojection;

import com.app.demoprojection.entities.Addresse;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.repositories.AddressRepository;
import com.app.demoprojection.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoProjectionApplication implements CommandLineRunner {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public DemoProjectionApplication(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectionApplication.class, args);
        System.out.println("------ application started ------");
    }

    @Override
    public void run(String... args)  {
        Addresse addresse = new Addresse("DAKAR" , "castor");
        Addresse addresse1 = new Addresse("Conakry" , "Labe");
        Addresse addresse3 = new Addresse("PITA" , "Madina");
        addressRepository.saveAll(List.of(addresse, addresse1, addresse3));
        
        List<Person> personList = new ArrayList<>();

//        int size = 100 ;
//        for (int i = 1; i <= size; i++) {
//            Person person = new Person("firstName"+ i, "lastName"+i , String.format("person%d@gmail.com",i) , "77 123 45 "+i , "metier "+ i, new Date() , i , i%2==0 ? addresse : addresse1);
//            personList.add(person);
//        }
//
//        personRepository.saveAll(personList);

    }
}
