package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.*;
import guru.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialitiesService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = petTypeService.save(dog);

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");
        Speciality savedRadiology = specialitiesService.save(radiology);

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
        System.out.println("Loaded Owners");

        Visit dogVisit = new Visit();
        dogVisit.setPet(johnsPet);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("Sneezy Doggy");

        visitService.save(dogVisit);

        Vet vet = new Vet();
        vet.setFirstName("Lindsay");
        vet.setLastName("Karl");
        vet.getSpecialities().add(savedRadiology);
        vetService.save(vet);

    }
}
