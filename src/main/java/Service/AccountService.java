package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }



    public Account insertAccount(Account account){
        return accountDAO.insertAccount(account);
    }

    public Account getUserAccount(Account account){
        return accountDAO.getUserAccount(account);
    }
}
