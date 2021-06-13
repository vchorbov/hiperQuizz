package quizz.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import quizz.dao.KeyGenerator;
import quizz.dao.UserRepository;
import quizz.exception.EntityAlreadyExistsException;
import quizz.exception.EntityNotFoundException;
import quizz.model.Gender;
import quizz.model.Player;
import quizz.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMemoryImplTest {
    private static final List<User> SAMPLE_USERS = List.of(
            new Player("spy", "spy@gmail.com", "????", Gender.MALE),
            new Player("zina", "zina1@gmail.com", "????", Gender.FEMALE),
            new Player("lora", "lori4ka@abv.bg", ".....", Gender.FEMALE)
    );
    private static final List<User> SAMPLE_USERS_WITH_IDS = List.of(
            new Player(1l,"spy", "spy@gmail.com", "????", Gender.MALE),
            new Player(2l,"zina", "zina1@gmail.com", "????", Gender.FEMALE),
            new Player(3l,"lora", "lori4ka@abv.bg", ".....", Gender.FEMALE)
    );
    private static final User NEW_USER =
            new Player("zoro", "zoro@fake.mail", "asd123ASD!", Gender.MALE);
    private static final User EXISTING_USER =
            new Player(1L, "spy", "UPDATED", "????", Gender.MALE);
    private static final User INVALID_USER =
            new Player(11L, "zoro", "zoro@fake.mail", "asd123ASD!", Gender.MALE);

    private static final long FIRST_USER_ID = 1;

    private KeyGenerator<Long> keyGenerator;
    private UserRepository userRepo;

    private User user;


    @BeforeAll
    static void setup() {
        //log.info("Before all test cases.");
    }

    @AfterAll
    static void cleanup() {
        // log.info("After all test cases.");
    }

    @BeforeEach
    void setUp() {
        // log.info("Before test case");
        keyGenerator = new LongKeyGenerator();
        userRepo = new UserMemoryImpl(keyGenerator);
    }

    @AfterEach
    void tearDown() {
        //  log.info("After test case");
    }

    @Test
    @DisplayName("When calling findAll on the repo, then the list of users is not empty")
    void findAll() {
        fillInUsers(SAMPLE_USERS);
        List<User> users = userRepo.findAll();
        assertNotNull(users);
    }

    @Test
    @DisplayName("When calling findAll on a empty repo, then the list of users is empty")
    void findAllWithEmptyRepo() {
        List<User> users = userRepo.findAll();
        assertEquals(0, users.size());
    }

    @Test
    @DisplayName("When called findById with id=1, then the first user in the repo is returned")
    void findById() {
        fillInUsers(SAMPLE_USERS);
        User firstUserInTheRepo = userRepo.findById(FIRST_USER_ID).orElse(null);
        assertEquals(SAMPLE_USERS.get(0), firstUserInTheRepo);
    }

    @Test
    @DisplayName("When create is called on the repo whit user, then that user is persisted in the repo")
    void create() {
        try {
            user = userRepo.create(NEW_USER);
        } catch (EntityAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotNull(user);
        assertEquals(FIRST_USER_ID, user.getId());
        assertEquals(NEW_USER, userRepo.findById(FIRST_USER_ID).orElse(null));
    }

    @Test
    @DisplayName("When create is called on the repo whit an existing user, then that method throws UserAlreadyExists ex.")
    void createWithException() {
        fillInUsers(SAMPLE_USERS);
        assertThrows(EntityAlreadyExistsException.class, () -> {
            user = userRepo.create(EXISTING_USER);
        });
    }

    @Test
    @DisplayName("When createBatch is called with a Collection, then all the Users in the collection are persisted")
    void createBatch() {
        int created = 0;
        try {
            created = userRepo.createBatch(SAMPLE_USERS_WITH_IDS);
        } catch (EntityAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(SAMPLE_USERS.size(), created);
        assertEquals(SAMPLE_USERS_WITH_IDS.get(1), userRepo.findById(2l).orElse(null));
    }

    @Test
    @DisplayName("When createBatch is called with a Collection of already existing users, then " +
            "the method throws ex")
    void createBatchWithException() {
        fillInUsers(SAMPLE_USERS);
        assertThrows(EntityAlreadyExistsException.class,  ()-> userRepo.createBatch(SAMPLE_USERS_WITH_IDS));
    }

    @Test
    @DisplayName("When given a valid id, then successfully updates")
    void update() {
        fillInUsers(SAMPLE_USERS);
        try {
            userRepo.update(EXISTING_USER);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(userRepo.findById(1l).orElse(null).getUsername().equals("spy"));
        assertTrue(userRepo.findById(1l).orElse(null).getEmail().equals("UPDATED"));
    }

    @Test
    @DisplayName("When given an invalid id, then exception is thrown")
    void updateWithException() {
        fillInUsers(SAMPLE_USERS);
        assertThrows(EntityNotFoundException.class, () -> userRepo.update(INVALID_USER));
    }

    @Test
    @DisplayName("When given an invalid id, then exception is thrown")
    void deleteById() {
        fillInUsers(SAMPLE_USERS);
        assertTrue(userRepo.findById(1L).orElse(null).getUsername().equals("spy"));
        try {
           User deleted = userRepo.deleteById(1L);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        assertNull(userRepo.findById(1L).orElse(null));
    }
    @Test
    @DisplayName("When given an invalid id, then exception is thrown")
    void deleteByIdWithException() {
        fillInUsers(SAMPLE_USERS);
        assertThrows(EntityNotFoundException.class, () -> userRepo.deleteById(9L));
    }

    @Test
    void count() {
        fillInUsers(SAMPLE_USERS);
        long actual = SAMPLE_USERS.size();
        long expected = userRepo.count();
        assertEquals(actual, expected);
    }

    @Test  @DisplayName("When given an empty repo, then count returns 0")
    void countWhenEmpty() {
        long actual = 0l;
        long expected = userRepo.count();
        assertEquals(actual, expected);
    }

    @Test @DisplayName("When given an empty repo, then count returns 0")
    void drop() {
        fillInUsers(SAMPLE_USERS);
        assertEquals(SAMPLE_USERS.size(), userRepo.count());
        userRepo.drop();
        assertEquals(0l, userRepo.count());
    }

    @Test @DisplayName("When the username is valid, then count returns the user")
    void findUserByUsername() {
        fillInUsers(SAMPLE_USERS);
        User found = new User();
        try {
            found = userRepo.findUserByUsername("zina");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("zina", found.getUsername());
        assertEquals("zina1@gmail.com", found.getEmail());
        assertEquals("FEMALE", found.getGender().toString());

    }
    @Test @DisplayName("When the username is invalid, then throws Exception")
    void findUserByUsernameWithException() {
        fillInUsers(SAMPLE_USERS);
        assertThrows(EntityNotFoundException.class, ()-> userRepo.findUserByUsername("fake"));
    }

    private void fillInUsers(List<User> usersList) {
        usersList.stream().forEach(user -> {
            try {
                userRepo.create(user);
            } catch (EntityAlreadyExistsException e) {
               // log.error("Error adding products to repository", e);
            }
        });
    }
}