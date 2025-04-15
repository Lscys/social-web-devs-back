package git.red.com.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final Map<String, String> refreshTokens = new HashMap<>();  // Aquí los guardamos temporalmente

    public String generateRefreshToken(String email) {
        String refreshToken = UUID.randomUUID().toString();  // O cualquier otro mecanismo de generación
        refreshTokens.put(refreshToken, email);
        return refreshToken;
    }

    public boolean isValid(String refreshToken) {
        return refreshTokens.containsKey(refreshToken);
    }

    public void revokeRefreshToken(String refreshToken) {
        refreshTokens.remove(refreshToken);
    }
}
