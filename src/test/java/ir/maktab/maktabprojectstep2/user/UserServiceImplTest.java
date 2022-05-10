package ir.maktab.maktabprojectstep2.user;

import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import ir.maktab.maktabprojectstep2.request.UserSearchRequest;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    UnderServiceService underServiceService;

    @Test
    void saveUser() {
        User user = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("mahdi@gmail.com")
                .password("asdd")
                .role(Role.CUSTOMER)
                .status(UserStatus.NEW)
                .build();
            userService.save(user);
        Assertions.assertEquals(user.getId(), userService.findById(user.getId()).get().getId());
    }



    @Test
    void saveExpert(){
        User user = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("mahdi@gmail.com")
                .password("asdd")
                .role(Role.CUSTOMER)
                .status(UserStatus.NEW)
                .image(getImage())
                .build();
        userService.save(user);
        Assertions.assertEquals(user.getId(), userService.findById(user.getId()).get().getId());
    }

    @Test
    void changePassword(){
        User user = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("mahdi@gmail.com")
                .password("asdd")
                .role(Role.CUSTOMER)
                .status(UserStatus.NEW)
                .build();
        userService.save(user);
        user.setPassword("sada");
        userService.save(user);
        userService.save(user);
        Assertions.assertEquals("test", userService.findById(user.getId()).get().getPassword());
    }

    @Test
    void findAllExpert(){
        List<User> allSpecialty = userService.findALlExpert();
        for (User user:
             allSpecialty) {
            Assertions.assertEquals(Role.EXPERT, user.getRole());
        }
    }

    @Test
    void getAllUser(){
        List<User> allUser = userService.findAllUser();
        for (User user:
                allUser) {
            Assertions.assertEquals(Role.CUSTOMER, user.getRole());
        }
    }

    @Test
    void addServiceInExpert(){
        Service service = Service.builder()
                .title("manzel")
                .build();
        serviceService.save(service);
        UnderService underService = UnderService.builder()
                .details("asdasd")
                .service(service)
                .basePrice(123)
                .build();
        underServiceService.save(underService);

        User user = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("mahdi@gmail.com")
                .password("asdd")
                .role(Role.CUSTOMER)
                .status(UserStatus.NEW)
                .score(4)
                .build();

        user.getServices().add(underService);
        userService.save(user);

        Assertions.assertEquals(user.getServices().size(), userService.findById(user.getId()).get().getServices().size());

    }

    @Test
    void findAll(){
        List<User> all = userService.findAll();
        Assertions.assertEquals(all.size(),userService.findAll().size());
    }

    public byte[] getImage() {
        File file = new File("./../resources/download.jpg");
        byte[] bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }

    @Test
    void search(){
        saveAllUser();
        UnderService underService = underServiceService.findById(1).get();
        UserSearchRequest userSearchRequest = UserSearchRequest.builder()
                .underService(underService)
                .role(Role.CUSTOMER)
                .email("m@gmail.com")
                .firstname("ali")
                .lastname("mohammadi")
                .build();

        List<User> search = userService.search(userSearchRequest);
        for(User user:search){
            Assertions.assertEquals("m@gmail.com",user.getEmail());
            Assertions.assertEquals("ali",user.getFirstname());
            Assertions.assertEquals("mohammadi", user.getLastname());
            Assertions.assertEquals(Role.EXPERT, user.getRole());
        }
    }

    private void saveAllUser() {
        Service service = Service.builder()
                .title("manzel")
                .build();
        serviceService.save(service);
        UnderService underService = UnderService.builder()
                .details("test")
                .service(service)
                .basePrice(123)
                .build();
        underServiceService.save(underService);
        User user = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("m@gmail.com")
                .password("asdd")
                .role(Role.EXPERT)
                .status(UserStatus.NEW)
                .build();
        user.getServices().add(underService);
        User user1 = User.builder()
                .firstname("ali")
                .lastname("mohammadi")
                .email("masda@gmail.com")
                .password("asdd")
                .role(Role.CUSTOMER)
                .status(UserStatus.NEW)
                .build();
        userService.save(user1);
        userService.save(user);

    }
}