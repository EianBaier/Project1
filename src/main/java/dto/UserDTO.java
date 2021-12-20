package dto;

public class UserDTO {
    String firstName;
    String lastName;
    String username;
    int roleId;
    int userId;

    public UserDTO(String firstName, String lastName, String username, int roleId, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleId = roleId;
        this.userId = userId;
    }



    /* public UserDTO(String firstName, String lastName, String username, int roleId, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleId = roleId;
        this.userId =userId;
    }*/

    public UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}