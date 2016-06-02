/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package example.proto;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ProductionCompany extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2784695803525243275L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ProductionCompany\",\"namespace\":\"example.proto\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public int id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public ProductionCompany() {}

  /**
   * All-args constructor.
   */
  public ProductionCompany(java.lang.CharSequence name, java.lang.Integer id) {
    this.name = name;
    this.id = id;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return id;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: id = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
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
   * Gets the value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Creates a new ProductionCompany RecordBuilder.
   * @return A new ProductionCompany RecordBuilder
   */
  public static example.proto.ProductionCompany.Builder newBuilder() {
    return new example.proto.ProductionCompany.Builder();
  }
  
  /**
   * Creates a new ProductionCompany RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ProductionCompany RecordBuilder
   */
  public static example.proto.ProductionCompany.Builder newBuilder(example.proto.ProductionCompany.Builder other) {
    return new example.proto.ProductionCompany.Builder(other);
  }
  
  /**
   * Creates a new ProductionCompany RecordBuilder by copying an existing ProductionCompany instance.
   * @param other The existing instance to copy.
   * @return A new ProductionCompany RecordBuilder
   */
  public static example.proto.ProductionCompany.Builder newBuilder(example.proto.ProductionCompany other) {
    return new example.proto.ProductionCompany.Builder(other);
  }
  
  /**
   * RecordBuilder for ProductionCompany instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ProductionCompany>
    implements org.apache.avro.data.RecordBuilder<ProductionCompany> {

    private java.lang.CharSequence name;
    private int id;

    /** Creates a new Builder */
    private Builder() {
      super(example.proto.ProductionCompany.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.proto.ProductionCompany.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing ProductionCompany instance
     * @param other The existing instance to copy.
     */
    private Builder(example.proto.ProductionCompany other) {
            super(example.proto.ProductionCompany.SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
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
    public example.proto.ProductionCompany.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public example.proto.ProductionCompany.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public example.proto.ProductionCompany.Builder setId(int value) {
      validate(fields()[1], value);
      this.id = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public example.proto.ProductionCompany.Builder clearId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public ProductionCompany build() {
      try {
        ProductionCompany record = new ProductionCompany();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.id = fieldSetFlags()[1] ? this.id : (java.lang.Integer) defaultValue(fields()[1]);
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
