<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sw="http://ftn.uns.ac.rs/team3/scientificWork">

	<xsl:template match="/">
		<html>
			<head>
				<title>ScientificWork</title>
			</head>
			<body
				style="font-family: Times New Roman; margin-left: 50px; margin-right: 50px;">
				<h1 style="text-align: center; font-weight: bold;">
					<xsl:value-of select="sw:scientificWork/sw:title" />
				</h1>

				<br />
				<i>
					Date received
					<xsl:value-of
						select="sw:scientificWork/sw:header/sw:received" />
				</i>
				<br />
				<i>
					Date revised
					<xsl:value-of
						select="sw:scientificWork/sw:header/sw:revised" />
				</i>
				<br />
				<i>
					Date accepted
					<xsl:value-of
						select="sw:scientificWork/sw:header/sw:accepted" />
				</i>

				<p style="text-align: center; font-weight: normal;">
					<xsl:for-each
						select="sw:scientificWork/sw:authors/sw:author">
						<i>
							<xsl:value-of select="sw:name" />
							&#xA0;
							<xsl:value-of select="sw:surname" />
						</i>
						<br />
						<i>
							<xsl:value-of select="sw:university/sw:name" />
							&#xA0;
							<xsl:value-of select="sw:university/sw:address" />
						</i>
						<br />
					</xsl:for-each>
				</p>
				<br />
				<br />

				<b style="font-size: 20px;">Abstract</b>
				<p>
					<b>Purpose&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:purpose" />
					<br />
					<b>Design&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:design" />
					<br />
					<b>Findings&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:findings" />
					<br />
					<b>Limitations&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:limitations" />
					<br />
					<b>Originality&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:originality" />
					<br />
					<b>Keywords&#xA0;&#xA0;</b>
					<xsl:for-each
						select="sw:scientificWork/sw:abstract/sw:keywords/sw:keyword">
						<xsl:if test="position()>1">
							,
						</xsl:if>
						<xsl:value-of select="." />
					</xsl:for-each>
					<br />
					<b>Scientific Work Type&#xA0;&#xA0;</b>
					<xsl:value-of
						select="sw:scientificWork/sw:abstract/sw:scientificWorkType" />
					<br />
				</p>

				<p style="text-align: center; font-weight: normal;">
					<xsl:for-each select="sw:scientificWork/sw:paragraph">
						<i>
							<xsl:value-of select="sw:text" />
						</i>
						<br />
					</xsl:for-each>
				</p>
				<br />

				<b style="font-size: 30px;">References</b>
				<ul style="list-style: none; padding: 0; margin: 0;">
					<xsl:for-each select="sw:scientificWork/sw:references">
						<li>
							<xsl:value-of select="sw:scientificWorkId" />
						</li>
					</xsl:for-each>
				</ul>

			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>