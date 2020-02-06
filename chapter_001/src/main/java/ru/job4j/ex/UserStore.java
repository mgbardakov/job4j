package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User finded = null;
        for (User u : users) {
            if (u.getUsername().equals(login)) {
                finded = u;
            }
        }
        if (finded == null) {
            throw new UserNotFoundException();
        }
        return finded;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException();
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Lee", true)
        };
        try {
            User user = findUser(users, "Lee");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            System.out.println("Invalid user.");
        } catch (UserNotFoundException e) {
            System.out.println("There's no such user");
        }
    }
}
