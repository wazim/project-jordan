package net.wazim.jordan.builder;

import net.wazim.jordan.domain.BluRay;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static net.wazim.jordan.fixtures.BluRayDataFixtures.someHtmlClass;

public class AmazonHtmlResponseBuilder {

    private List<BluRay> bluRays = new ArrayList<>();
    private int belowPrice;
    private int pageNumber;
    private int totalPageNumber;

    public AmazonHtmlResponseBuilder() {
        underPriceOf(8);
        withCurrentPageNumber(1);
        withTotalPageNumbers(1);
    }

    public AmazonHtmlResponseBuilder with(BluRay bluRay) {
        bluRays.add(bluRay);
        return this;
    }

    public AmazonHtmlResponseBuilder underPriceOf(int bluRaysBelowValue) {
        belowPrice = bluRaysBelowValue;
        return this;
    }

    public AmazonHtmlResponseBuilder withCurrentPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public AmazonHtmlResponseBuilder withTotalPageNumbers(int totalPageNumbers) {
        this.totalPageNumber = totalPageNumbers;
        return this;
    }

    public String build() {
        String htmlHeader = "<html><head><title>x.co.uk: Under £" + belowPrice + " - Blu-ray / Blu-ray: DVD &amp; Blu-ray</title>\n" +
                "<body>\n" +
                "Blu Ray Under £" + belowPrice;

        String htmlBody = "";

        for (int i = 0; i < bluRays.size(); i++) {
            htmlBody += "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                    "<div id=\"result_" + i + "\" class=\""+someHtmlClass()+" prod celwidget\" name=\"B008OGHUFK\">\n <div class=\"s-item-container\">" +
                    "<h3 class=\"newaps\">\n" +
                    "<span class=\"a-spacing-none\"><a href=\"http://www.amazon.co.uk/" + bluRays.get(i).getName() + "\"></a></span>" +
                    "<span class=\"s-access-title\">" + bluRays.get(i).getName() + "</span>\n" +
                    "</h3><ul class=\"rsltGridList grey\">\n" +
                    "<li class=\"newp\">\n" +
                    "<div class=\"\">\n" +
                    "<span class=\"a-color-price\">" + bluRays.get(i).getPriceNew() + "</span>\n" +
                    "<span class=\"a-color-price\">" + bluRays.get(i).getPriceNew() + "</span>" +
                    "<span class=\"a-color-price\">" + bluRays.get(i).getPriceUsed() + "</span>" +
                    "</div></div></div></div>";
        }

        String htmlFooter =  "<div id=\"centerBelowMinus\">\n" +
                "<div class=\"img_header hdr noborder\" id=\"bottomBar\">\n" +
                "<div id=\"pagn\" class=\"pagnHy\" >\n" +
                "<span class=\"pagnLA1\"> <span class=\"srSprite firstPageLeftArrow\"></span>\n" +
                "<span id=\"pagnPrevString\">Previous Page</span></span>\n" +
                "<span class=\"pagnCur\">" + pageNumber +"</span>\n" +
                "<span class=\"pagnDisabled\">" + totalPageNumber + "</span>\n" +
                "<span class=\"pagnRA\"> <a title=\"Next Page\" id=\"pagnNextLink\" class=\"pagnNext\" href=\"no-link\">\n" +
                "<span id=\"pagnNextString\">Next Page</span>\n" +
                "<span class=\"srSprite pagnNextArrow\"></span>\n" +
                "</a></span>\n" +
                "<br clear=\"all\" />\n" +
                "</div>\n" +
                "</div>\n" +
                "</body></html>";

        return format("%s\n%s\n%s", htmlHeader, htmlBody, htmlFooter);
    }

}
