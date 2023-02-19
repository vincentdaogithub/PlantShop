package obj.account;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private int status;
    private int role;

    public Account() {
        this.ID = 0;
        this.email = "";
        this.password = "";
        this.fullname = "";
        this.phone = "";
        this.status = 0;
        this.role = 0;
    }

    public Account(int ID, String email, String password, String fullname,
            String phone, int status, int role) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    /**
     * @return the accountID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the accountID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the accountEmail
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the accountEmail to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountPassword
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the accountPassword to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the accountFullName
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the accountFullName to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the accountPhone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the accountPhone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the accountStatus
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the accountStatus to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the accountRole
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the accountRole to set
     */
    public void setRole(int role) {
        this.role = role;
    }
}
