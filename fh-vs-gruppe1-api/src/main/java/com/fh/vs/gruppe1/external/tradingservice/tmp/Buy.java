
package com.fh.vs.gruppe1.external.tradingservice.tmp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java-Klasse f�r buy complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>{@code
 * <complexType name="buy">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="symbol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="shares" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buy", propOrder = {
    "symbol",
    "shares"
})
public class Buy {

    @XmlElement(required = true)
    protected String symbol;
    protected int shares;

    /**
     * Ruft den Wert der symbol-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Legt den Wert der symbol-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }

    /**
     * Ruft den Wert der shares-Eigenschaft ab.
     * 
     */
    public int getShares() {
        return shares;
    }

    /**
     * Legt den Wert der shares-Eigenschaft fest.
     * 
     */
    public void setShares(int value) {
        this.shares = value;
    }

}
