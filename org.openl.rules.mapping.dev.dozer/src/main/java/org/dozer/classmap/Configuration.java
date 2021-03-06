/*
 * Copyright 2005-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dozer.classmap;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dozer.converters.CustomConverterContainer;
import org.dozer.util.DozerConstants;

/**
 * Internal class that represents the configuration block specified in the
 * mapping xml file(s). Only intended for internal use.
 * 
 * @author garsombke.franz
 * @author sullins.ben
 * @author tierney.matt
 * 
 */
public class Configuration {

    private final CustomConverterContainer customConverters = new CustomConverterContainer();
    private final CopyByReferenceContainer copyByReferences = new CopyByReferenceContainer();
    private final AllowedExceptionContainer allowedExceptions = new AllowedExceptionContainer();
    private Boolean wildcard;
    private Boolean stopOnErrors;
    private Boolean trimStrings;
    private Boolean mapNulls;
    private Boolean mapEmptyStrings;
    private Boolean requiredFields;
    private String dateFormat;
    private String beanFactory;
    private RelationshipType relationshipType;

    public AllowedExceptionContainer getAllowedExceptions() {
        return allowedExceptions;
    }

    public CustomConverterContainer getCustomConverters() {
        return customConverters;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String format) {
        dateFormat = format;
    }

    public Boolean getWildcard() {
        return wildcard != null ? wildcard : Boolean.valueOf(DozerConstants.DEFAULT_WILDCARD_POLICY);
    }

    public void setWildcard(Boolean globalWildcardPolicy) {
        wildcard = globalWildcardPolicy;
    }

    public Boolean getStopOnErrors() {
        return stopOnErrors != null ? stopOnErrors : Boolean.valueOf(DozerConstants.DEFAULT_ERROR_POLICY);
    }

    public void setStopOnErrors(Boolean stopOnErrors) {
        this.stopOnErrors = stopOnErrors;
    }

    public String getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(String beanFactory) {
        this.beanFactory = beanFactory;
    }

    public CopyByReferenceContainer getCopyByReferences() {
        return copyByReferences;
    }

    public Boolean getTrimStrings() {
        return trimStrings != null ? trimStrings : Boolean.valueOf(DozerConstants.DEFAULT_TRIM_STRINGS_POLICY);
    }

    public void setTrimStrings(Boolean trimStrings) {
        this.trimStrings = trimStrings;
    }

    public Boolean getMapNulls() {
        return mapNulls != null ? mapNulls : Boolean.valueOf(DozerConstants.DEFAULT_MAP_NULL_POLICY);
    }

    public void setMapNulls(Boolean mapNulls) {
        this.mapNulls = mapNulls;
    }

    public Boolean getMapEmptyStrings() {
        return mapEmptyStrings != null ? mapEmptyStrings
                                       : Boolean.valueOf(DozerConstants.DEFAULT_MAP_EMPTY_STRING_POLICY);
    }

    public void setMapEmptyStrings(Boolean mapEmptyStrings) {
        this.mapEmptyStrings = mapEmptyStrings;
    }

    public Boolean getRequiredFields() {
        return requiredFields != null ? requiredFields : Boolean.valueOf(DozerConstants.DEFAULT_REQUIRED_FIELDS_POLICY);
    }

    public void setRequiredFields(Boolean requiredFields) {
        this.requiredFields = requiredFields;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}