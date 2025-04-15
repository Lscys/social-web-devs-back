package git.red.com.controllers.Auth;


import git.red.com.dto.Auth.LoginUserDto;
import git.red.com.dto.Auth.RegisterUserDto;
import git.red.com.dto.RefreshTokenRequest;
import git.red.com.models.User;
import git.red.com.response.JwtResponse;
import git.red.com.response.LoginResponse;
import git.red.com.services.Auth.AuthenticationService;
import git.red.com.services.JwtService;
import git.red.com.services.RefreshTokenService;
import git.red.com.services.implement.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(JwtService jwtService, UserDetailsServiceImpl userDetailsService, AuthenticationService authenticationService, RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Generar el access token
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Generar el refresh token
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        // Crear la respuesta con ambos tokens
        LoginResponse loginResponse = new LoginResponse()
                .setAccessToken(jwtToken)
                .setRefreshToken(refreshToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (refreshTokenService.isValid(refreshToken)) {
            String email = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            String newAccessToken = jwtService.generateRefreshToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(newAccessToken));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token");
        }
    }

}
