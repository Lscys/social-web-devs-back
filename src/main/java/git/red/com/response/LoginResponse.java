package git.red.com.response;

public class LoginResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public LoginResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public LoginResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    private long expiresIn;



    public long getExpiresIn() { return expiresIn; }

    public LoginResponse setExpiresIn(long expiresIn) { this.expiresIn = expiresIn; return this; }
}
