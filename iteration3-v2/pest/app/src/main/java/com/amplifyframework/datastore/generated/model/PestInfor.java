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

/** This is an auto generated class representing the PestInfor type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "PestInfors")
public final class PestInfor implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField LATITUD = field("latitud");
  public static final QueryField LONGTITUD = field("longtitud");
  public static final QueryField YEAR = field("year");
  public static final QueryField NUM = field("num");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String latitud;
  private final @ModelField(targetType="String") String longtitud;
  private final @ModelField(targetType="String") String year;
  private final @ModelField(targetType="Int") Integer num;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getLatitud() {
      return latitud;
  }
  
  public String getLongtitud() {
      return longtitud;
  }
  
  public String getYear() {
      return year;
  }
  
  public Integer getNum() {
      return num;
  }
  
  private PestInfor(String id, String name, String latitud, String longtitud, String year, Integer num) {
    this.id = id;
    this.name = name;
    this.latitud = latitud;
    this.longtitud = longtitud;
    this.year = year;
    this.num = num;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      PestInfor pestInfor = (PestInfor) obj;
      return ObjectsCompat.equals(getId(), pestInfor.getId()) &&
              ObjectsCompat.equals(getName(), pestInfor.getName()) &&
              ObjectsCompat.equals(getLatitud(), pestInfor.getLatitud()) &&
              ObjectsCompat.equals(getLongtitud(), pestInfor.getLongtitud()) &&
              ObjectsCompat.equals(getYear(), pestInfor.getYear()) &&
              ObjectsCompat.equals(getNum(), pestInfor.getNum());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getLatitud())
      .append(getLongtitud())
      .append(getYear())
      .append(getNum())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("PestInfor {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("latitud=" + String.valueOf(getLatitud()) + ", ")
      .append("longtitud=" + String.valueOf(getLongtitud()) + ", ")
      .append("year=" + String.valueOf(getYear()) + ", ")
      .append("num=" + String.valueOf(getNum()))
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
  public static PestInfor justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new PestInfor(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      latitud,
      longtitud,
      year,
      num);
  }
  public interface BuildStep {
    PestInfor build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep name(String name);
    BuildStep latitud(String latitud);
    BuildStep longtitud(String longtitud);
    BuildStep year(String year);
    BuildStep num(Integer num);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String name;
    private String latitud;
    private String longtitud;
    private String year;
    private Integer num;
    @Override
     public PestInfor build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new PestInfor(
          id,
          name,
          latitud,
          longtitud,
          year,
          num);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep latitud(String latitud) {
        this.latitud = latitud;
        return this;
    }
    
    @Override
     public BuildStep longtitud(String longtitud) {
        this.longtitud = longtitud;
        return this;
    }
    
    @Override
     public BuildStep year(String year) {
        this.year = year;
        return this;
    }
    
    @Override
     public BuildStep num(Integer num) {
        this.num = num;
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
    private CopyOfBuilder(String id, String name, String latitud, String longtitud, String year, Integer num) {
      super.id(id);
      super.name(name)
        .latitud(latitud)
        .longtitud(longtitud)
        .year(year)
        .num(num);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder latitud(String latitud) {
      return (CopyOfBuilder) super.latitud(latitud);
    }
    
    @Override
     public CopyOfBuilder longtitud(String longtitud) {
      return (CopyOfBuilder) super.longtitud(longtitud);
    }
    
    @Override
     public CopyOfBuilder year(String year) {
      return (CopyOfBuilder) super.year(year);
    }
    
    @Override
     public CopyOfBuilder num(Integer num) {
      return (CopyOfBuilder) super.num(num);
    }
  }
  
}
