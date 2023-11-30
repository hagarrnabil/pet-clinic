package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.OwnerService;
import guru.springframework.petclinic.services.PetTypeService;
import guru.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = petTypeService.save(dog);

        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Michael");
        owner.setAddress("10th St.");
        owner.setCity("London");
        owner.setTelephone("5308136");


        Pet johnsPet = new Pet();
        johnsPet.setPetType(dog);
        johnsPet.setOwner(owner);
        johnsPet.setBirthDate(LocalDate.now());
        johnsPet.setName("rocky");
        owner.getPets().add(johnsPet);

        ownerService.save(owner);

        Vet vet = new Vet();
        vet.setFirstName("Lindsay");
        vet.setLastName("Karl");
        vetService.save(vet);

    }
}
