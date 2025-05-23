package com.webstardemo.second_converter.service;

import com.webstardemo.second_converter.model.AppUser;
import com.webstardemo.second_converter.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindUserById_UserExists_ReturnsUser() {
        AppUser user = new AppUser("tester", "tester@example.com");

        when(userRepository.findById(1L)).thenReturn(user);

        AppUser result = userService.findUserById(1L);

        assertNotNull(result);
        assertEquals("tester", result.getUsername());
        assertEquals("tester@example.com", result.getEmail());
        verify(userRepository).findById(1L);
    }

    @Test
    void testFindUserById_UserDoesNotExist_ReturnsNull() {
        when(userRepository.findById(2L)).thenReturn(null);

        AppUser result = userService.findUserById(2L);

        assertNull(result);
        verify(userRepository).findById(2L);
    }

    @Test
    void testSaveUser_ReturnsSavedUser() {
        AppUser user = new AppUser("newuser", "newuser@example.com");

        when(userRepository.save(user)).thenReturn(user);

        AppUser saved = userService.save(user);

        assertNotNull(saved);
        assertEquals("newuser", saved.getUsername());
        assertEquals("newuser@example.com", saved.getEmail());
        verify(userRepository).save(user);
    }
}
