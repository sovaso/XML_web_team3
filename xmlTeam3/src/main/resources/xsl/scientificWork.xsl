<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:template match="/">
        <html>
            <head>
                <title>ScientificWork</title>
            </head>
            <body style="font-family: Times New Roman; margin-left: 50px; margin-right: 50px;">
                <h1 style="text-align: center; font-weight: bold;">
                    <xsl:value-of select="scientificWork/title"/>
                </h1>
                <xsl:apply-templates select="scientificWork"/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="scientificWork">
        <xsl:apply-templates select="header"/>
        <xsl:apply-templates select="authors"/>
        <xsl:apply-templates select="abstract"/>
        <xsl:apply-templates select="paragraph"/>
        <xsl:apply-templates select="references"/>
    </xsl:template>
    
    <xsl:template match="scientificWork/authors">
        <p style="text-align: center; font-weight: normal;">
            <xsl:for-each select="author">
                <i>
                    <xsl:value-of select="name"/> &#xA0;<xsl:value-of select="surname"/>
                </i>
                <br/>
                <i>
                    <xsl:value-of select="university/name"/> &#xA0;<xsl:value-of select="university/address"/>
                </i>
                <br/>
            </xsl:for-each>
        </p>
        <br/>
        <br/>
    </xsl:template>
    
    <xsl:template match="scientificWork/abstract">
        
        <b style="font-size: 20px;">Abstract</b>
        <p>
            <b>Purpose&#xA0;&#xA0;</b>
            <xsl:value-of select="purpose"/>
            <br/>
            <b>Design/Methodology/Approach&#xA0;&#xA0;</b>
            <xsl:value-of select="design"/>
            <br/>
            <b>Findings&#xA0;&#xA0;</b>
            <xsl:value-of select="findings"/>
            <br/>
            <b>Research Limitations Implications&#xA0;&#xA0;</b>
            <xsl:value-of select="limitations"/>
            <br/>
            <b>Originality Value&#xA0;&#xA0;</b>
            <xsl:value-of select="originality"/>
            <br/>
            <b>Keywords&#xA0;&#xA0;</b>
            <xsl:for-each select="keywords/keyword">
                <xsl:if test="position()>1">,
                </xsl:if>
                <xsl:value-of select="."/>
            </xsl:for-each>
            <br/>
            <b>Paper Type&#xA0;&#xA0;</b>
            <xsl:value-of select="scientificWorkType"/>
            <br/>
        </p>
    </xsl:template>
    
    <xsl:template match="scientificWork/paragraph">
        <p style="text-align: center; font-weight: normal;">
            <xsl:for-each select="/">
                <i>
                    <xsl:value-of select="text"/> &#xA0;<xsl:value-of select="surname"/>
                </i>
                <br/>
            </xsl:for-each>
        </p>
        <br/>
    </xsl:template>
    
    <xsl:template match="scientificWork/references">
        <b style="font-size: 30px;">References</b>
        <ul style="list-style: none; padding: 0; margin: 0;">
            <xsl:for-each select="references">
                <li>
                    <xsl:value-of select="scientificWorkId"/>
                </li>
            </xsl:for-each>
        </ul>
    </xsl:template>
    
    <xsl:template match="scientificWork/header">
        <br/>
        <br/>
        <i>Date received
            <xsl:value-of select="received"/>
        </i>
        <br/>
        <i>Date revised
            <xsl:value-of select="revised"/>
        </i>
        <br/>
        <i>Date accepted
            <xsl:value-of select="accepted"/>
        </i>
    </xsl:template>
    
</xsl:stylesheet>