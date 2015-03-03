/*
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2002 - 2015 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */

package com.pentaho.metaverse.analyzer.kettle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pentaho.metaverse.api.model.IInfo;
import com.pentaho.metaverse.api.model.IOperation;
import com.pentaho.metaverse.api.model.Operation;
import com.pentaho.metaverse.api.model.Operations;
import flexjson.JSONSerializer;

import java.util.List;

/**
 * The ComponentDerivationRecord is a collection of information about a change to a component, including
 * named operation(s) performed to derive the component. For example, a component derivation record for a
 * transformation stream field may include a named operation such as "modified" or "calculated".
 */
@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = IInfo.JSON_PROPERTY_CLASS )
public class ComponentDerivationRecord {

  protected String changedEntityName;
  protected String originalEntityName;
  protected ChangeType changeType;

  protected Operations operations;

  public ComponentDerivationRecord() {
    changeType = ChangeType.METADATA;
    operations = new Operations();
  }

  public ComponentDerivationRecord( String originalEntityName, String changedEntityName ) {
    this( originalEntityName, changedEntityName, ChangeType.METADATA );
  }

  public ComponentDerivationRecord( String originalEntityName, String changedEntityName, ChangeType changeType ) {
    this();
    this.changedEntityName = changedEntityName;
    this.originalEntityName = originalEntityName;
    this.changeType = changeType;
  }

  public ComponentDerivationRecord( String changedEntityName, ChangeType changeType ) {
    this();
    this.changedEntityName = changedEntityName;
    this.originalEntityName = changedEntityName;
    this.changeType = changeType;
  }

  public ComponentDerivationRecord( String changedEntityName ) {
    this( changedEntityName, ChangeType.METADATA );
  }

  public ChangeType getChangeType() {
    return changeType;
  }

  public void setChangeType( ChangeType changeType ) {
    this.changeType = changeType;
  }

  public String getChangedEntityName() {
    return changedEntityName;
  }

  public void setChangedEntityName( String changedEntityName ) {
    this.changedEntityName = changedEntityName;
  }

  public String getOriginalEntityName() {
    return originalEntityName;
  }

  public void setOriginalEntityName( String originalEntityName ) {
    this.originalEntityName = originalEntityName;
  }

  /**
   * returns a named list (i.e. map) of operations and their operands
   *
   * @return a Map from the operation name to a list of operands
   */
  public List<IOperation> getOperations( ChangeType type ) {
    return getOperations().get( type );
  }

  /**
   * Returns the Operations associated with this record
   *
   * @return an Operations object containing this record's associated data/metadata operations
   */
  public Operations getOperations() {
    if ( operations == null ) {
      operations = new Operations();
    }
    return operations;
  }

  /**
   * Adds or sets the operand list for an operation with the specified name
   *
   * @param operation the operation to add
   */
  public void addOperation( Operation operation ) {
    if ( operation != null ) {
      getOperations().addOperation( operation.getType(), operation );
    }
  }

  /**
   * Determines whether this record represents a change in metadata or data, by inspecting how many operations have
   * been applied
   *
   * @return true if this record has changed the associated field, false otherwise
   */
  public boolean hasDelta() {
    return operations != null && !operations.isEmpty();
  }

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) {
      return true;
    }
    if ( o == null || getClass() != o.getClass() ) {
      return false;
    }

    ComponentDerivationRecord that = (ComponentDerivationRecord) o;

    if ( changeType != that.changeType ) {
      return false;
    }
    if ( changedEntityName != null ? !changedEntityName.equals( that.changedEntityName ) : that.changedEntityName != null ) {
      return false;
    }
    if ( operations != null ? !operations.equals( that.operations ) : that.operations != null ) {
      return false;
    }
    if ( originalEntityName != null ? !originalEntityName.equals( that.originalEntityName ) : that.originalEntityName != null ) {
      return false;
    }

    if ( hashCode() != that.hashCode() ) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = changedEntityName != null ? changedEntityName.hashCode() : 0;
    result = 31 * result + ( originalEntityName != null ? originalEntityName.hashCode() : 0 );
    result = 31 * result + changeType.hashCode();
    result = 31 * result + ( operations != null ? operations.hashCode() : 0 );
    return result;
  }

  @Override
  public String toString() {
    return new JSONSerializer().include( "*" ).serialize( operations );
  }
}
