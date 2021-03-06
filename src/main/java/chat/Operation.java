/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package chat;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-10-22")
public class Operation implements org.apache.thrift.TBase<Operation, Operation._Fields>, java.io.Serializable, Cloneable, Comparable<Operation> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Operation");

  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField INT_PARAM1_FIELD_DESC = new org.apache.thrift.protocol.TField("intParam1", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField LONG_PARAM1_FIELD_DESC = new org.apache.thrift.protocol.TField("longParam1", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField LONG_PARAM2_FIELD_DESC = new org.apache.thrift.protocol.TField("longParam2", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField LONG_PARAM3_FIELD_DESC = new org.apache.thrift.protocol.TField("longParam3", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new OperationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new OperationTupleSchemeFactory());
  }

  /**
   * 
   * @see OperationType
   */
  public OperationType type; // required
  public int intParam1; // required
  public long longParam1; // required
  public long longParam2; // required
  public long longParam3; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see OperationType
     */
    TYPE((short)1, "type"),
    INT_PARAM1((short)2, "intParam1"),
    LONG_PARAM1((short)3, "longParam1"),
    LONG_PARAM2((short)4, "longParam2"),
    LONG_PARAM3((short)5, "longParam3");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TYPE
          return TYPE;
        case 2: // INT_PARAM1
          return INT_PARAM1;
        case 3: // LONG_PARAM1
          return LONG_PARAM1;
        case 4: // LONG_PARAM2
          return LONG_PARAM2;
        case 5: // LONG_PARAM3
          return LONG_PARAM3;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __INTPARAM1_ISSET_ID = 0;
  private static final int __LONGPARAM1_ISSET_ID = 1;
  private static final int __LONGPARAM2_ISSET_ID = 2;
  private static final int __LONGPARAM3_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OperationType.class)));
    tmpMap.put(_Fields.INT_PARAM1, new org.apache.thrift.meta_data.FieldMetaData("intParam1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LONG_PARAM1, new org.apache.thrift.meta_data.FieldMetaData("longParam1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LONG_PARAM2, new org.apache.thrift.meta_data.FieldMetaData("longParam2", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LONG_PARAM3, new org.apache.thrift.meta_data.FieldMetaData("longParam3", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Operation.class, metaDataMap);
  }

  public Operation() {
  }

  public Operation(
    OperationType type,
    int intParam1,
    long longParam1,
    long longParam2,
    long longParam3)
  {
    this();
    this.type = type;
    this.intParam1 = intParam1;
    setIntParam1IsSet(true);
    this.longParam1 = longParam1;
    setLongParam1IsSet(true);
    this.longParam2 = longParam2;
    setLongParam2IsSet(true);
    this.longParam3 = longParam3;
    setLongParam3IsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Operation(Operation other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetType()) {
      this.type = other.type;
    }
    this.intParam1 = other.intParam1;
    this.longParam1 = other.longParam1;
    this.longParam2 = other.longParam2;
    this.longParam3 = other.longParam3;
  }

  public Operation deepCopy() {
    return new Operation(this);
  }

  @Override
  public void clear() {
    this.type = null;
    setIntParam1IsSet(false);
    this.intParam1 = 0;
    setLongParam1IsSet(false);
    this.longParam1 = 0;
    setLongParam2IsSet(false);
    this.longParam2 = 0;
    setLongParam3IsSet(false);
    this.longParam3 = 0;
  }

  /**
   * 
   * @see OperationType
   */
  public OperationType getType() {
    return this.type;
  }

  /**
   * 
   * @see OperationType
   */
  public Operation setType(OperationType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public int getIntParam1() {
    return this.intParam1;
  }

  public Operation setIntParam1(int intParam1) {
    this.intParam1 = intParam1;
    setIntParam1IsSet(true);
    return this;
  }

  public void unsetIntParam1() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INTPARAM1_ISSET_ID);
  }

  /** Returns true if field intParam1 is set (has been assigned a value) and false otherwise */
  public boolean isSetIntParam1() {
    return EncodingUtils.testBit(__isset_bitfield, __INTPARAM1_ISSET_ID);
  }

  public void setIntParam1IsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INTPARAM1_ISSET_ID, value);
  }

  public long getLongParam1() {
    return this.longParam1;
  }

  public Operation setLongParam1(long longParam1) {
    this.longParam1 = longParam1;
    setLongParam1IsSet(true);
    return this;
  }

  public void unsetLongParam1() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LONGPARAM1_ISSET_ID);
  }

  /** Returns true if field longParam1 is set (has been assigned a value) and false otherwise */
  public boolean isSetLongParam1() {
    return EncodingUtils.testBit(__isset_bitfield, __LONGPARAM1_ISSET_ID);
  }

  public void setLongParam1IsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LONGPARAM1_ISSET_ID, value);
  }

  public long getLongParam2() {
    return this.longParam2;
  }

  public Operation setLongParam2(long longParam2) {
    this.longParam2 = longParam2;
    setLongParam2IsSet(true);
    return this;
  }

  public void unsetLongParam2() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LONGPARAM2_ISSET_ID);
  }

  /** Returns true if field longParam2 is set (has been assigned a value) and false otherwise */
  public boolean isSetLongParam2() {
    return EncodingUtils.testBit(__isset_bitfield, __LONGPARAM2_ISSET_ID);
  }

  public void setLongParam2IsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LONGPARAM2_ISSET_ID, value);
  }

  public long getLongParam3() {
    return this.longParam3;
  }

  public Operation setLongParam3(long longParam3) {
    this.longParam3 = longParam3;
    setLongParam3IsSet(true);
    return this;
  }

  public void unsetLongParam3() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LONGPARAM3_ISSET_ID);
  }

  /** Returns true if field longParam3 is set (has been assigned a value) and false otherwise */
  public boolean isSetLongParam3() {
    return EncodingUtils.testBit(__isset_bitfield, __LONGPARAM3_ISSET_ID);
  }

  public void setLongParam3IsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LONGPARAM3_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((OperationType)value);
      }
      break;

    case INT_PARAM1:
      if (value == null) {
        unsetIntParam1();
      } else {
        setIntParam1((Integer)value);
      }
      break;

    case LONG_PARAM1:
      if (value == null) {
        unsetLongParam1();
      } else {
        setLongParam1((Long)value);
      }
      break;

    case LONG_PARAM2:
      if (value == null) {
        unsetLongParam2();
      } else {
        setLongParam2((Long)value);
      }
      break;

    case LONG_PARAM3:
      if (value == null) {
        unsetLongParam3();
      } else {
        setLongParam3((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPE:
      return getType();

    case INT_PARAM1:
      return getIntParam1();

    case LONG_PARAM1:
      return getLongParam1();

    case LONG_PARAM2:
      return getLongParam2();

    case LONG_PARAM3:
      return getLongParam3();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPE:
      return isSetType();
    case INT_PARAM1:
      return isSetIntParam1();
    case LONG_PARAM1:
      return isSetLongParam1();
    case LONG_PARAM2:
      return isSetLongParam2();
    case LONG_PARAM3:
      return isSetLongParam3();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Operation)
      return this.equals((Operation)that);
    return false;
  }

  public boolean equals(Operation that) {
    if (that == null)
      return false;

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_intParam1 = true;
    boolean that_present_intParam1 = true;
    if (this_present_intParam1 || that_present_intParam1) {
      if (!(this_present_intParam1 && that_present_intParam1))
        return false;
      if (this.intParam1 != that.intParam1)
        return false;
    }

    boolean this_present_longParam1 = true;
    boolean that_present_longParam1 = true;
    if (this_present_longParam1 || that_present_longParam1) {
      if (!(this_present_longParam1 && that_present_longParam1))
        return false;
      if (this.longParam1 != that.longParam1)
        return false;
    }

    boolean this_present_longParam2 = true;
    boolean that_present_longParam2 = true;
    if (this_present_longParam2 || that_present_longParam2) {
      if (!(this_present_longParam2 && that_present_longParam2))
        return false;
      if (this.longParam2 != that.longParam2)
        return false;
    }

    boolean this_present_longParam3 = true;
    boolean that_present_longParam3 = true;
    if (this_present_longParam3 || that_present_longParam3) {
      if (!(this_present_longParam3 && that_present_longParam3))
        return false;
      if (this.longParam3 != that.longParam3)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_intParam1 = true;
    list.add(present_intParam1);
    if (present_intParam1)
      list.add(intParam1);

    boolean present_longParam1 = true;
    list.add(present_longParam1);
    if (present_longParam1)
      list.add(longParam1);

    boolean present_longParam2 = true;
    list.add(present_longParam2);
    if (present_longParam2)
      list.add(longParam2);

    boolean present_longParam3 = true;
    list.add(present_longParam3);
    if (present_longParam3)
      list.add(longParam3);

    return list.hashCode();
  }

  @Override
  public int compareTo(Operation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIntParam1()).compareTo(other.isSetIntParam1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIntParam1()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.intParam1, other.intParam1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLongParam1()).compareTo(other.isSetLongParam1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLongParam1()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.longParam1, other.longParam1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLongParam2()).compareTo(other.isSetLongParam2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLongParam2()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.longParam2, other.longParam2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLongParam3()).compareTo(other.isSetLongParam3());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLongParam3()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.longParam3, other.longParam3);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Operation(");
    boolean first = true;

    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("intParam1:");
    sb.append(this.intParam1);
    first = false;
    if (!first) sb.append(", ");
    sb.append("longParam1:");
    sb.append(this.longParam1);
    first = false;
    if (!first) sb.append(", ");
    sb.append("longParam2:");
    sb.append(this.longParam2);
    first = false;
    if (!first) sb.append(", ");
    sb.append("longParam3:");
    sb.append(this.longParam3);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class OperationStandardSchemeFactory implements SchemeFactory {
    public OperationStandardScheme getScheme() {
      return new OperationStandardScheme();
    }
  }

  private static class OperationStandardScheme extends StandardScheme<Operation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Operation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = chat.OperationType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INT_PARAM1
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.intParam1 = iprot.readI32();
              struct.setIntParam1IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LONG_PARAM1
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.longParam1 = iprot.readI64();
              struct.setLongParam1IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // LONG_PARAM2
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.longParam2 = iprot.readI64();
              struct.setLongParam2IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LONG_PARAM3
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.longParam3 = iprot.readI64();
              struct.setLongParam3IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Operation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(INT_PARAM1_FIELD_DESC);
      oprot.writeI32(struct.intParam1);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LONG_PARAM1_FIELD_DESC);
      oprot.writeI64(struct.longParam1);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LONG_PARAM2_FIELD_DESC);
      oprot.writeI64(struct.longParam2);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LONG_PARAM3_FIELD_DESC);
      oprot.writeI64(struct.longParam3);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class OperationTupleSchemeFactory implements SchemeFactory {
    public OperationTupleScheme getScheme() {
      return new OperationTupleScheme();
    }
  }

  private static class OperationTupleScheme extends TupleScheme<Operation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Operation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetType()) {
        optionals.set(0);
      }
      if (struct.isSetIntParam1()) {
        optionals.set(1);
      }
      if (struct.isSetLongParam1()) {
        optionals.set(2);
      }
      if (struct.isSetLongParam2()) {
        optionals.set(3);
      }
      if (struct.isSetLongParam3()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetType()) {
        oprot.writeI32(struct.type.getValue());
      }
      if (struct.isSetIntParam1()) {
        oprot.writeI32(struct.intParam1);
      }
      if (struct.isSetLongParam1()) {
        oprot.writeI64(struct.longParam1);
      }
      if (struct.isSetLongParam2()) {
        oprot.writeI64(struct.longParam2);
      }
      if (struct.isSetLongParam3()) {
        oprot.writeI64(struct.longParam3);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Operation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.type = chat.OperationType.findByValue(iprot.readI32());
        struct.setTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.intParam1 = iprot.readI32();
        struct.setIntParam1IsSet(true);
      }
      if (incoming.get(2)) {
        struct.longParam1 = iprot.readI64();
        struct.setLongParam1IsSet(true);
      }
      if (incoming.get(3)) {
        struct.longParam2 = iprot.readI64();
        struct.setLongParam2IsSet(true);
      }
      if (incoming.get(4)) {
        struct.longParam3 = iprot.readI64();
        struct.setLongParam3IsSet(true);
      }
    }
  }

}

