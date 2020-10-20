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

/** This is an auto generated class representing the Report type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Reports")
public final class Report implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField PEST = field("pest");
  public static final QueryField DATE = field("date");
  public static final QueryField NUM = field("num");
  public static final QueryField DESCRIPTION = field("description");
  public static final QueryField LOCATION = field("location");
  public static final QueryField IMAGE_PATH = field("imagePath");
  public static final QueryField USER_NAME = field("userName");
  public static final QueryField USEEMAIL = field("useemail");
  public static final QueryField USER_SCORE = field("userScore");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String pest;
  private final @ModelField(targetType="String") String date;
  private final @ModelField(targetType="Int") Integer num;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String") String location;
  private final @ModelField(targetType="String") String imagePath;
  private final @ModelField(targetType="String") String userName;
  private final @ModelField(targetType="String") String useemail;
  private final @ModelField(targetType="Int") Integer userScore;
  public String getId() {
      return id;
  }
  
  public String getPest() {
      return pest;
  }
  
  public String getDate() {
      return date;
  }
  
  public Integer getNum() {
      return num;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getLocation() {
      return location;
  }
  
  public String getImagePath() {
      return imagePath;
  }
  
  public String getUserName() {
      return userName;
  }
  
  public String getUseemail() {
      return useemail;
  }
  
  public Integer getUserScore() {
      return userScore;
  }
  
  private Report(String id, String pest, String date, Integer num, String description, String location, String imagePath, String userName, String useemail, Integer userScore) {
    this.id = id;
    this.pest = pest;
    this.date = date;
    this.num = num;
    this.description = description;
    this.location = location;
    this.imagePath = imagePath;
    this.userName = userName;
    this.useemail = useemail;
    this.userScore = userScore;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Report report = (Report) obj;
      return ObjectsCompat.equals(getId(), report.getId()) &&
              ObjectsCompat.equals(getPest(), report.getPest()) &&
              ObjectsCompat.equals(getDate(), report.getDate()) &&
              ObjectsCompat.equals(getNum(), report.getNum()) &&
              ObjectsCompat.equals(getDescription(), report.getDescription()) &&
              ObjectsCompat.equals(getLocation(), report.getLocation()) &&
              ObjectsCompat.equals(getImagePath(), report.getImagePath()) &&
              ObjectsCompat.equals(getUserName(), report.getUserName()) &&
              ObjectsCompat.equals(getUseemail(), report.getUseemail()) &&
              ObjectsCompat.equals(getUserScore(), report.getUserScore());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPest())
      .append(getDate())
      .append(getNum())
      .append(getDescription())
      .append(getLocation())
      .append(getImagePath())
      .append(getUserName())
      .append(getUseemail())
      .append(getUserScore())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Report {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("pest=" + String.valueOf(getPest()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("num=" + String.valueOf(getNum()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("imagePath=" + String.valueOf(getImagePath()) + ", ")
      .append("userName=" + String.valueOf(getUserName()) + ", ")
      .append("useemail=" + String.valueOf(getUseemail()) + ", ")
      .append("userScore=" + String.valueOf(getUserScore()))
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
  public static Report justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Report(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      pest,
      date,
      num,
      description,
      location,
      imagePath,
      userName,
      useemail,
      userScore);
  }
  public interface BuildStep {
    Report build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep pest(String pest);
    BuildStep date(String date);
    BuildStep num(Integer num);
    BuildStep description(String description);
    BuildStep location(String location);
    BuildStep imagePath(String imagePath);
    BuildStep userName(String userName);
    BuildStep useemail(String useemail);
    BuildStep userScore(Integer userScore);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String pest;
    private String date;
    private Integer num;
    private String description;
    private String location;
    private String imagePath;
    private String userName;
    private String useemail;
    private Integer userScore;
    @Override
     public Report build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Report(
          id,
          pest,
          date,
          num,
          description,
          location,
          imagePath,
          userName,
          useemail,
          userScore);
    }
    
    @Override
     public BuildStep pest(String pest) {
        this.pest = pest;
        return this;
    }
    
    @Override
     public BuildStep date(String date) {
        this.date = date;
        return this;
    }
    
    @Override
     public BuildStep num(Integer num) {
        this.num = num;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.location = location;
        return this;
    }
    
    @Override
     public BuildStep imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
    
    @Override
     public BuildStep userName(String userName) {
        this.userName = userName;
        return this;
    }
    
    @Override
     public BuildStep useemail(String useemail) {
        this.useemail = useemail;
        return this;
    }
    
    @Override
     public BuildStep userScore(Integer userScore) {
        this.userScore = userScore;
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
    private CopyOfBuilder(String id, String pest, String date, Integer num, String description, String location, String imagePath, String userName, String useemail, Integer userScore) {
      super.id(id);
      super.pest(pest)
        .date(date)
        .num(num)
        .description(description)
        .location(location)
        .imagePath(imagePath)
        .userName(userName)
        .useemail(useemail)
        .userScore(userScore);
    }
    
    @Override
     public CopyOfBuilder pest(String pest) {
      return (CopyOfBuilder) super.pest(pest);
    }
    
    @Override
     public CopyOfBuilder date(String date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder num(Integer num) {
      return (CopyOfBuilder) super.num(num);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder imagePath(String imagePath) {
      return (CopyOfBuilder) super.imagePath(imagePath);
    }
    
    @Override
     public CopyOfBuilder userName(String userName) {
      return (CopyOfBuilder) super.userName(userName);
    }
    
    @Override
     public CopyOfBuilder useemail(String useemail) {
      return (CopyOfBuilder) super.useemail(useemail);
    }
    
    @Override
     public CopyOfBuilder userScore(Integer userScore) {
      return (CopyOfBuilder) super.userScore(userScore);
    }
  }
  
}
