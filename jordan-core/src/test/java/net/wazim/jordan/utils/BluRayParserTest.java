package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BluRayParserTest {

    @Test
    public void successfullyParsesBluRays() {
        JordanHttpResponse response = new JordanHttpResponse(200, sampleAmazonResponse());
        ArrayList<BluRay> bluRays = BluRayParser.parseIntoBluRays(response, 1);
        assertThat(bluRays.get(0).name(), is("The Raid [Blu-ray]"));
    }

    private String sampleAmazonResponse() {
        return "<div id=\"result_0\" class=\"fstRowGrid prod celwidget\" name=\"B008OGHUFK\"> \n" +
                "  <div class=\"linePlaceholder\"></div>\n" +
                "  <div class=\"image imageContainer\"> \n" +
                "   <a href=\"http://www.amazon.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK\">\n" +
                "    <div class=\"imageBox\"> \n" +
                "     <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51AvOH64ZpL._AA160_.jpg\" class=\"productImage cfMarker\" alt=\"Product Details\" /> \n" +
                "    </div></a>\n" +
                "  </div> \n" +
                "  <h3 class=\"newaps\"> <a href=\"http://www.amazon.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK\"><span class=\"lrg bold\">Dredd (Blu-ray 3D + Blu-ray)</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Karl Urban, Olivia Thirlby, Lena Headey, et al.</span></span> </h3>\n" +
                "  <ul class=\"rsltGridList grey\"> \n" +
                "   <li class=\"newp\"> \n" +
                "    <div class=\"\"> \n" +
                "     <a href=\"http://www.amazon.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK\"><span class=\"bld lrg red\"> &pound;6.00</span> <span class=\"lrg\">Blu-ray</span> </a>\n" +
                "     <div class=\"imageBox\"> \n" +
                "      <img src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\" height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\" align=\"baseline\" /> \n" +
                "     </div>\n" +
                "    </div> </li> \n" +
                "   <li> <span class=\"grey sml\"> Get it by <span class=\"bld grn nowrp\">Friday, Oct 3</span></span> </li>\n" +
                "   <li class=\"sss2\"> FREE Delivery on orders over &pound;10 </li>\n" +
                "   <li class=\"sect mbc\">More buying choices - Blu-ray</li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B008OGHUFK\"><span class=\"price bld\">&pound;4.46</span> new <span class=\"grey\">(47 offers)</span></a> </li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B008OGHUFK\"><span class=\"price bld\">&pound;4.47</span> used <span class=\"grey\">(6 offers)</span></a> </li>\n" +
                "   <li class=\"rvw\"> <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B008OGHUFK&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B008OGHUFK\" ref=\"sr_cr_\" class=\"asinReviewsSummary\"> <a alt=\"4.5 out of 5 stars\" href=\"http://www.amazon.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK\"><span class=\"srSprite spr_stars4_5Active newStars\"> <span class=\"displayNone\"></span> </span> <span class=\"srSprite spr_chevron\"> <span class=\"displayNone\"></span> </span> </a></span> </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK\">1,119</a>)</span> </li> \n" +
                "   <li class=\"bestSellerMessage\"> \n" +
                "    <div class=\"badgeWrapper\"> \n" +
                "     <a href=\"/gp/bestsellers/dvd/383380011/ref=sr_bs_1\"><span class=\"flagWrapper\"> <span class=\"left \"> <span class=\"topCorner bsbSprite\"></span> <span class=\"btmCorner bsbSprite\"></span> <span class=\"rank\"> #<span class=\"rankNumber\">1</span> Best Seller</span> </span> <span class=\"right \"> <span class=\"topCorner bsbSprite\"></span> <span class=\"btmCorner bsbSprite\"></span> </span> </span> <span class=\"node\"> in <span class=\"nodeLink\">Blu-ray</span></span></a>\n" +
                "    </div> </li> \n" +
                "  </ul> \n" +
                "  <br clear=\"all\" /> \n" +
                " </div> \n" +
                " <div id=\"result_1\" class=\"fstRowGrid prod celwidget\" name=\"B00838IG3O\"> \n" +
                "  <div class=\"linePlaceholder\"></div>\n" +
                "  <div class=\"image imageContainer\"> \n" +
                "   <a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/dp/B00838IG3O\">\n" +
                "    <div class=\"imageBox\"> \n" +
                "     <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/513prOaUOGL._AA160_.jpg\" class=\"productImage cfMarker\" alt=\"Product Details\" /> \n" +
                "    </div></a>\n" +
                "  </div> \n" +
                "  <h3 class=\"newaps\"> <a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/dp/B00838IG3O\"><span class=\"lrg bold\">The Raid [Blu-ray]</span></a> <span class=\"med reg\">(2012) <span class=\"starring\">Starring Iko Uwais, Joe Taslim, Doni Alamsyah and Yayan Ruhian</span></span> </h3>\n" +
                "  <ul class=\"rsltGridList grey\"> \n" +
                "   <li class=\"newp\"> \n" +
                "    <div class=\"\"> \n" +
                "     <a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/dp/B00838IG3O\"><span class=\"bld lrg red\"> &pound;5.60</span> <span class=\"lrg\">Blu-ray</span> </a>\n" +
                "     <div class=\"imageBox\"> \n" +
                "      <img src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\" height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\" align=\"baseline\" /> \n" +
                "     </div>\n" +
                "    </div> </li> \n" +
                "   <li> <span class=\"grey sml\"> Get it by <span class=\"bld grn nowrp\">Friday, Oct 3</span></span> </li>\n" +
                "   <li class=\"promotions_popup\"> \n" +
                "    <div class=\"promotions_popup\"> \n" +
                "     <ul class=\"a-color-base\"> \n" +
                "      <li>Blu-ray 3 for &pound;17</li> \n" +
                "     </ul> \n" +
                "     <div class=\"seeDetails\"> \n" +
                "      <a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/dp/B00838IG3O#productPromotions\">See product for more details</a>\n" +
                "     </div> \n" +
                "    </div> </li> \n" +
                "   <li>FREE Delivery on orders over &pound;10 and <span class=\"morePromotions\"><a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/dp/B00838IG3O#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-amazon.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li>\n" +
                "   <li class=\"sect mbc\">More buying choices - Blu-ray</li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B00838IG3O\"><span class=\"price bld\">&pound;4.49</span> new <span class=\"grey\">(36 offers)</span></a> </li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B00838IG3O\"><span class=\"price bld\">&pound;3.77</span> used <span class=\"grey\">(10 offers)</span></a> </li>\n" +
                "   <li class=\"rvw\"> <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00838IG3O&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00838IG3O\" ref=\"sr_cr_\" class=\"asinReviewsSummary\"> <a alt=\"4.3 out of 5 stars\" href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/product-reviews/B00838IG3O\"><span class=\"srSprite spr_stars4_5Active newStars\"> <span class=\"displayNone\"></span> </span> <span class=\"srSprite spr_chevron\"> <span class=\"displayNone\"></span> </span> </a></span> </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Raid-Blu-ray-Iko-Uwais/product-reviews/B00838IG3O\">278</a>)</span> </li> \n" +
                "  </ul> \n" +
                "  <br clear=\"all\" /> \n" +
                " </div> \n" +
                " <div id=\"result_2\" class=\"fstRowGrid prod celwidget\" name=\"B009934S5M\"> \n" +
                "  <div class=\"linePlaceholder\"></div>\n" +
                "  <div class=\"image imageContainer\"> \n" +
                "   <a href=\"http://www.amazon.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M\">\n" +
                "    <div class=\"imageBox\"> \n" +
                "     <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-amazon.com/images/I/51K7eJ6In1L._AA160_.jpg\" class=\"productImage cfMarker\" alt=\"Product Details\" /> \n" +
                "    </div></a>\n" +
                "  </div> \n" +
                "  <h3 class=\"newaps\"> <a href=\"http://www.amazon.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M\"><span class=\"lrg bold\">Star Trek Into Darkness (Blu-ray + Digital Copy) [Region Free]</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Chris Pine, Zachary Quinto, Karl Urban, et al.</span></span> </h3>\n" +
                "  <ul class=\"rsltGridList grey\"> \n" +
                "   <li class=\"newp\"> \n" +
                "    <div class=\"\"> \n" +
                "     <a href=\"http://www.amazon.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M\"><span class=\"bld lrg red\"> &pound;6.51</span> <span class=\"lrg\">Blu-ray</span> </a>\n" +
                "     <div class=\"imageBox\"> \n" +
                "      <img src=\"http://g-ecx.images-amazon.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\" height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\" align=\"baseline\" /> \n" +
                "     </div>\n" +
                "    </div> </li> \n" +
                "   <li> <span class=\"grey sml\"> Get it by <span class=\"bld grn nowrp\">Friday, Oct 3</span></span> </li>\n" +
                "   <li class=\"sss2\"> FREE Delivery on orders over &pound;10 </li>\n" +
                "   <li class=\"sect mbc\">More buying choices - Blu-ray</li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B009934S5M\"><span class=\"price bld\">&pound;4.99</span> new <span class=\"grey\">(39 offers)</span></a> </li> \n" +
                "   <li class=\"med grey mkp2\"> <a href=\"http://www.amazon.co.uk/gp/offer-listing/B009934S5M\"><span class=\"price bld\">&pound;4.19</span> used <span class=\"grey\">(7 offers)</span></a> </li>\n" +
                "   <li class=\"rvw\"> <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009934S5M&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009934S5M\" ref=\"sr_cr_\" class=\"asinReviewsSummary\"> <a alt=\"4.4 out of 5 stars\" href=\"http://www.amazon.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M\"><span class=\"srSprite spr_stars4_5Active newStars\"> <span class=\"displayNone\"></span> </span> <span class=\"srSprite spr_chevron\"> <span class=\"displayNone\"></span> </span> </a></span> </span><span class=\"rvwCnt\">(<a href=\"http://www.amazon.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M\">1,353</a>)</span> </li> \n" +
                "  </ul> \n" +
                "  <br clear=\"all\" /> \n" +
                " </div> ";
    }


}