//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.04 at 01:47:01 PM CET 
//


package com.example.xml.team3.model.scientificwork;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.xml.team3.model.scientificwork package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Comment_QNAME = new QName("http://ftn.uns.ac.rs/team3/scientificWork", "comment");
    private final static QName _Author_QNAME = new QName("http://ftn.uns.ac.rs/team3/scientificWork", "author");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.xml.team3.model.scientificwork
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ScientificWork }
     * 
     */
    public ScientificWork createScientificWork() {
        return new ScientificWork();
    }

    /**
     * Create an instance of {@link ScientificWork.Abstract }
     * 
     */
    public ScientificWork.Abstract createScientificWorkAbstract() {
        return new ScientificWork.Abstract();
    }

    /**
     * Create an instance of {@link Author }
     * 
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link Paragraph }
     * 
     */
    public Paragraph createParagraph() {
        return new Paragraph();
    }

    /**
     * Create an instance of {@link ScientificWork.Header }
     * 
     */
    public ScientificWork.Header createScientificWorkHeader() {
        return new ScientificWork.Header();
    }

    /**
     * Create an instance of {@link ScientificWork.Authors }
     * 
     */
    public ScientificWork.Authors createScientificWorkAuthors() {
        return new ScientificWork.Authors();
    }

    /**
     * Create an instance of {@link ScientificWork.References }
     * 
     */
    public ScientificWork.References createScientificWorkReferences() {
        return new ScientificWork.References();
    }

    /**
     * Create an instance of {@link ScientificWork.Abstract.Keywords }
     * 
     */
    public ScientificWork.Abstract.Keywords createScientificWorkAbstractKeywords() {
        return new ScientificWork.Abstract.Keywords();
    }

    /**
     * Create an instance of {@link Author.University }
     * 
     */
    public Author.University createAuthorUniversity() {
        return new Author.University();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/team3/scientificWork", name = "comment")
    public JAXBElement<String> createComment(String value) {
        return new JAXBElement<String>(_Comment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Author }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/team3/scientificWork", name = "author")
    public JAXBElement<Author> createAuthor(Author value) {
        return new JAXBElement<Author>(_Author_QNAME, Author.class, null, value);
    }

}
