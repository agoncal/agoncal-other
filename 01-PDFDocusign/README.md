# Other - iText and DocusSign

## Purpose of this sample

The purpose of this sample is to show how to generate a PDF document with [iText 7](http://itextpdf.com/) and send it through [DocuSign](http://docusign.com).

[Read more on my blog](http://antoniogoncalves.org/2013/07/03/monster-component-in-java-ee-7/)

## Execute the sample

The `Main` class uses the :

* `PdfGenerator` : generates a PDF with iText
* `PdfSender` : sends the PDF with DocuSign. For this to work you need a `src/main/resources/docusign.properties`containing the DocuSign user/pwd/integration key suchs as 

```
username=theDocusign.email@address.com
password=theDocusignPassword
integratorKey=some1-thing2-like3-that-e4294bffd2c4
```

<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>
