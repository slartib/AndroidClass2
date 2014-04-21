package edu.washington.android.homework251.model;

/**
 * Singleton model class for Homework251. Provides for saving the state of the username and password
 * @author rick
 *
 */
public class Homework251model
{
  private String username;
  private String password;
  private static Homework251model instance;
  
  /**
   *  Private constructor
   */
  private Homework251model()
  {
    username = password = "";
  }

  /**
   * Retrieves the username from the model
   * @return String
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Sets the username in the model
   * @param username 
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

  /**
   * Retrieves the password from the model
   * @return String
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the password in the model
   * @param password 
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Returns the singleton instance of Homework251model
   * @return Homework251model 
   */
  public static Homework251model getInstance()
  {
    if (instance == null)
    {
      instance = new Homework251model();
    }
    return instance;
  }
  
}
