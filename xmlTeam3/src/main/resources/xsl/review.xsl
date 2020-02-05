<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:r="http://ftn.uns.ac.rs/team3/review">

	<xsl:template match="/">
		<html>
			<head>
				<title>Review</title>
			</head>
			<body
				style="font-family: Times New Roman; margin-left: 50px; margin-right: 50px;">
				<h1 style="text-align: center; font-weight: bold;">
					Review
				</h1>

				<b style="font-size: 20px;">Grades</b>
				<p>
					<b>Uniqueness&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:uniqueness" />
					<br />

					<b>Keywords&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:keywords" />
					<br />

					<b>Abstract&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:abstract" />
					<br />

					<b>Conclusions&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:conclusions" />
					<br />

					<b>Experiments&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:experiments" />
					<br />

					<b>Layout&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:layout" />
					<br />

					<b>Formality&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:formality" />
					<br />

					<b>References&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:references" />
					<br />

					<b>Headings&#xA0;&#xA0;</b>
					<xsl:value-of select="r:review/r:grades/r:headings" />
					<br />

					<b>General Grade&#xA0;&#xA0;</b>
					<xsl:value-of
						select="r:review/r:grades/r:generalGrade" />
					<br />
				</p>




			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>