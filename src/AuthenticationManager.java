import models.AppUser;

public class AuthenticationManager {
    private static AppUser[] appUsers = {
            new AppUser("ali", "1234", "علی تفکری خسروشاهی"),
            new AppUser("pirahesh", "3574159", "استاد"),
    };

    private static AuthenticationManager manager;

    public static AuthenticationManager getInstance() {
        if (manager == null) {
            manager = new AuthenticationManager();
        }
        return manager;
    }

    public AppUser authenticate(String username, String password) {
        for (AppUser user : appUsers) {
            if (user.username.equals(username) && user.password.equals(password)){
                return user;
            }
        }
        return null;
    }
}
