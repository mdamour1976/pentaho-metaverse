/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2015 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.metaverse.api.model.kettle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.pentaho.metaverse.api.model.IInfo;

/**
 * User: RFellows Date: 12/11/14
 */
@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = IInfo.JSON_PROPERTY_CLASS )
public interface IFieldMapping {
  public static final String JSON_PROPERTY_SOURCE_FIELD_NAME = "sourceFieldName";
  public static final String JSON_PROPERTY_TARGET_FIELD_NAME = "targetFieldName";

  @JsonProperty( JSON_PROPERTY_SOURCE_FIELD_NAME )
  public String getSourceFieldName();
  public void setSourceFieldName( String sourceFieldName );

  @JsonProperty( JSON_PROPERTY_TARGET_FIELD_NAME )
  public String getTargetFieldName();
  public void setTargetFieldName( String targetFieldName );

}
