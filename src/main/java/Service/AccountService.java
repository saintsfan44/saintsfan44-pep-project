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

    public Account getUserAccount(String username, String password){
        return accountDAO.getUserAccount(username, password);
    }

   public Account getAccountByUsername(String username){
        return accountDAO.getAccountByUsername(username);
   }

   public boolean isUsernameDuplicate(String username){
        return accountDAO.isUsernameDuplicate(username);
   }

   public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
   }
}
