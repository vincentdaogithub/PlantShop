package dao.account;

import obj.account.Account;
import obj.account.AccountStatuses;
import obj.account.Accounts;

public class AccountFactory {
    public static final Account buildForDB(String email,
            String password,
            String fullname,
            String phone,
            Accounts role) {

        return new Account(
                0,
                email,
                password,
                fullname,
                phone,
                AccountStatuses.ACTIVE.getStatus(),
                role.getRole());
    }
}
