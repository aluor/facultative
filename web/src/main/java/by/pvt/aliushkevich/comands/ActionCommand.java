package by.pvt.aliushkevich.comands;
import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
String execute(HttpServletRequest request);
}