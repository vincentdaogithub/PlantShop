package dao.account;

import obj.account.Account;
import obj.account.AccountStatus;
import obj.account.Accounts;

public class AccountFactory {
    public static final Account build(String email, String password, String fullname, String phone, Accounts role) {
        return new Account(
                0, email, password, fullname, phone, AccountStatus.ACTIVE.getStatus(), role.getRole());
    }
}
