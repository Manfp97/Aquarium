package com.example.aquarium.Configuration;

import com.example.aquarium.Entities.*;
import com.example.aquarium.Repositories.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final Environment env;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final FishRepository fishRepository;
    private final ProductRepository productRepository;
    private final TankRepository tankRepository;

    public ApplicationStartup(UserRepository userRepository, RolesRepository rolesRepository,
                              Environment env, BCryptPasswordEncoder bCryptPasswordEncoder, DeliveryNoteRepository deliveryNoteRepository, FishRepository fishRepository, ShopRepository shopRepository, EmployeeRepository employeeRepository, ProductRepository productRepository, TankRepository tankRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.env = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.fishRepository = fishRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.tankRepository = tankRepository;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        String db_initialize = env.getProperty("execution.mode");
        if (Objects.equals(db_initialize, "1")) {
            altaRoles();
            altaUsers();
            altaEmployees();
            altaFish();
            altaProducts();
            altaTanks();
        }
    }

    public void altaRoles() {
        Roles roles = new Roles();
        roles.setNameRol("ROLE_ADMIN");
        ;
        Roles srole = rolesRepository.save(roles);

        Roles roles1 = new Roles();
        roles1.setNameRol("ROLE:ANONYMOUS");
        Roles srole1 = rolesRepository.save(roles1);

        Roles roles2 = new Roles();
        roles2.setNameRol("ROLE:USER");
        Roles srole2 = rolesRepository.save(roles2);

        Roles roles3 = new Roles();
        roles3.setNameRol("ROLE:EMPLOYEE");
        Roles srole3 = rolesRepository.save(roles3);

    }

    public void altaUsers() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("morena12345"));
        user.setActive(true);
        user.setRol(rolesRepository.findByNameRol("ROLE_ADMIN"));

        UserDetails userDetails = new UserDetails();
        userDetails.setName("Username");
        userDetails.setPassport("User Passport".substring(0, 10));
        userDetails.setSurname("User Surname");
        userDetails.setMail("User Mail");
        userDetails.setAddress("User Adress");

        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        userRepository.save(user);

        User userEmployee1 = new User();
        userEmployee1.setUsername("employee1");
        userEmployee1.setPassword(bCryptPasswordEncoder.encode("morena12345"));
        userEmployee1.setActive(true);
        userEmployee1.setRol(rolesRepository.findByNameRol("ROLE_EMPLOYEE"));

        UserDetails userEmployeeDetails1 = new UserDetails();
        userEmployeeDetails1.setName("Manuel");
        userEmployeeDetails1.setSurname("Fernández Pernía ");
        userEmployeeDetails1.setAddress("Villanueva del Trabuco ");
        userEmployeeDetails1.setPassport("021020241");
        userEmployeeDetails1.setMail("employee1@example.com");

        userEmployeeDetails1.setUser(userEmployee1);
        userEmployee1.setUserDetails(userEmployeeDetails1);

        userRepository.save(userEmployee1);
    }

    public void altaFish() {
        List<Fish> fish = new ArrayList<>();

        fish.add(new Fish(
                1,
                "lamprey",
                "Petromyzon marinus",
                "Atlantic ocean",
                "male",
                5,
                "Larvae live under the mud and once they grow up they become parasites, " +
                        "sticking to other fish, feeding on their blood and flesh"
        ));

        fish.add(new Fish(2,
                "Smooth hammerhead",
                "Sphyrna zygaena",
                "All oceans",
                "female",
                5,
                "Aggresive towards humans, "
        ));
    }

    public void altaTanks() {
        List<Tank> tanks = new ArrayList<>();

        tanks.add(new Tank(
                1,
                2.5,
                "small aquariums"
        ));

        tanks.add(new Tank(2, 5.5, "small aquariums"));
        tanks.add(new Tank(3, 10.0, "small aquariums"));
        tanks.add(new Tank(4, 15.0, "small aquariums"));
        tanks.add(new Tank(5, 20.0, "mid-sized aquariums"));
        tanks.add(new Tank(6, 20.0, "mid-sized aquariums"));

    }

    public void altaEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                1,
                "Manuel Fernández",
                27
        ));

    }

    public void altaProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(
                1,
                "Decoration algae",
                5.5,
                "Decoration items",
                "fresh water"
        ));
    }
}


