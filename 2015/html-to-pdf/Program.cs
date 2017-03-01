using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PdfSharp;
using PdfSharp.Pdf;
using TheArtOfDev.HtmlRenderer.PdfSharp;

namespace HtmlToPdf
{
    class Program
    {
        private static void Main(string[] args)
        {
            string html = "<p><h1>Hello World</h1>This is html rendered text <span class='blue'>with css styling in blue</span></p>";
            string css = ".blue { color: blue; }";
            PdfDocument pdf = PdfGenerator.GeneratePdf(html, PageSize.A4, 20, PdfGenerator.ParseStyleSheet(css));
            pdf.Save("document.pdf");
        }
    }

}
