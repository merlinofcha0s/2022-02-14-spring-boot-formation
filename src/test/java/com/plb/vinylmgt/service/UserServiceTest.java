package com.plb.vinylmgt.service;

import com.plb.vinylmgt.VinylmgtApplication;
import com.plb.vinylmgt.domain.User;
import com.plb.vinylmgt.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = VinylmgtApplication.class)
public class UserServiceTest {

    public static final String DEFAULT_EMAIL = "test@toto.com";
    public static final String DEFAULT_RANDOM_PASSWORD = "sdkjezebzebfsdhfdsjdsjqdjdjqsjazejzke";
    public static final String DEFAULT_FIRSTNAME = "toto";
    public static final String DEFAULT_LASTNAME = "tata";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserService userServiceWithMockedRepository;

    @Mock
    private UserRepository userRepositoryMock;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;

    public static User createEntity() {
        User user = new User();
        user.setEmail(DEFAULT_EMAIL);
        user.setPassword(DEFAULT_RANDOM_PASSWORD);
        user.setFirstname(DEFAULT_FIRSTNAME);
        user.setLastname(DEFAULT_LASTNAME);
        user.setAuthorities("USER");
        return user;
    }

    @BeforeEach
    public void init() {
        user = createEntity();
        userServiceWithMockedRepository = new UserService(userRepositoryMock, passwordEncoder);
    }

    @Test
    public void getAllShouldWork() {
        User user1 = createEntity();

        userRepository.save(user1);
        userRepository.save(user);

        List<User> allUser = userService.getAllUser();

        Assertions.assertThat(allUser).isNotEmpty();
        Assertions.assertThat(allUser.size()).isEqualTo(2);
        Assertions.assertThat(allUser).contains(user1, user);
    }

    @Test
    public void getAllShouldWorkMocked() {
        User user1 = createEntity();

        Mockito.when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(user1, user));

        List<User> allUser = userServiceWithMockedRepository.getAllUser();

        Assertions.assertThat(allUser).isNotEmpty();
        Assertions.assertThat(allUser.size()).isEqualTo(1);
        Assertions.assertThat(allUser).contains(user1, user);
    }

    @Test
    public void saveWorkSuccessfuly() {
        userService.save(user);

        Optional<User> verifyUser = userRepository.findByEmail(DEFAULT_EMAIL);
        assertThat(verifyUser).isPresent();
        assertThat(verifyUser.get().getAuthorities()).isEqualTo("USER");
        assertThat(verifyUser.get().getPassword()).startsWith("$");
    }

    @Test
    public void saveWorkSuccessfulyMockedRepo() {
        Mockito.when(userRepositoryMock.save(user)).thenReturn(createEntity());
        userServiceWithMockedRepository.save(user);

        Optional<User> verifyUser = userRepository.findByEmail(DEFAULT_EMAIL);
        assertThat(verifyUser).isNotPresent();
    }

}
