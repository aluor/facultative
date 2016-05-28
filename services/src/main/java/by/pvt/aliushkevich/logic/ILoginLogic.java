package by.pvt.aliushkevich.logic;

/**
 * Created by Rabotnik on 28.05.2016.
 */
public interface ILoginLogic {

  boolean checkLecturerLogin(String enterLogin, String enterPass);

  boolean checkStudentLogin(String enterLogin, String enterPass);

}
