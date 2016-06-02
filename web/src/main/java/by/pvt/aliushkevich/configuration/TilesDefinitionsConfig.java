package by.pvt.aliushkevich.configuration;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Apache tiles configuration class. Implements DefinitionsFactory to provide programmatic configuration for Apache tiles.
 */
public final class TilesDefinitionsConfig implements DefinitionsFactory {

  private static final Map<String, Definition> tilesDefinitions = new HashMap<String,Definition>();
  private static final Attribute BASE_TEMPLATE = new Attribute("/WEB-INF/views/layout/defaultLayout.jsp");

  @Override
  public Definition getDefinition(String name, Request tilesContext) {
    return tilesDefinitions.get(name);
  }

  /**
   * @param name Name of the view
   * @param title Page title
   * @param body Body JSP file path
   *
   * Adds default layout definitions
   */
  private static void addDefaultLayoutDef(String name, String title, String body) {
    Map<String, Attribute> attributes = new HashMap<>();
    attributes.put("title", new Attribute(title));
    attributes.put("header", new Attribute("/WEB-INF/views/layout/header.jsp"));
    //attributes.put("menu", new Attribute("/WEB-INF/views/layout/menu.jsp"));
    attributes.put("body", new Attribute(body));
    attributes.put("footer", new Attribute("/WEB-INF/views/layout/footer.jsp"));

    tilesDefinitions.put(name, new Definition(name, BASE_TEMPLATE, attributes));
  }

  /**
   * Adds Apache tiles definitions
   */
  public static void addDefinitions(){
    addDefaultLayoutDef("fail", "Fail", "/WEB-INF/views/fail.jsp");
    addDefaultLayoutDef("lecturer", "Lecturer", "/WEB-INF/views/lecturer.jsp");
    addDefaultLayoutDef("login", "Login", "/WEB-INF/views/login.jsp");
    addDefaultLayoutDef("register", "Register", "/WEB-INF/views/register.jsp");
    addDefaultLayoutDef("student", "Student", "/WEB-INF/views/student.jsp");
    addDefaultLayoutDef("success", "Success", "/WEB-INF/views/success.jsp");
  }
}
