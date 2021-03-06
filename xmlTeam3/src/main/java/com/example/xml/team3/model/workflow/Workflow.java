//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.04 at 01:47:58 PM CET 
//


package com.example.xml.team3.model.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authorUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewerUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="editorUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scientificWorkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "authorUsername",
    "reviewerUsername",
    "editorUsername",
    "scientificWorkId"
})
@XmlRootElement(name = "workflow")
public class Workflow {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String authorUsername;
    @XmlElement(required = true)
    protected String reviewerUsername;
    @XmlElement(required = true)
    protected String editorUsername;
    @XmlElement(required = true)
    protected String scientificWorkId;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the authorUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorUsername() {
        return authorUsername;
    }

    /**
     * Sets the value of the authorUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorUsername(String value) {
        this.authorUsername = value;
    }

    /**
     * Gets the value of the reviewerUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerUsername() {
        return reviewerUsername;
    }

    /**
     * Sets the value of the reviewerUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerUsername(String value) {
        this.reviewerUsername = value;
    }

    /**
     * Gets the value of the editorUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditorUsername() {
        return editorUsername;
    }

    /**
     * Sets the value of the editorUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditorUsername(String value) {
        this.editorUsername = value;
    }

    /**
     * Gets the value of the scientificWorkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScientificWorkId() {
        return scientificWorkId;
    }

    /**
     * Sets the value of the scientificWorkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScientificWorkId(String value) {
        this.scientificWorkId = value;
    }

}
