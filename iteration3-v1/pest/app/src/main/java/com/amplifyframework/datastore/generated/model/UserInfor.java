package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the UserInfor type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserInfors")
public final class UserInfor implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField UEMAIL = field("uemail");
  public static final QueryField UNAME = field("uname");
  public static final QueryField PASSWORD = field("password");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String uemail;
  private final @ModelField(targetType="String") String uname;
  private final @ModelField(targetType="String") String password;
  public String getId() {
      return id;
  }
  
  public String getUemail() {
      return uemail;
  }
  
  public String getUname() {
      return uname;
  }
  
  public String getPassword() {
      return password;
  }
  
  private UserInfor(String id, String uemail, String uname, String password) {
    this.id = id;
    this.uemail = uemail;
    this.uname = uname;
    this.password = password;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserInfor userInfor = (UserInfor) obj;
      return ObjectsCompat.equals(getId(), userInfor.getId()) &&
              ObjectsCompat.equals(getUemail(), userInfor.getUemail()) &&
              ObjectsCompat.equals(getUname(), userInfor.getUname()) &&
              ObjectsCompat.equals(getPassword(), userInfor.getPassword());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUemail())
      .append(getUname())
      .append(getPassword())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserInfor {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("uemail=" + String.valueOf(getUemail()) + ", ")
      .append("uname=" + String.valueOf(getUname()) + ", ")
      .append("password=" + String.valueOf(getPassword()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static UserInfor justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new UserInfor(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      uemail,
      uname,
      password);
  }
  public interface BuildStep {
    UserInfor build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep uemail(String uemail);
    BuildStep uname(String uname);
    BuildStep password(String password);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String uemail;
    private String uname;
    private String password;
    @Override
     public UserInfor build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserInfor(
          id,
          uemail,
          uname,
          password);
    }
    
    @Override
     public BuildStep uemail(String uemail) {
        this.uemail = uemail;
        return this;
    }
    
    @Override
     public BuildStep uname(String uname) {
        this.uname = uname;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        this.password = password;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String uemail, String uname, String password) {
      super.id(id);
      super.uemail(uemail)
        .uname(uname)
        .password(password);
    }
    
    @Override
     public CopyOfBuilder uemail(String uemail) {
      return (CopyOfBuilder) super.uemail(uemail);
    }
    
    @Override
     public CopyOfBuilder uname(String uname) {
      return (CopyOfBuilder) super.uname(uname);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
  }
  
}
