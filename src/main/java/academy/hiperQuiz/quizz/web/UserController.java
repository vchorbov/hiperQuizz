package academy.hiperQuiz.quizz.web;
;
import academy.hiperQuiz.quizz.entity.User;
import academy.hiperQuiz.quizz.exception.EntityAlreadyExistsException;
import academy.hiperQuiz.quizz.exception.EntityNotFoundException;
import academy.hiperQuiz.quizz.exception.InvalidEntityDataException;
import academy.hiperQuiz.quizz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public  User getUserById(@PathVariable("id") Long id) throws EntityNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createProduct(@Valid @RequestBody User user) throws EntityAlreadyExistsException {
        User created =  userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri())
                .body(created);
    }
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public User updateProduct(@PathVariable("id") Long id, @Valid @RequestBody User user) throws EntityNotFoundException {
        if (!id.equals(user.getId())) {
            throw new InvalidEntityDataException(
                    String.format("ID in URL:'%s' is different from ID in request body ID:'%s'.",
                            id, user.getId())
            );
        }
        return userService.updateUser(user);
    }
    @DeleteMapping(path = "/{id}")
    public User deleteProduct(@PathVariable("id") Long id) throws EntityNotFoundException {
        return userService.deleteUserById(id);
    }


}
