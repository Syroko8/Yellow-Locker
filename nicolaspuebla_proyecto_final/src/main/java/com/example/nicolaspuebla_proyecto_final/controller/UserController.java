package com.example.nicolaspuebla_proyecto_final.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.model.dto.LoginRequest;
import com.example.nicolaspuebla_proyecto_final.model.dto.LoginResponse;
import com.example.nicolaspuebla_proyecto_final.model.dto.UserSignUp;
import com.example.nicolaspuebla_proyecto_final.service.TokenService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.NoResultException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador que maneja las peticiones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;
    
    /**
     * Genera un token JWT para el usuario especificado.
     * @param username El nombre de usuario para el cual se generará el token.
     * @return El token JWT generado.
     */
    private String getJWTToken(String username) {
		SecretKey secretKey = Jwts.SIG.HS256.key().build();
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.subject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.issuedAt(new Date(System.currentTimeMillis()))
				.signWith(secretKey, Jwts.SIG.HS256)
                .compact();

		return "Bearer " + token;
	}

    /**
     * Genera un token JWT para el usuario móvil y lo guarda en la base de datos.
     * @param user El usuario móvil para el cual se generará el token.
     * @return El token generado y los datos del usuario.
     */
    private ResponseEntity<LoginResponse> logUser(MobileUser user){
        try {
            String newToken = getJWTToken(user.getName());
            Token token = new Token(newToken, user.getId());
            tokenService.saveToken(token);
            return ResponseEntity.ok().body(new LoginResponse(user, token.getToken()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que maneja las peticiones de inicio de sesión.
     * @param loginRequest Objeto que contiene los datos de inicio de sesión del usuario.
     * @return El token de acceso e información del usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> logIn(@RequestBody LoginRequest loginRequest) {
        try {
            loginRequest.setEmail(loginRequest.getEmail().trim());
            loginRequest.setPasswd(loginRequest.getPasswd().trim());

            MobileUser user = (MobileUser) userService.getUserByEmail(loginRequest.getEmail());
            Boolean auth = (user.getPassword().equals(loginRequest.getPasswd())) && (!user.isDisabled()) ? true : false;
            ResponseEntity<LoginResponse> response = auth ? 
                logUser(user) : 
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            return response;
        } catch (NoResultException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que maneja las peticiones de registro.
     * @param newUser Objeto que contiene los datos del nuevo usuario a registrar.
     * @return El usuario creado.
     */
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserSignUp newUser) {
        try {
            User user = parseSignUpUser(newUser); 
            userService.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que parsea un objeto UserSignUp a un objeto User.
     * @param user Objeto UserSignUp a parsear.
     * @return Objeto User parseado.
     */
    private User parseSignUpUser(UserSignUp user){
        User parsed = new User(
            user.getName(), 
            user.getSurname(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getDisabled()
        );
        return parsed;
    }

    /**
     * Método que maneja las peticiones de registro para usuarios móviles.
     * @param newUser Objeto que contiene los datos del nuevo usuario móvil a registrar.
     * @return El usuario creado.
     */
    @PostMapping("/signup_mobile")
    public ResponseEntity<User> signUpMobileUser(@RequestBody UserSignUp newUser) {
        try {
            MobileUser user = parseSignUpUserToMobile(newUser);
            userService.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que parsea un objeto UserSignUp a un objeto MobileUser.
     * @param user Objeto UserSignUp a parsear.
     * @return Objeto MobileUser parseado.
     * @throws Exception
     */
    private MobileUser parseSignUpUserToMobile(UserSignUp user) throws Exception {
        try {
            MobileUser parsed = new MobileUser(
                user.getName(), 
                user.getSurname(), 
                user.getEmail(), 
                user.getPassword(), 
                false,
                strToDate(user.getBirthDate())
            );
            return parsed;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Método que parsea una cadena de texto a un objeto Date.
     * @param date La cadena de texto que representa la fecha.
     * @return El objeto Date correspondiente a la fecha.
     */
    private java.sql.Date strToDate(String date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date utilDate = sdf.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
            return sqlDate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Método que maneja las peticiones para deshabilitar un usuario.
     * @param userId El identificador del usuario a deshabilitar.
     * @return El usuario deshabilitado.
     */
    @PutMapping("/disable/{id}")
    public ResponseEntity<User> disableUser(@PathVariable Long userId) {
        try{
            User user = userService.getUser(userId);
            user.setDisabled(true);
            userService.updateUser(user);
    
            return ResponseEntity.ok().body(null);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } 
    
    /**
     * Método que maneja las peticiones para obtener los usuarios de un equipo.
     * @param userIdList Lista de identificadores de usuarios.
     * @return Lista de usuarios del equipo.
     */
    @GetMapping("/team_users")
    public ResponseEntity<List<User>> getTeamUsers(@RequestBody List<Long> userIdList) {
        try {
            ArrayList<User> users = new ArrayList<>();

            for(Long user_id : userIdList){
                users.add(userService.getUser(user_id));
            }
            return ResponseEntity.ok().body(users);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que maneja las peticiones para obtener un usuario por su ID.
     * @param userId El identificador del usuario a obtener.
     * @return El usuario correspondiente al ID proporcionado.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        
        try {
            User user = userService.getUser(userId);

            return ResponseEntity.ok().body(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }    
}
