//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.31 at 02:54:11 PM CET 
//


package com.example.xml.team3.model.scientificwork;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="revised" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="accepted" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://ftn.uns.ac.rs/team3/scientificWork}StatusType"/>
 *         &lt;element name="authors">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element ref="{http://ftn.uns.ac.rs/team3/scientificWork}author"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="abstract">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="purpose" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="design" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="findings" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="limitations" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="originality" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="keywords">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="scientificWorkType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://ftn.uns.ac.rs/team3/scientificWork}paragraph" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="references">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reference">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="scientificWorkId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://ftn.uns.ac.rs/team3/scientificWork}comment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "title",
    "status",
    "authors",
    "_abstract",
    "paragraph",
    "references",
    "comment"
})
@XmlRootElement(name = "scientificWork")
public class ScientificWork {

    @XmlElement(required = true)
    protected ScientificWork.Header header;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected StatusType status;
    @XmlElement(required = true)
    protected ScientificWork.Authors authors;
    @XmlElement(name = "abstract", required = true)
    protected ScientificWork.Abstract _abstract;
    protected List<Paragraph> paragraph;
    @XmlElement(required = true)
    protected ScientificWork.References references;
    protected List<String> comment;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link ScientificWork.Header }
     *     
     */
    public ScientificWork.Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScientificWork.Header }
     *     
     */
    public void setHeader(ScientificWork.Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link ScientificWork.Authors }
     *     
     */
    public ScientificWork.Authors getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScientificWork.Authors }
     *     
     */
    public void setAuthors(ScientificWork.Authors value) {
        this.authors = value;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link ScientificWork.Abstract }
     *     
     */
    public ScientificWork.Abstract getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScientificWork.Abstract }
     *     
     */
    public void setAbstract(ScientificWork.Abstract value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the paragraph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paragraph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParagraph().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Paragraph }
     * 
     * 
     */
    public List<Paragraph> getParagraph() {
        if (paragraph == null) {
            paragraph = new ArrayList<Paragraph>();
        }
        return this.paragraph;
    }

    /**
     * Gets the value of the references property.
     * 
     * @return
     *     possible object is
     *     {@link ScientificWork.References }
     *     
     */
    public ScientificWork.References getReferences() {
        return references;
    }

    /**
     * Sets the value of the references property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScientificWork.References }
     *     
     */
    public void setReferences(ScientificWork.References value) {
        this.references = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComment() {
        if (comment == null) {
            comment = new ArrayList<String>();
        }
        return this.comment;
    }

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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="purpose" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="design" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="findings" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="limitations" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="originality" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="keywords">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="scientificWorkType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "purpose",
        "design",
        "findings",
        "limitations",
        "originality",
        "keywords",
        "scientificWorkType"
    })
    public static class Abstract {

        @XmlElement(required = true)
        protected String purpose;
        @XmlElement(required = true)
        protected String design;
        @XmlElement(required = true)
        protected String findings;
        @XmlElement(required = true)
        protected String limitations;
        @XmlElement(required = true)
        protected String originality;
        @XmlElement(required = true)
        protected ScientificWork.Abstract.Keywords keywords;
        @XmlElement(required = true)
        protected String scientificWorkType;

        /**
         * Gets the value of the purpose property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPurpose() {
            return purpose;
        }

        /**
         * Sets the value of the purpose property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPurpose(String value) {
            this.purpose = value;
        }

        /**
         * Gets the value of the design property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesign() {
            return design;
        }

        /**
         * Sets the value of the design property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesign(String value) {
            this.design = value;
        }

        /**
         * Gets the value of the findings property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindings() {
            return findings;
        }

        /**
         * Sets the value of the findings property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindings(String value) {
            this.findings = value;
        }

        /**
         * Gets the value of the limitations property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLimitations() {
            return limitations;
        }

        /**
         * Sets the value of the limitations property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLimitations(String value) {
            this.limitations = value;
        }

        /**
         * Gets the value of the originality property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOriginality() {
            return originality;
        }

        /**
         * Sets the value of the originality property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOriginality(String value) {
            this.originality = value;
        }

        /**
         * Gets the value of the keywords property.
         * 
         * @return
         *     possible object is
         *     {@link ScientificWork.Abstract.Keywords }
         *     
         */
        public ScientificWork.Abstract.Keywords getKeywords() {
            return keywords;
        }

        /**
         * Sets the value of the keywords property.
         * 
         * @param value
         *     allowed object is
         *     {@link ScientificWork.Abstract.Keywords }
         *     
         */
        public void setKeywords(ScientificWork.Abstract.Keywords value) {
            this.keywords = value;
        }

        /**
         * Gets the value of the scientificWorkType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getScientificWorkType() {
            return scientificWorkType;
        }

        /**
         * Sets the value of the scientificWorkType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setScientificWorkType(String value) {
            this.scientificWorkType = value;
        }


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
         *         &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
            "keyword"
        })
        public static class Keywords {

            @XmlElement(required = true)
            protected List<String> keyword;

            /**
             * Gets the value of the keyword property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the keyword property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getKeyword().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getKeyword() {
                if (keyword == null) {
                    keyword = new ArrayList<String>();
                }
                return this.keyword;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element ref="{http://ftn.uns.ac.rs/team3/scientificWork}author"/>
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
        "author"
    })
    public static class Authors {

        @XmlElement(required = true)
        protected List<Author> author;

        /**
         * Gets the value of the author property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the author property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Author }
         * 
         * 
         */
        public List<Author> getAuthor() {
            if (author == null) {
                author = new ArrayList<Author>();
            }
            return this.author;
        }

    }


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
     *         &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="revised" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="accepted" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
        "received",
        "revised",
        "accepted"
    })
    public static class Header {

        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar received;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar revised;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar accepted;

        /**
         * Gets the value of the received property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getReceived() {
            return received;
        }

        /**
         * Sets the value of the received property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setReceived(XMLGregorianCalendar value) {
            this.received = value;
        }

        /**
         * Gets the value of the revised property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getRevised() {
            return revised;
        }

        /**
         * Sets the value of the revised property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setRevised(XMLGregorianCalendar value) {
            this.revised = value;
        }

        /**
         * Gets the value of the accepted property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getAccepted() {
            return accepted;
        }

        /**
         * Sets the value of the accepted property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setAccepted(XMLGregorianCalendar value) {
            this.accepted = value;
        }

    }


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
     *         &lt;element name="reference">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="scientificWorkId" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "reference"
    })
    public static class References {

        @XmlElement(required = true)
        protected ScientificWork.References.Reference reference;

        /**
         * Gets the value of the reference property.
         * 
         * @return
         *     possible object is
         *     {@link ScientificWork.References.Reference }
         *     
         */
        public ScientificWork.References.Reference getReference() {
            return reference;
        }

        /**
         * Sets the value of the reference property.
         * 
         * @param value
         *     allowed object is
         *     {@link ScientificWork.References.Reference }
         *     
         */
        public void setReference(ScientificWork.References.Reference value) {
            this.reference = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="scientificWorkId" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Reference {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "id")
            protected String id;
            @XmlAttribute(name = "scientificWorkId")
            protected String scientificWorkId;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

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

    }

}