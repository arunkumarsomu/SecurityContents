<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
  
<xsl:template match="/"  xmlns:date="xalan://java.util.Date">
<html>
<body>
   <p>Date: <xsl:value-of select="date:new()"/></p> 
</body>
</html>
</xsl:template>
</xsl:stylesheet>
