<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
  
<xsl:template match="/"  xmlns:date="xalan://java.util.Date"
   xmlns:formatter="xalan://java.text.SimpleDateFormat">
<html>
<body>
   <xsl:variable name="today" select="date:new()" />
   <xsl:variable name="formatter" select="formatter:new('MM/dd/yyyy')" />
   <p>Date: <xsl:value-of select="formatter:format($formatter, $today)"/></p> 
</body>
</html>
</xsl:template>
</xsl:stylesheet>
