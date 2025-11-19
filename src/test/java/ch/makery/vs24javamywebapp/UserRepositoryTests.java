package ch.makery.vs24javamywebapp;

import ch.makery.vs24javamywebapp.user.User;
import ch.makery.vs24javamywebapp.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("nrobinkivit@gmail.com");
        user.setPassword("qwerty");
        user.setFirstName("Robin Risto");
        user.setLastName("Konovit");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        // Convert Iterable to List
        List<User> userList = StreamSupport.stream(users.spliterator(), false)
                .toList();
        Assertions.assertFalse(userList.isEmpty());

        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("qwerty1234");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertEquals(updatedUser.getPassword(), "qwerty1234");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertTrue(optionalUser.isPresent());

        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertFalse(optionalUser.isPresent());
    }
}
