<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:file="xalan://java.io.File"
  xmlns:fis="xalan://java.io.FileInputStream"
  xmlns:bis="xalan://java.io.BufferedInputStream"
  xmlns:dis="xalan://java.io.DataInputStream">
<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>


<xsl:template match="/" >
<html>
<body>
<p>
  <xsl:choose>
     <xsl:when test="file:exists(file:new('C:\StudentWork\workspace\AdvXSLT2\Document.xml'))">
      <xsl:variable name="targetfile" select="file:new('C:\CodeZone\HeliosXML\TestProject\Examples\Document.xml')" />
      <xsl:value-of select="file:getAbsolutePath($targetfile)"/>
      <xsl:variable name="targetfis" select="fis:new($targetfile)" />
      <xsl:variable name="targetbis" select="bis:new($targetfis)" />
      <xsl:variable name="targetdis" select="dis:new($targetbis)" /> 
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
      <xsl:if test="dis:available($targetdis)">
        <xsl:text>&#10;</xsl:text>
        <xsl:value-of select="dis:readLine($targetdis)"/>
      </xsl:if>
     </xsl:when>
     <xsl:otherwise>
     ...xsl code WITHOUT using the document()
     </xsl:otherwise>
  </xsl:choose>
</p>
</body>
</html>
</xsl:template>




</xsl:stylesheet>
