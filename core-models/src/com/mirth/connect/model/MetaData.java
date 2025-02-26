/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public abstract class MetaData {
    @XStreamAsAttribute
    private String path;

    private String name;
    private String author;
    private String mirthVersion;
    private String pluginVersion;
    private String pluginBuildNumber;
    private Boolean coreExtension;
    private Map<String, String> minCoreVersions;
    private String url;
    private String description;
    @XStreamAlias("apiProviders")
    @XStreamImplicit(itemFieldName = "apiProvider")
    private List<ApiProvider> apiProviders;
    @XStreamAlias("libraries")
    @XStreamImplicit(itemFieldName = "library")
    private List<ExtensionLibrary> libraries;
    private String templateClassName;
    private List<String> userutilPackages;
    private Boolean notify;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMirthVersion() {
        return mirthVersion;
    }

    public void setMirthVersion(String mirthVersion) {
        this.mirthVersion = mirthVersion;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public String getPluginBuildNumber() {
        return pluginBuildNumber;
    }

    public void setPluginBuildNumber(String pluginBuildNumber) {
        this.pluginBuildNumber = pluginBuildNumber;
    }

    public Boolean isCoreExtension() {
        return coreExtension;
    }

    public void setCoreExtension(Boolean coreExtension) {
        this.coreExtension = coreExtension;
    }

    public Map<String, String> getMinCoreVersions() {
        return minCoreVersions;
    }

    public void setMinCoreVersions(Map<String, String> minCoreVersions) {
        this.minCoreVersions = minCoreVersions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ApiProvider> getApiProviders() {
        return apiProviders;
    }

    public void setApiProviders(List<ApiProvider> apiProviders) {
        this.apiProviders = apiProviders;
    }

    public List<ExtensionLibrary> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<ExtensionLibrary> libraries) {
        this.libraries = libraries;
    }

    public String getTemplateClassName() {
        return templateClassName;
    }

    public void setTemplateClassName(String templateClassName) {
        this.templateClassName = templateClassName;
    }

    public List<String> getUserutilPackages() {
        return userutilPackages;
    }

    public void setUserutilPackages(List<String> userutilPackages) {
        this.userutilPackages = userutilPackages;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CalendarToStringStyle.instance());
    }
}
