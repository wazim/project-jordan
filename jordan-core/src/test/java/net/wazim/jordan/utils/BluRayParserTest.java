package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BluRayParserTest {

    @Test
    public void successfullyParsesBluRays() {
        JordanHttpResponse response = new JordanHttpResponse(200, sampleAmazonResponse());
        InMemoryPersistableDatabase database = new InMemoryPersistableDatabase();

        BluRayParser.parseIntoBluRays(response, URI.create("http://x.com"), database);
        assertThat(database.getFirstBluRay().getName(), is("Batman Begins (Blu-ray) (2005) (Region Free)"));
        assertThat(database.getFirstBluRay().getId(), is(-903014007));
    }

    private String sampleAmazonResponse() {
        return "<div id=\"result_116\" class=\"fstRowGrid prod celwidget\" name=\"B0019FLTH8\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.amazon.co.uk/Batman-Begins-Blu-ray-Region-Free/dp/B0019FLTH8/ref=sr_1_117?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-117\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51DS%2B%2BLIMGL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Batman-Begins-Blu-ray-Region-Free/dp/B0019FLTH8/ref=sr_1_117?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-117\"><span class=\"lrg bold\">Batman Begins [Blu-ray] [2005] [Region Free]</span></a> <span class=\"med reg\">(2008) <span class=\"starring\">Starring Christian Bale,&#32;Michael Caine,&#32;Liam Neeson and Katie Holmes</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Batman-Begins-Blu-ray-Region-Free/dp/B0019FLTH8/ref=sr_1_117?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-117\"><span class=\"bld lrg red\"> £0.48</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B0019FLTH8/ref=sr_1_117_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-117&amp;condition=new\"><span class=\"price bld\">£0.80</span> new <span class=\"grey\">(48 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B0019FLTH8/ref=sr_1_117_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-117&amp;condition=used\"><span class=\"price bld\">£0.92</span> used <span class=\"grey\">(19 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B0019FLTH8&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B0019FLTH8\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.5 out of 5 stars\" href=\"http://www.amazon.co.uk/Batman-Begins-Blu-ray-Region-Free/product-reviews/B0019FLTH8/ref=sr_1_117_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Batman-Begins-Blu-ray-Region-Free/product-reviews/B0019FLTH8/ref=sr_1_117_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">363</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_117\" class=\"rsltGrid prod celwidget\" name=\"B00BBTNOE8\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.amazon.co.uk/Worlds-End-Blu-ray-Simon-Pegg/dp/B00BBTNOE8/ref=sr_1_118?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-118\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51aqmcM53XL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Worlds-End-Blu-ray-Simon-Pegg/dp/B00BBTNOE8/ref=sr_1_118?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-118\"><span class=\"lrg bold\">The World's End [Blu-ray]</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Simon Pegg,&#32;Nick Frost,&#32;Martin Freeman and Rosmand Pike</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Worlds-End-Blu-ray-Simon-Pegg/dp/B00BBTNOE8/ref=sr_1_118?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-118\"><span class=\"bld lrg red\"> £8.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B00BBTNOE8/ref=sr_1_118_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-118&amp;condition=new\"><span class=\"price bld\">£6.39</span> new <span class=\"grey\">(37 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B00BBTNOE8/ref=sr_1_118_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-118&amp;condition=used\"><span class=\"price bld\">£5.00</span> used <span class=\"grey\">(12 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00BBTNOE8&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00BBTNOE8\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.5 out of 5 stars\" href=\"http://www.amazon.co.uk/Worlds-End-Blu-ray-Simon-Pegg/product-reviews/B00BBTNOE8/ref=sr_1_118_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-3-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Worlds-End-Blu-ray-Simon-Pegg/product-reviews/B00BBTNOE8/ref=sr_1_118_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">333</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_118\" class=\"rsltGrid prod celwidget\" name=\"B003E47G3S\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.amazon.co.uk/Shutter-Island-Blu-ray-Leonardo-DiCaprio/dp/B003E47G3S/ref=sr_1_119?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-119\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51AuFABK2OL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Shutter-Island-Blu-ray-Leonardo-DiCaprio/dp/B003E47G3S/ref=sr_1_119?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-119\"><span class=\"lrg bold\">Shutter Island [Blu-ray] [2010]</span></a> <span class=\"med reg\">(2010) <span class=\"starring\">Starring Leonardo DiCaprio,&#32;Mark Ruffalo,&#32;Ben Kingsley, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Shutter-Island-Blu-ray-Leonardo-DiCaprio/dp/B003E47G3S/ref=sr_1_119?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-119\"><span class=\"bld lrg red\"> £4.62</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B003E47G3S/ref=sr_1_119_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-119&amp;condition=new\"><span class=\"price bld\">£3.23</span> new <span class=\"grey\">(46 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B003E47G3S/ref=sr_1_119_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-119&amp;condition=used\"><span class=\"price bld\">£1.40</span> used <span class=\"grey\">(19 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B003E47G3S&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B003E47G3S\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.1 out of 5 stars\" href=\"http://www.amazon.co.uk/Shutter-Island-Blu-ray-Leonardo-DiCaprio/product-reviews/B003E47G3S/ref=sr_1_119_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Shutter-Island-Blu-ray-Leonardo-DiCaprio/product-reviews/B003E47G3S/ref=sr_1_119_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">313</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_119\" class=\"rsltGrid prod celwidget\" name=\"B003IHVKS8\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/dp/B003IHVKS8/ref=sr_1_120?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51wK5QjXoEL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/dp/B003IHVKS8/ref=sr_1_120?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120\"><span class=\"lrg bold\">Forbidden Planet [Blu-ray] [1956] [Region Free]</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Walter Pidgeon,&#32;Leslie Nielsen,&#32;Anne Francis and Jack Kelly</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/dp/B003IHVKS8/ref=sr_1_120?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120\"><span class=\"bld lrg red\"> £7.50</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/dp/B003IHVKS8/ref=sr_1_120_det?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/dp/B003IHVKS8/ref=sr_1_120_det?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-amazon.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B003IHVKS8/ref=sr_1_120_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120&amp;condition=new\"><span class=\"price bld\">£7.03</span> new <span class=\"grey\">(34 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.amazon.co.uk/gp/offer-listing/B003IHVKS8/ref=sr_1_120_olp?s=dvd&amp;ie=UTF8&amp;qid=1412705118&amp;sr=1-120&amp;condition=used\"><span class=\"price bld\">£6.20</span> used <span class=\"grey\">(4 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B003IHVKS8&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B003IHVKS8\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.7 out of 5 stars\" href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/product-reviews/B003IHVKS8/ref=sr_1_120_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Forbidden-Planet-Blu-ray-Region-Free/product-reviews/B003IHVKS8/ref=sr_1_120_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">209</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "</div>\n" +
                "<div id=\"search-js-btr\">\n" +
                "      <script type=\"text/javascript\">\n" +
                "\n" +
                "      \n" +
                "\n" +
                "        amzn.sx.utils.jsDepMgr.when(\"jQuery ready\", 'searchJsBtr', function($) {\n" +
                "            \n" +
                "          $(\"td.tpType\").parent(\"tr\").hover(\n" +
                "            function() { $(this).addClass(\"toeRowHover\"); },\n" +
                "            function() { $(this).removeClass(\"toeRowHover\"); });\n" +
                "\n" +
                "          \n" +
                "          amzn.sx.utils.jsDepMgr.when(\"ImageRotation\", 'setupImageRotation-atf-next', function (imageRotation) {\n" +
                "            imageRotation.setupImageRotation(\"atf\");\n" +
                "            imageRotation.setupImageRotation(\"btf\");\n" +
                "          });\n" +
                "          \n" +
                "\n" +
                "        });\n" +
                "\n" +
                "        amzn.sx.utils.jsDepMgr.when('search-js-general', 'processPromotionsPopover', function() {\n" +
                "          \n" +
                "          amzn.sx.utils.jsDepMgr.when('popover', 'promotionsPopover', function() {\n" +
                "            processPromotionsPopover();\n" +
                "\n" +
                "            \n" +
                "          });\n" +
                "\n" +
                "          \n" +
                "          SPUtils.triggerEvent(\"spResultsEnd\");\n" +
                "\n" +
                "          registerAivHandler(\"btf\");\n" +
                "\n" +
                "          \n" +
                "          if (typeof processBundlesFlyouts == 'function') {\n" +
                "              processBundlesFlyouts('btf');\n" +
                "          }\n" +
                "\n" +
                "          \n" +
                "        });\n" +
                "\n" +
                "        </script>\n" +
                "      </div>\n" +
                "  <div id=\"raw-search-js-btr\" class=\"searchUndoAUIHacks\">\n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"search-js-btr-external\">\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</div><div id=\"raw-search-desktop-advertising-tower-1\" class=\"searchUndoAUIHacks\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "      \n" +
                "      <div id=\"click_withinLazyLoad_tower\" class=\"searchBreakAUI\">\n" +
                "  </div>\n" +
                "\n" +
                "\n" +
                "  <script type=\"text/JavaScript\">\n" +
                "\n" +
                "      \n" +
                "(function() {\n" +
                "  P.when(\"A\").execute(function (A) {\n" +
                "    var jQuery = A.$;\n" +
                "    var widgetName  = \"click_within_tower\";\n" +
                "    var keywords =  \"\";\n" +
                "    var searchAlias = \"\";\n" +
                "    var browseLadder = [];\n" +
                "      browseLadder.push(\"283926\");\n" +
                "      browseLadder.push(\"573408\");\n" +
                "      browseLadder.push(\"712388\");\n" +
                "      browseLadder.push(\"293962011\");\n" +
                "    var childBrowseNode = \"\";\n" +
                "    var deviceType = \"desktop\";\n" +
                "    var pageType = \"Search\";\n" +
                "    var subPageType = \"portal-batch-slow-btf\";\n" +
                "    var profile = null;\n" +
                "    var extensions = undefined;\n" +
                "    var placement = \"tower\";\n" +
                "    var useAui = \"1\";\n" +
                "    var browseName = \"Blu-ray\";\n" +
                "    var hasRefinements = \"1\";\n" +
                "    var isSorted = 0;\n" +
                "    var specialtyRestriction = \"\";\n" +
                "    var hiddenKeywords = \"\";\n" +
                "    var vehicle = \"\";\n" +
                "    var widget = jQuery('#click_withinLazyLoad_tower'); \n" +
                "    var stateKey = 'sponsoredProductsState';\n" +
                "    var layout = \"grid\";\n" +
                "    var columns = 3;\n" +
                "    var pageNumber = 5;\n" +
                "\n" +
                "    var existingState = A.state(stateKey);    \n" +
                "\n" +
                "    var seenAsins;\n" +
                "\n" +
                "    var isNewLadder = function(a1, a2) {\n" +
                "      if (a1.length === 0 || a2.length === 0) {\n" +
                "        return true;\n" +
                "      }\n" +
                "\n" +
                "      if (a1[0] !== a2[0]) {\n" +
                "        return true;\n" +
                "      }\n" +
                "\n" +
                "      if (searchAlias !== \"aps\") {\n" +
                "        if (a1[a1.length - 1] !== a2[a2.length - 1]) {\n" +
                "          return true;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      return false;\n" +
                "    }\n" +
                "\n" +
                "    if (typeof existingState !== \"undefined\") {\n" +
                "      if (existingState.keywords !== keywords) {\n" +
                "        seenAsins = [];\n" +
                "        existingState.keywords = keywords;\n" +
                "        existingState.browseLadder = browseLadder;\n" +
                "        existingState.seenAsins = [];\n" +
                "        A.state.replace(stateKey, existingState);\n" +
                "      } else if (isNewLadder(existingState.browseLadder, browseLadder)) {\n" +
                "        seenAsins = [];\n" +
                "        existingState.browseLadder = browseLadder;\n" +
                "        existingState.seenAsins = [];\n" +
                "        A.state.replace(stateKey, existingState);\n" +
                "      } else {\n" +
                "        seenAsins = existingState.seenAsins;\n" +
                "      }\n" +
                "    } else {\n" +
                "      var spData = {\n" +
                "        'keywords':keywords,\n" +
                "        'browseLadder':browseLadder,\n" +
                "        'seenAsins':[]\n" +
                "      }\n" +
                "\n" +
                "      A.state(stateKey, spData);\n" +
                "    }\n" +
                "\n" +
                "    var lazyLoadURL = '/gp/sponsored-products/lazyLoad/handler/click-within-handler.html';\n" +
                "    jQuery.ajax({\n" +
                "      \n" +
                "      url: lazyLoadURL + \"?searchTerms=\"+keywords+\n" +
                "                         \"&searchAlias=\"+encodeURIComponent(searchAlias)+\n" +
                "                         \"&widgetName=\"+encodeURIComponent(widgetName)+\n" +
                "                         \"&pageType=\" + encodeURIComponent(pageType) + \n" +
                "                         \"&subPageType=\" + encodeURIComponent(subPageType) +\n" +
                "                         \"&browseLadder=\"+encodeURIComponent(browseLadder)+\n" +
                "                         \"&childBrowseNodes=\"+ encodeURIComponent(childBrowseNode) +\n" +
                "                         \"&placement=\" + encodeURIComponent(placement) + \n" +
                "                         \"&seenasins=\"+encodeURIComponent(seenAsins)+\n" +
                "                         \"&useAui=\" + encodeURIComponent(useAui) +\n" +
                "                         \"&browseName=\" + encodeURIComponent(browseName) +\n" +
                "                         \"&deviceType=\" + encodeURIComponent(deviceType) +\n" +
                "                         \"&profile=\" + encodeURIComponent(profile) +\n" +
                "                         \"&extensions=\" + encodeURIComponent(extensions) +\n" +
                "                         \"&hasRefinements=\" + encodeURIComponent(hasRefinements) + \n" +
                "                         \"&isSorted=\" + encodeURIComponent(isSorted) +\n" +
                "                         \"&specialtyRestriction=\" + encodeURIComponent(specialtyRestriction) +\n" +
                "                         \"&hiddenKeywords=\" + encodeURIComponent(hiddenKeywords) +\n" +
                "                         \"&vehicle=\" + encodeURIComponent(vehicle) +\n" +
                "                         \"&layout=\" + encodeURIComponent(layout) +\n" +
                "                         \"&columns=\" + encodeURIComponent(columns) +\n" +
                "                         \"&pageNumber=\" + encodeURIComponent(pageNumber),\n" +
                "\n" +
                "      type: \"GET\",\n" +
                "      cache : false,\n" +
                "      success : function(html) { \n" +
                "        widget.html(html);    \n" +
                "      },\n" +
                "      error : function(html) { \n" +
                "        widget.html(\"<div></div>\");    \n" +
                "      }\n" +
                "    }); \n" +
                "  });\n" +
                "})();\n" +
                "\n" +
                "  </script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                " \n" +
                " \n" +
                "\n" +
                "\n" +
                "</div></div>\n" +
                "                            </div>\n" +
                "                        \n" +
                "                            <div id=\"centerBelowMinus\">\n" +
                "                                <div class=\"img_header hdr noborder\" id=\"bottomBar\">\n" +
                "    <div id=\"pagn\" class=\"pagnHy\" >\n" +
                "            <span class=\"pagnLA\"> <a title=\"Previous Page\"\n" +
                "                       id=\"pagnPrevLink\"\n" +
                "                       class=\"pagnPrev\"\n" +
                "                       href=\"/s/ref=sr_pg_4?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-800&amp;page=4&amp;ie=UTF8&amp;qid=1412705118\">\n" +
                "                       <span class=\"srSprite pagnPrevArrow\"></span>\n" +
                "                       <span id=\"pagnPrevString\">Previous Page</span>\n" +
                "                    </a></span>\n" +
                "                <span class=\"pagnLink\"><a href=\"/s/ref=sr_pg_1?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-800&amp;ie=UTF8&amp;qid=1412705118\" >1</a></span>\n" +
                "                            <span class=\"pagnMore\">...</span>\n" +
                "                            <span class=\"pagnLink\"><a href=\"/s/ref=sr_pg_4?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-800&amp;page=4&amp;ie=UTF8&amp;qid=1412705118\" >4</a></span>\n" +
                "                            <span class=\"pagnCur\">5</span>\n" +
                "                            <span class=\"pagnLink\"><a href=\"/s/ref=sr_pg_6?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-800&amp;page=6&amp;ie=UTF8&amp;qid=1412705118\" >6</a></span>\n" +
                "                            <span class=\"pagnMore\">...</span>\n" +
                "                            <span class=\"pagnDisabled\">4</span>\n" +
                "                            <span class=\"pagnRA\"> <a title=\"Next Page\"\n" +
                "                       id=\"pagnNextLink\"\n" +
                "                       class=\"pagnNext\"\n" +
                "                       href=\"/s/ref=sr_pg_6?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-800&amp;page=6&amp;ie=UTF8&amp;qid=1412705118\">\n" +
                "                        <span id=\"pagnNextString\">Next Page</span>\n" +
                "                        <span class=\"srSprite pagnNextArrow\"></span>\n" +
                "                     </a></span>\n" +
                "            <br clear=\"all\" />\n" +
                "        </div>\n" +
                "    </div>";
    }

}