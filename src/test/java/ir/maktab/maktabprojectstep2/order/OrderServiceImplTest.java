package ir.maktab.maktabprojectstep2.order;

import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import ir.maktab.maktabprojectstep2.service.order.OrderService;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService ;
    @Autowired
    UnderServiceService underServiceService ;
    @Autowired
    ServiceService serviceService ;
    @Autowired
    UserService userService ;

    @Test
    void save() {
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
                .build();
        userService.save(user);
        Order order = Order.builder()
                .proposedPrice(213)
                .address("asdasd")
                .status(StatusOrder.STARTED)
                .wordTime(LocalDateTime.now())
                .underService(underService)
                .user(user)
                .build();
        orderService.save(order);
        Assertions.assertEquals(order.getId(), orderService.findById(order.getId()).get().getId());
    }

    @Test
    void findAll(){
        List<Order> all = orderService.findAll();
        Assertions.assertEquals(all.size(), orderService.findAll().size());
    }

}