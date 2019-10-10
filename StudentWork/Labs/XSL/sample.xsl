<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fr="http://flightnamespace" >
	<xsl:output method="text" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
	<xsl:text>  This itinerary includes </xsl:text>
		<xsl:value-of select="count(//fr:FlightInfo)" />
	<xsl:text> flights. </xsl:text>
	</xsl:template>
</xsl:stylesheet>
