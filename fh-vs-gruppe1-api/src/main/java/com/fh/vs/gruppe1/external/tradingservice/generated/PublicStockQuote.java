
package com.fh.vs.gruppe1.external.tradingservice.generated;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r publicStockQuote complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>{@code
 * <complexType name="publicStockQuote">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="floatShares" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="lastTradePrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="lastTradeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="marketCapitalization" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="stockExchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publicStockQuote", propOrder = {
    "companyName",
    "floatShares",
    "lastTradePrice",
    "lastTradeTime",
    "marketCapitalization",
    "stockExchange",
    "symbol"
})
public class PublicStockQuote {

    protected String companyName;
    protected Long floatShares;
    protected BigDecimal lastTradePrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastTradeTime;
    protected Long marketCapitalization;
    protected String stockExchange;
    protected String symbol;

    /**
     * Ruft den Wert der companyName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Legt den Wert der companyName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Ruft den Wert der floatShares-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFloatShares() {
        return floatShares;
    }

    /**
     * Legt den Wert der floatShares-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFloatShares(Long value) {
        this.floatShares = value;
    }

    /**
     * Ruft den Wert der lastTradePrice-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLastTradePrice() {
        return lastTradePrice;
    }

    /**
     * Legt den Wert der lastTradePrice-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLastTradePrice(BigDecimal value) {
        this.lastTradePrice = value;
    }

    /**
     * Ruft den Wert der lastTradeTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastTradeTime() {
        return lastTradeTime;
    }

    /**
     * Legt den Wert der lastTradeTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastTradeTime(XMLGregorianCalendar value) {
        this.lastTradeTime = value;
    }

    /**
     * Ruft den Wert der marketCapitalization-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    /**
     * Legt den Wert der marketCapitalization-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMarketCapitalization(Long value) {
        this.marketCapitalization = value;
    }

    /**
     * Ruft den Wert der stockExchange-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStockExchange() {
        return stockExchange;
    }

    /**
     * Legt den Wert der stockExchange-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStockExchange(String value) {
        this.stockExchange = value;
    }

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

}
