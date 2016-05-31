package by.pvt.aliushkevich.logic;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides check client login logic
 */
public interface ILoginLogic {

  boolean checkLecturerLogin(String enterLogin, String enterPass);

  boolean checkStudentLogin(String enterLogin, String enterPass);

}
