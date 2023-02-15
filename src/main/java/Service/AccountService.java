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



    public Account postAccount(Account account){
        return accountDAO.postAccount(account);
    }

    public Account getUserAccount(Account account){
        return accountDAO.getUserAccount(account);
    }

   public Account getAccountByUsername(String username){
        return accountDAO.getAccountByUsername(username);
   }

   public boolean isUsernameDuplicate(String username){
        return accountDAO.isUsernameDuplicate(username);
   }
}
