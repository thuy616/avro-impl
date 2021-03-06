/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package example.proto;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Collection extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6439006243723783897L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Collection\",\"namespace\":\"example.proto\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"poster_path\",\"type\":\"string\"},{\"name\":\"backdrop_path\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public long id;
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public java.lang.CharSequence poster_path;
  @Deprecated public java.lang.CharSequence backdrop_path;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Collection() {}

  /**
   * All-args constructor.
   */
  public Collection(java.lang.Long id, java.lang.CharSequence name, java.lang.CharSequence poster_path, java.lang.CharSequence backdrop_path) {
    this.id = id;
    this.name = name;
    this.poster_path = poster_path;
    this.backdrop_path = backdrop_path;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return poster_path;
    case 3: return backdrop_path;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: name = (java.lang.CharSequence)value$; break;
    case 2: poster_path = (java.lang.CharSequence)value$; break;
    case 3: backdrop_path = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'poster_path' field.
   */
  public java.lang.CharSequence getPosterPath() {
    return poster_path;
  }

  /**
   * Sets the value of the 'poster_path' field.
   * @param value the value to set.
   */
  public void setPosterPath(java.lang.CharSequence value) {
    this.poster_path = value;
  }

  /**
   * Gets the value of the 'backdrop_path' field.
   */
  public java.lang.CharSequence getBackdropPath() {
    return backdrop_path;
  }

  /**
   * Sets the value of the 'backdrop_path' field.
   * @param value the value to set.
   */
  public void setBackdropPath(java.lang.CharSequence value) {
    this.backdrop_path = value;
  }

  /**
   * Creates a new Collection RecordBuilder.
   * @return A new Collection RecordBuilder
   */
  public static example.proto.Collection.Builder newBuilder() {
    return new example.proto.Collection.Builder();
  }
  
  /**
   * Creates a new Collection RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Collection RecordBuilder
   */
  public static example.proto.Collection.Builder newBuilder(example.proto.Collection.Builder other) {
    return new example.proto.Collection.Builder(other);
  }
  
  /**
   * Creates a new Collection RecordBuilder by copying an existing Collection instance.
   * @param other The existing instance to copy.
   * @return A new Collection RecordBuilder
   */
  public static example.proto.Collection.Builder newBuilder(example.proto.Collection other) {
    return new example.proto.Collection.Builder(other);
  }
  
  /**
   * RecordBuilder for Collection instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Collection>
    implements org.apache.avro.data.RecordBuilder<Collection> {

    private long id;
    private java.lang.CharSequence name;
    private java.lang.CharSequence poster_path;
    private java.lang.CharSequence backdrop_path;

    /** Creates a new Builder */
    private Builder() {
      super(example.proto.Collection.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.proto.Collection.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.poster_path)) {
        this.poster_path = data().deepCopy(fields()[2].schema(), other.poster_path);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.backdrop_path)) {
        this.backdrop_path = data().deepCopy(fields()[3].schema(), other.backdrop_path);
        fieldSetFlags()[3] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing Collection instance
     * @param other The existing instance to copy.
     */
    private Builder(example.proto.Collection other) {
            super(example.proto.Collection.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.poster_path)) {
        this.poster_path = data().deepCopy(fields()[2].schema(), other.poster_path);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.backdrop_path)) {
        this.backdrop_path = data().deepCopy(fields()[3].schema(), other.backdrop_path);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public example.proto.Collection.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public example.proto.Collection.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public example.proto.Collection.Builder setName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public example.proto.Collection.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'poster_path' field.
      * @return The value.
      */
    public java.lang.CharSequence getPosterPath() {
      return poster_path;
    }

    /**
      * Sets the value of the 'poster_path' field.
      * @param value The value of 'poster_path'.
      * @return This builder.
      */
    public example.proto.Collection.Builder setPosterPath(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.poster_path = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'poster_path' field has been set.
      * @return True if the 'poster_path' field has been set, false otherwise.
      */
    public boolean hasPosterPath() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'poster_path' field.
      * @return This builder.
      */
    public example.proto.Collection.Builder clearPosterPath() {
      poster_path = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'backdrop_path' field.
      * @return The value.
      */
    public java.lang.CharSequence getBackdropPath() {
      return backdrop_path;
    }

    /**
      * Sets the value of the 'backdrop_path' field.
      * @param value The value of 'backdrop_path'.
      * @return This builder.
      */
    public example.proto.Collection.Builder setBackdropPath(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.backdrop_path = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'backdrop_path' field has been set.
      * @return True if the 'backdrop_path' field has been set, false otherwise.
      */
    public boolean hasBackdropPath() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'backdrop_path' field.
      * @return This builder.
      */
    public example.proto.Collection.Builder clearBackdropPath() {
      backdrop_path = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Collection build() {
      try {
        Collection record = new Collection();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.poster_path = fieldSetFlags()[2] ? this.poster_path : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.backdrop_path = fieldSetFlags()[3] ? this.backdrop_path : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);  

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);  

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
