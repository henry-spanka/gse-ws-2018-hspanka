<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes"/>
<xsl:decimal-format/>

<xsl:template match="checkstyle">
    <html>
        <head>
        <style type="text/css">
    .bannercell {
      border: 0px;
      padding: 0px;
    }
    body {
      margin-left: 10;
      margin-right: 10;
      font:normal 80% arial,helvetica,sanserif;
      background-color:#FFFFFF;
      color:#000000;
    }
    .a td { 
      background: #efefef;
    }
    .b td { 
      background: #fff;
    }
    th, td {
      text-align: left;
      vertical-align: top;
    }
    th {
      font-weight:bold;
      background: #ccc;
      color: black;
    }
    table, th, td {
      font-size:100%;
      border: none
    }
    table.log tr td, tr th {
      
    }
    h2 {
      font-weight:bold;
      font-size:140%;
      margin-bottom: 5;
    }
    h3 {
      font-size:100%;
      font-weight:bold;
      background: #525D76;
      color: white;
      text-decoration: none;
      padding: 5px;
      margin-right: 2px;
      margin-left: 2px;
      margin-bottom: 0;
    }
        </style>
        </head>
        <body>
          <a name="#top"/>
      <!-- jakarta logo -->
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="bannercell" rowspan="2">
          <!--a href="http://jakarta.apache.org/">
          <img src="http://jakarta.apache.org/images/jakarta-logo.gif" alt="http://jakarta.apache.org" align="left" border="0"/>
          </a-->
        </td>
            <td class="text-align:right"><h2>CheckStyle Audit</h2></td>
            </tr>
            <tr>
            <td class="text-align:right">Designed for use with <a href='http://checkstyle.sourceforge.net/'>CheckStyle</a> and <a href='http://jakarta.apache.org'>Ant</a>.</td>
            </tr>
      </table>
        <hr size="1"/>
            
            <!-- Summary part -->
            <xsl:apply-templates select="." mode="summary"/>
            <hr size="1" width="100%" align="left"/>
            
            <!-- Package List part -->
            <xsl:apply-templates select="." mode="filelist"/>
            <hr size="1" width="100%" align="left"/>
            
            <!-- For each package create its part -->
            <xsl:for-each select="file">
              <xsl:sort select="@name"/>
              <xsl:apply-templates select="."/>
              <p/>
              <p/>
            </xsl:for-each>
            <hr size="1" width="100%" align="left"/>
            
            
        </body>
    </html>
</xsl:template>
    
    
    
    <xsl:template match="checkstyle" mode="filelist">   
        <h3>Files</h3>
        <table class="log" border="0" cellpadding="5" cellspacing="2" width="100%">
      <tr>
        <th>Name</th>
        <th>Errors</th>
      </tr>
            <xsl:for-each select="file">
                <xsl:sort select="@name"/>
                <xsl:variable name="errorCount" select="count(error)"/>             
                <tr>
          <xsl:call-template name="alternated-row"/>
                    <td><a href="#{@name}"><xsl:value-of select="@name"/></a></td>
                    <td><xsl:value-of select="$errorCount"/></td>
                </tr>
            </xsl:for-each>
        </table>        
    </xsl:template>
    
    
    <xsl:template match="file">
      <a name="#{@name}"/>
    <h3>File <xsl:value-of select="@name"/></h3>
    
    <table class="log" border="0" cellpadding="5" cellspacing="2" width="100%">
        <tr>
          <th>Error Description</th>
          <th>Line</th>
      </tr>
      <xsl:for-each select="error">
        <tr>
        <xsl:call-template name="alternated-row"/>
          <td><xsl:value-of select="@message"/></td>
          <td><xsl:value-of select="@line"/></td>
        </tr>
        </xsl:for-each>
    </table>
    <a href="#top">Back to top</a>
    </xsl:template>
    
    
    <xsl:template match="checkstyle" mode="summary">
        <h3>Summary</h3>
        <xsl:variable name="fileCount" select="count(file)"/>
        <xsl:variable name="errorCount" select="count(file/error)"/>
        <table class="log" border="0" cellpadding="5" cellspacing="2" width="100%">
        <tr>
            <th>Files</th>
            <th>Errors</th>
        </tr>
        <tr>
          <xsl:call-template name="alternated-row"/>
            <td><xsl:value-of select="$fileCount"/></td>
            <td><xsl:value-of select="$errorCount"/></td>
        </tr>
        </table>
    </xsl:template>
    
  <xsl:template name="alternated-row">
    <xsl:attribute name="class">
      <xsl:if test="position() mod 2 = 1">a</xsl:if>
      <xsl:if test="position() mod 2 = 0">b</xsl:if>
    </xsl:attribute>  
  </xsl:template>   
</xsl:stylesheet>

