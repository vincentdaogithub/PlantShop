package obj;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int accountID;
    private String accountEmail;
    private String accountPassword;
    private String accountFullName;
    private String accountPhone;
    private int accountStatus;
    private int accountRole;

    public Account() {
        this.accountID = 0;
        this.accountEmail = "";
        this.accountPassword = "";
        this.accountFullName = "";
        this.accountPhone = "";
        this.accountStatus = 0;
        this.accountRole = 0;
    }

    public Account(int accountID, String accountEmail, String accountPassword, String accountFullName,
            String accountPhone, int accountStatus, int accountRole) {
        this.accountID = accountID;
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
        this.accountFullName = accountFullName;
        this.accountPhone = accountPhone;
        this.accountStatus = accountStatus;
        this.accountRole = accountRole;
    }

    public Account(String accountEmail, String accountPassword, String accountFullName, String accountPhone) {
        this.accountID = 0;
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
        this.accountFullName = accountFullName;
        this.accountPhone = accountPhone;
        this.accountStatus = 1;
        this.accountRole = 0;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the accountEmail
     */
    public String getAccountEmail() {
        return accountEmail;
    }

    /**
     * @param accountEmail the accountEmail to set
     */
    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    /**
     * @return the accountPassword
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * @param accountPassword the accountPassword to set
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    /**
     * @return the accountFullName
     */
    public String getAccountFullName() {
        return accountFullName;
    }

    /**
     * @param accountFullName the accountFullName to set
     */
    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    /**
     * @return the accountPhone
     */
    public String getAccountPhone() {
        return accountPhone;
    }

    /**
     * @param accountPhone the accountPhone to set
     */
    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    /**
     * @return the accountStatus
     */
    public int getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus the accountStatus to set
     */
    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * @return the accountRole
     */
    public int getAccountRole() {
        return accountRole;
    }

    /**
     * @param accountRole the accountRole to set
     */
    public void setAccountRole(int accountRole) {
        this.accountRole = accountRole;
    }
}
