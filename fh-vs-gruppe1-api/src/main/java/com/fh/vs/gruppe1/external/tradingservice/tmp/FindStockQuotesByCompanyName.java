
package com.fh.vs.gruppe1.external.tradingservice.tmp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r findStockQuotesByCompanyName complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>{@code
 * <complexType name="findStockQuotesByCompanyName">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="partOfCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findStockQuotesByCompanyName", propOrder = {
    "partOfCompanyName"
})
public class FindStockQuotesByCompanyName {

    @XmlElement(required = true)
    protected String partOfCompanyName;

    /**
     * Ruft den Wert der partOfCompanyName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartOfCompanyName() {
        return partOfCompanyName;
    }

    /**
     * Legt den Wert der partOfCompanyName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartOfCompanyName(String value) {
        this.partOfCompanyName = value;
    }

}
