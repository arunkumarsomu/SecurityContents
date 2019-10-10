<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
  

<xsl:template match="/"  xmlns:file="xalan://java.io.File">
<html>
<body>
<p>
  <xsl:choose>
     <xsl:when test="file:exists(file:new('C:\StudentWork\workspace\AdvXSLT2\Document.xml'))">
     ...xsl code using the document()...
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
