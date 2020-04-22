package com.tlemceni.restController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlemceni.entities.dto.UserDto;
import com.tlemceni.entities.mapping.UserMapper;
import com.tlemceni.security.jwt.JwtResponse;
import com.tlemceni.security.jwt.JwtTokenProvider;
import com.tlemceni.security.jwt.UserForm;
import com.tlemceni.security.model.User;
import com.tlemceni.service.interf.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String ENTITY_NAME = "user";

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserController(UserService userService, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }
    
    /**
     * POST  /users : Créer un nouveau user.
     *
     * @param user user à  créer
     * @return ResponseEntity avec status 201 (Created) et nouveau user dans le body, ou status 400 (Bad Request) si user posséde deja un ID
     * @throws URISyntaxException exception si URI est incorrecte
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = userService.saveUser(userDto);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }

    /**
     * PUT  /users : mettre à jour
     *
     * @param user user a mettre à jour
     * @return ResponseEntity avec status 200 (OK)  user à jour dans le body,
     * ou status 400 (Bad Request) si user non valide
     * ou avec status 500 (Internal Server Error) si user ne peut pas etre mis à jour
     */
    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {

        UserDto result = userService.saveUser(userDto);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }

    /**
     * GET  /users : recuperer tous(toutes) les users.
     *
     * @param pageable informations de pagination
     * @return ResponseEntity avec status 200 (OK) et la liste des users dans le body
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("REST request to get a page of Users");
        Page<UserDto> page = userService.findAll(pageable);
        return new ResponseEntity<List<UserDto>>(page.getContent(), HttpStatus.OK);
    }

    /**
     * GET  /users/:id : récupérer le/la user par "id".
     *
     * @param id l'id du user a recuperer
     * @return ResponseEntity avec status 200 (OK) et user dans le body, oubien avec status 404 (Not Found)
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<UserDto> user = userService.findOne(id);
        if(user.isPresent()) {
            return new ResponseEntity<UserDto>(user.get(),HttpStatus.OK);
        } else
            return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.OK);
    }
    
    @GetMapping("/usersByUsername/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        log.debug("REST request to get User : {}", username);
        UserDto user = userService.findByUsername(username);
            return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PostMapping("/users/signin")
    public ResponseEntity<?> login(@RequestBody UserForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable("id") Long id){
            userService.delete(id);

    }
}
