package net.wazim.jordan.stub;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AmazonStub extends HttpServlet {

    private AmazonServlet servlet;
    private Server server;
    private final ServletContextHandler context;

    public static void main(String[] args) {
        AmazonStub amazonStub = new AmazonStub();
        amazonStub.createPageAndPrimeResponse("/amazon/bluray", 200, amazonStub.getSamplePage1());
        amazonStub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, amazonStub.getSamplePage2());
    }

    public AmazonStub() {
        server = new Server(11511);

        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        servlet = new AmazonServlet(200, "OK");
        context.addServlet(new ServletHolder(servlet), "/amazon");

        startServer();
    }

    private void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPageAndPrimeResponse(String path, int primedResponseCode, String primedResponseBody) {
        context.addServlet(new ServletHolder(new AmazonServlet(primedResponseCode, primedResponseBody)), path);
    }

    private static class AmazonServlet extends HttpServlet {

        private int responseCode;
        private String responseBody;

        public AmazonServlet(int responseCode, String responseBody) {
            this.responseCode = responseCode;
            this.responseBody = responseBody;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            resp.setStatus(responseCode);
            resp.getWriter().println(responseBody);
        }

    }

    private String getSamplePage1() {
        return "<head>\n" +
                "<title>x.co.uk: Under £8 - Blu-ray / Blu-ray: DVD &amp; Blu-ray</title>\n" +
                "\n" +
                "<meta name=\"description\" content=\"Online shopping from a great selection at DVD &amp; Blu-ray Store.\" />\n" +
                "<meta name=\"keywords\" content=\"Under £8 - Blu-ray / Blu-ray, DVD &amp; Blu-ray, x.co.uk\" />\n" +
                "<link rel=\"canonical\" href=\"http://www.x.co.uk/Blu-ray-Under-%C2%A38-DVD/s?ie=UTF8&amp;page=1&amp;rh=n%3A293962011%2Cp_36%3A-800%2Cp_n_binding_browse-bin%3A383380011\" />\n" +
                "<link rel=\"dns-prefetch\" href=\"ecx.images-x.com\" />\n" +
                "    <link rel=\"dns-prefetch\" href=\"g-ecx.images-x.com\" />\n" +
                "    <link rel=\"dns-prefetch\" href=\"z-ecx.images-x.com\" />\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_0\" class=\"fstRowGrid prod celwidget\" name=\"B008OGHUFK\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51AvOH64ZpL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><span class=\"lrg bold\">Dredd (Blu-ray 3D + Blu-ray)</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Karl Urban,&#32;Olivia Thirlby,&#32;Lena Headey, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><span class=\"bld lrg red\"> £5.95</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B008OGHUFK/ref=sr_1_1_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1&amp;condition=new\"><span class=\"price bld\">£4.51</span> new <span class=\"grey\">(45 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B008OGHUFK/ref=sr_1_1_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1&amp;condition=used\"><span class=\"price bld\">£3.93</span> used <span class=\"grey\">(6 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B008OGHUFK&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B008OGHUFK\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.5 out of 5 stars\" href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK/ref=sr_1_1_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK/ref=sr_1_1_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">1,132</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_1\" class=\"fstRowGrid prod celwidget\" name=\"B004BDOEZO\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510WI2HML9L._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><span class=\"lrg bold\">Transformers: Dark of the Moon [Blu-ray + DVD] [2011] [Region Free]</span></a> <span class=\"med reg\">(2001) <span class=\"starring\">Starring Shia LaBeouf,&#32;Josh Duhamel,&#32;Hugo Weaving and John Malkovich</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><span class=\"bld lrg red\"> £2.43</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 14 left in stock - order soon.</span></li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004BDOEZO/ref=sr_1_2_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2&amp;condition=new\"><span class=\"price bld\">£1.94</span> new <span class=\"grey\">(58 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004BDOEZO/ref=sr_1_2_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2&amp;condition=used\"><span class=\"price bld\">£1.24</span> used <span class=\"grey\">(14 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B004BDOEZO&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B004BDOEZO\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.9 out of 5 stars\" href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/product-reviews/B004BDOEZO/ref=sr_1_2_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/product-reviews/B004BDOEZO/ref=sr_1_2_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">408</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_2\" class=\"fstRowGrid prod celwidget\" name=\"B00AW9MB4W\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51twLxIAYuL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><span class=\"lrg bold\">Ender's Game [Blu-ray]</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Asa Butterfield,&#32;Abigail Breslin,&#32;Harrison Ford, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><span class=\"bld lrg red\"> £7.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 14 left in stock - order soon.</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9MB4W/ref=sr_1_3_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3&amp;condition=new\"><span class=\"price bld\">£6.36</span> new <span class=\"grey\">(32 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9MB4W/ref=sr_1_3_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3&amp;condition=used\"><span class=\"price bld\">£4.66</span> used <span class=\"grey\">(12 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00AW9MB4W&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00AW9MB4W\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.5 out of 5 stars\" href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/product-reviews/B00AW9MB4W/ref=sr_1_3_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-3-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/product-reviews/B00AW9MB4W/ref=sr_1_3_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">344</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_3\" class=\"rsltGrid prod celwidget\" name=\"B009VI63OE\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510VzB3YTBL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><span class=\"lrg bold\">Django Unchained (Blu-ray + UV Copy) [2013]</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Jamie Foxx,&#32;Leonardo DiCaprio,&#32;Christoph Waltz, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><span class=\"bld lrg red\"> £8.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 3 left in stock - order soon.</span></li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI63OE/ref=sr_1_4_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4&amp;condition=new\"><span class=\"price bld\">£7.02</span> new <span class=\"grey\">(34 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI63OE/ref=sr_1_4_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4&amp;condition=used\"><span class=\"price bld\">£5.64</span> used <span class=\"grey\">(8 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009VI63OE&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009VI63OE\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.4 out of 5 stars\" href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/product-reviews/B009VI63OE/ref=sr_1_4_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/product-reviews/B009VI63OE/ref=sr_1_4_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">591</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_4\" class=\"rsltGrid prod celwidget\" name=\"B004Q9T6CO\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51JFepwYTrL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><span class=\"lrg bold\">The Dark Knight Rises (Blu-ray + UV Copy) [2012] [Region Free]</span></a> <span class=\"med reg\">(2012) <span class=\"starring\">Starring Christian Bale,&#32;Tom Hardy,&#32;Anne Hathaway and Michael Caine</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><span class=\"bld lrg red\"> £7.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004Q9T6CO/ref=sr_1_5_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5&amp;condition=new\"><span class=\"price bld\">£5.72</span> new <span class=\"grey\">(55 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004Q9T6CO/ref=sr_1_5_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5&amp;condition=used\"><span class=\"price bld\">£3.49</span> used <span class=\"grey\">(12 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B004Q9T6CO&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B004Q9T6CO\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.2 out of 5 stars\" href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/product-reviews/B004Q9T6CO/ref=sr_1_5_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/product-reviews/B004Q9T6CO/ref=sr_1_5_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">980</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_5\" class=\"rsltGrid prod celwidget\" name=\"B00F8J5Q8E\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);if (typeof uet =='function') { uet('af'); if(window.amzn && amzn.sx.utils.jsDepMgr) {amzn.sx.utils.jsDepMgr.when('jQuery','ajax_ue_init_manageLoad', function($) { var u=$.searchUE;if(u) {u.manageLoad();}});} } if(window.amzn && amzn.sx.utils.jsDepMgr) {amzn.sx.utils.jsDepMgr.when('search-js-general', 'atf_event_trigger_rhs', function() {SPUtils.triggerATFEvent(1);});}\" src=\"http://ecx.images-x.com/images/I/41mlFvjGYVL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><span class=\"lrg bold\">Riddick [Blu-ray]</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Vin Diesel,&#32;Karl Urban and Katee Sackhoff</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><span class=\"bld lrg red\"> £8.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00F8J5Q8E/ref=sr_1_6_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6&amp;condition=new\"><span class=\"price bld\">£6.94</span> new <span class=\"grey\">(29 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00F8J5Q8E/ref=sr_1_6_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6&amp;condition=used\"><span class=\"price bld\">£4.90</span> used <span class=\"grey\">(7 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00F8J5Q8E&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00F8J5Q8E\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4 out of 5 stars\" href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/product-reviews/B00F8J5Q8E/ref=sr_1_6_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/product-reviews/B00F8J5Q8E/ref=sr_1_6_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">340</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "</div>\n" +
                "</div>\n" +
                "        \n" +
                "                                <div id=\"paRightContent\" class=\"rightCol\">\n" +
                "                                    </div>\n" +
                "\n" +
                "                                <div id=\"centerBelowPlus\" class='' >\n" +
                "                                    <div id=\"search-js-btf\">\n" +
                "\n" +
                "  <script type=\"text/javascript\">\n" +
                "      \n" +
                "      amzn.sx.utils.jsDepMgr.when('jQuery search-js-general breadcrumb', 'searchJsBtf', function($, searchComponents, breadcrumb) {\n" +
                "          \n" +
                "          SPUtils.triggerEvent(\"spFold\");\n" +
                "\n" +
                "          \n" +
                "          breadcrumb.breadcrumbSearch();\n" +
                "\n" +
                "          \n" +
                "          breadcrumb.editableBreadcrumb();\n" +
                "\n" +
                "          \n" +
                "          $(function () {searchComponents.hoverOverImageTriggersTitleHover();});\n" +
                "\n" +
                "          SPUtils.afterEvent(\"spATFEvent\", function () {\n" +
                "            \n" +
                "            registerAivHandler(\"atf\");\n" +
                "\n" +
                "              \n" +
                "              setFinancialValidation();\n" +
                "          });\n" +
                "\n" +
                "          \n" +
                "          if (typeof processBundlesFlyouts == 'function') {\n" +
                "              processBundlesFlyouts('atf');\n" +
                "          }\n" +
                "\n" +
                "          \n" +
                "                      amzn.sx.utils.jsDepMgr.when('popover', 'promotionPopover', function() {\n" +
                "                          processPromotionsPopover();\n" +
                "                      });\n" +
                "          \n" +
                "\n" +
                "      });\n" +
                "\n" +
                "      </script>\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"raw-search-js-btf\" class=\"searchUndoAUIHacks\">\n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   \n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"search-js-btf-external\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                "<script language=\"javascript\">var cloudfrontImg = new Image();if (location.protocol == \"http:\") {if (window.addEventListener) {window.addEventListener(\"load\", function() {setTimeout(function(){ cloudfrontImg.src = \"http://cloudfront-labs.xaws.com/x.png\"; }, 400);}, false);} else if (window.attachEvent) {window.attachEvent(\"onload\", function() {setTimeout(function(){ cloudfrontImg.src = \"http://cloudfront-labs.xaws.com/x.png\"; }, 400);});}} </script> \n" +
                "\n" +
                " \n" +
                " \n" +
                "\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div><div id=\"btfResults\" class=\"grid results  cols3\">\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_6\" class=\"rsltGrid prod celwidget\" name=\"B00AW9M8N6\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/61uq9ib4JvL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><span class=\"lrg bold\">Now You See Me [Blu-ray]</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Jesse Eisenberg,&#32;Mark Ruffalo,&#32;Isla Fisher, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><span class=\"bld lrg red\"> £6.40</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9M8N6/ref=sr_1_7_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7&amp;condition=new\"><span class=\"price bld\">£5.13</span> new <span class=\"grey\">(41 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9M8N6/ref=sr_1_7_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7&amp;condition=used\"><span class=\"price bld\">£4.14</span> used <span class=\"grey\">(5 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00AW9M8N6&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00AW9M8N6\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.1 out of 5 stars\" href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/product-reviews/B00AW9M8N6/ref=sr_1_7_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/product-reviews/B00AW9M8N6/ref=sr_1_7_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">431</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_7\" class=\"rsltGrid prod celwidget\" name=\"B009934S5M\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51K7eJ6In1L._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><span class=\"lrg bold\">Star Trek Into Darkness (Blu-ray + Digital Copy) [Region Free]</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Chris Pine,&#32;Zachary Quinto,&#32;Karl Urban, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><span class=\"bld lrg red\"> £7.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009934S5M/ref=sr_1_8_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8&amp;condition=new\"><span class=\"price bld\">£4.99</span> new <span class=\"grey\">(40 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009934S5M/ref=sr_1_8_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8&amp;condition=used\"><span class=\"price bld\">£3.99</span> used <span class=\"grey\">(10 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009934S5M&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009934S5M\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.4 out of 5 stars\" href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M/ref=sr_1_8_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M/ref=sr_1_8_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">1,369</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_8\" class=\"rsltGrid prod celwidget\" name=\"B00FGSJERG\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51w-ZJCnRRL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><span class=\"lrg bold\">The Long Goodbye [Blu-ray]</span></a> <span class=\"med reg\">(1973) <span class=\"starring\">Starring Elliott Gould,&#32;Nina van Pallandt and Sterling Hayden</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><span class=\"bld lrg red\"> £6.90</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"grey sml\">Not in stock; order now and we'll deliver when available</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00FGSJERG/ref=sr_1_9_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9&amp;condition=new\"><span class=\"price bld\">£5.36</span> new <span class=\"grey\">(33 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00FGSJERG/ref=sr_1_9_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9&amp;condition=used\"><span class=\"price bld\">£9.00</span> used <span class=\"grey\">(6 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00FGSJERG&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00FGSJERG\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.8 out of 5 stars\" href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/product-reviews/B00FGSJERG/ref=sr_1_9_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/product-reviews/B00FGSJERG/ref=sr_1_9_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">34</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_9\" class=\"rsltGrid prod celwidget\" name=\"B009VI68FS\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510lYKs8X%2BL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><span class=\"lrg bold\">White House Down [Blu-ray] [2013]</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Channing Tatum,&#32;Maggie Gyllenhaal and Jamie Foxx</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><span class=\"bld lrg red\"> £7.50</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 5 left in stock - order soon.</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI68FS/ref=sr_1_10_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10&amp;condition=new\"><span class=\"price bld\">£7.02</span> new <span class=\"grey\">(35 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI68FS/ref=sr_1_10_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10&amp;condition=used\"><span class=\"price bld\">£4.49</span> used <span class=\"grey\">(7 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009VI68FS&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009VI68FS\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.8 out of 5 stars\" href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/product-reviews/B009VI68FS/ref=sr_1_10_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/product-reviews/B009VI68FS/ref=sr_1_10_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">256</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div id=\"centerBelowMinus\">\n" +
                "<div class=\"img_header hdr noborder\" id=\"bottomBar\">\n" +
                "<div id=\"pagn\" class=\"pagnHy\" >\n" +
                "<span class=\"pagnLA1\"> <span class=\"srSprite firstPageLeftArrow\"></span>\n" +
                "<span id=\"pagnPrevString\">Previous Page</span></span>\n" +
                "<span class=\"pagnCur\">1</span>\n" +
                "<span class=\"pagnDisabled\">2</span>\n" +
                "<span class=\"pagnRA\"> <a title=\"Next Page\" id=\"pagnNextLink\" class=\"pagnNext\" href=\"no-link\">\n" +
                "<span id=\"pagnNextString\">Next Page</span>\n" +
                "<span class=\"srSprite pagnNextArrow\"></span>\n" +
                "</a></span>\n" +
                "<br clear=\"all\" />\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
    }

    private String getSamplePage2() {
        return "<head>\n" +
                "<title>x.co.uk: Under £8 - Blu-ray / Blu-ray: DVD &amp; Blu-ray</title>\n" +
                "\n" +
                "<meta name=\"description\" content=\"Online shopping from a great selection at DVD &amp; Blu-ray Store.\" />\n" +
                "<meta name=\"keywords\" content=\"Under £8 - Blu-ray / Blu-ray, DVD &amp; Blu-ray, x.co.uk\" />\n" +
                "<link rel=\"canonical\" href=\"http://www.x.co.uk/Blu-ray-Under-%C2%A38-DVD/s?ie=UTF8&amp;page=1&amp;rh=n%3A293962011%2Cp_36%3A-800%2Cp_n_binding_browse-bin%3A383380011\" />\n" +
                "<link rel=\"dns-prefetch\" href=\"ecx.images-x.com\" />\n" +
                "    <link rel=\"dns-prefetch\" href=\"g-ecx.images-x.com\" />\n" +
                "    <link rel=\"dns-prefetch\" href=\"z-ecx.images-x.com\" />\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_0\" class=\"fstRowGrid prod celwidget\" name=\"B008OGHUFK\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51AvOH64ZpL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><span class=\"lrg bold\">The Godfather (Blu-ray 3D + Blu-ray)</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Karl Urban,&#32;Olivia Thirlby,&#32;Lena Headey, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/dp/B008OGHUFK/ref=sr_1_1?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1\"><span class=\"bld lrg red\">£1.95</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B008OGHUFK/ref=sr_1_1_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1&amp;condition=new\"><span class=\"price bld\">£2.51</span> new <span class=\"grey\">(45 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B008OGHUFK/ref=sr_1_1_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-1&amp;condition=used\"><span class=\"price bld\">£1.93</span> used <span class=\"grey\">(6 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B008OGHUFK&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B008OGHUFK\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.5 out of 5 stars\" href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK/ref=sr_1_1_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Dredd-Blu-ray-3D-Karl-Urban/product-reviews/B008OGHUFK/ref=sr_1_1_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">1,132</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_1\" class=\"fstRowGrid prod celwidget\" name=\"B004BDOEZO\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510WI2HML9L._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><span class=\"lrg bold\">James Blunt Live</span></a> <span class=\"med reg\">(2001) <span class=\"starring\">Starring Shia LaBeouf,&#32;Josh Duhamel,&#32;Hugo Weaving and John Malkovich</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/dp/B004BDOEZO/ref=sr_1_2?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2\"><span class=\"bld lrg red\">£1.43</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 14 left in stock - order soon.</span></li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004BDOEZO/ref=sr_1_2_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2&amp;condition=new\"><span class=\"price bld\">£4.94</span> new <span class=\"grey\">(58 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004BDOEZO/ref=sr_1_2_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-2&amp;condition=used\"><span class=\"price bld\">£2.26</span> used <span class=\"grey\">(14 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B004BDOEZO&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B004BDOEZO\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.9 out of 5 stars\" href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/product-reviews/B004BDOEZO/ref=sr_1_2_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Transformers-Dark-Moon-Blu-ray-Region/product-reviews/B004BDOEZO/ref=sr_1_2_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">408</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_2\" class=\"fstRowGrid prod celwidget\" name=\"B00AW9MB4W\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51twLxIAYuL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><span class=\"lrg bold\">Michael Jackson's Moonwalker</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Asa Butterfield,&#32;Abigail Breslin,&#32;Harrison Ford, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3\"><span class=\"bld lrg red\">£3.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 14 left in stock - order soon.</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/dp/B00AW9MB4W/ref=sr_1_3_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9MB4W/ref=sr_1_3_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3&amp;condition=new\"><span class=\"price bld\">£1.36</span> new <span class=\"grey\">(32 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9MB4W/ref=sr_1_3_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-3&amp;condition=used\"><span class=\"price bld\">£7.66</span> used <span class=\"grey\">(12 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00AW9MB4W&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00AW9MB4W\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.5 out of 5 stars\" href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/product-reviews/B00AW9MB4W/ref=sr_1_3_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-3-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Enders-Game-Blu-ray-Asa-Butterfield/product-reviews/B00AW9MB4W/ref=sr_1_3_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">344</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_3\" class=\"rsltGrid prod celwidget\" name=\"B009VI63OE\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510VzB3YTBL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><span class=\"lrg bold\">Pulp Fiction</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Jamie Foxx,&#32;Leonardo DiCaprio,&#32;Christoph Waltz, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/dp/B009VI63OE/ref=sr_1_4?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4\"><span class=\"bld lrg red\">£10.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 3 left in stock - order soon.</span></li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI63OE/ref=sr_1_4_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4&amp;condition=new\"><span class=\"price bld\">£2.02</span> new <span class=\"grey\">(34 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI63OE/ref=sr_1_4_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-4&amp;condition=used\"><span class=\"price bld\">£2.64</span> used <span class=\"grey\">(8 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009VI63OE&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009VI63OE\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.4 out of 5 stars\" href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/product-reviews/B009VI63OE/ref=sr_1_4_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Django-Unchained-Blu-ray-UV-Copy/product-reviews/B009VI63OE/ref=sr_1_4_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">591</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_4\" class=\"rsltGrid prod celwidget\" name=\"B004Q9T6CO\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51JFepwYTrL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><span class=\"lrg bold\">Ghost</span></a> <span class=\"med reg\">(2012) <span class=\"starring\">Starring Christian Bale,&#32;Tom Hardy,&#32;Anne Hathaway and Michael Caine</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5\"><span class=\"bld lrg red\">£1.01</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/dp/B004Q9T6CO/ref=sr_1_5_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004Q9T6CO/ref=sr_1_5_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5&amp;condition=new\"><span class=\"price bld\">£2.72</span> new <span class=\"grey\">(55 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B004Q9T6CO/ref=sr_1_5_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-5&amp;condition=used\"><span class=\"price bld\">£2.49</span> used <span class=\"grey\">(12 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B004Q9T6CO&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B004Q9T6CO\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.2 out of 5 stars\" href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/product-reviews/B004Q9T6CO/ref=sr_1_5_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Dark-Knight-Rises-Blu-ray-Region/product-reviews/B004Q9T6CO/ref=sr_1_5_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">980</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_5\" class=\"rsltGrid prod celwidget\" name=\"B00F8J5Q8E\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);if (typeof uet =='function') { uet('af'); if(window.amzn && amzn.sx.utils.jsDepMgr) {amzn.sx.utils.jsDepMgr.when('jQuery','ajax_ue_init_manageLoad', function($) { var u=$.searchUE;if(u) {u.manageLoad();}});} } if(window.amzn && amzn.sx.utils.jsDepMgr) {amzn.sx.utils.jsDepMgr.when('search-js-general', 'atf_event_trigger_rhs', function() {SPUtils.triggerATFEvent(1);});}\" src=\"http://ecx.images-x.com/images/I/41mlFvjGYVL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><span class=\"lrg bold\">Fast and Furious</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Vin Diesel,&#32;Karl Urban and Katee Sackhoff</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6\"><span class=\"bld lrg red\">£0.90</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/dp/B00F8J5Q8E/ref=sr_1_6_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00F8J5Q8E/ref=sr_1_6_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6&amp;condition=new\"><span class=\"price bld\">£4.94</span> new <span class=\"grey\">(29 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00F8J5Q8E/ref=sr_1_6_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-6&amp;condition=used\"><span class=\"price bld\">£2.90</span> used <span class=\"grey\">(7 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00F8J5Q8E&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00F8J5Q8E\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4 out of 5 stars\" href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/product-reviews/B00F8J5Q8E/ref=sr_1_6_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Riddick-Blu-ray-Vin-Diesel/product-reviews/B00F8J5Q8E/ref=sr_1_6_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">340</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "</div>\n" +
                "</div>\n" +
                "        \n" +
                "                                <div id=\"paRightContent\" class=\"rightCol\">\n" +
                "                                    </div>\n" +
                "\n" +
                "                                <div id=\"centerBelowPlus\" class='' >\n" +
                "                                    <div id=\"search-js-btf\">\n" +
                "\n" +
                "  <script type=\"text/javascript\">\n" +
                "      \n" +
                "      amzn.sx.utils.jsDepMgr.when('jQuery search-js-general breadcrumb', 'searchJsBtf', function($, searchComponents, breadcrumb) {\n" +
                "          \n" +
                "          SPUtils.triggerEvent(\"spFold\");\n" +
                "\n" +
                "          \n" +
                "          breadcrumb.breadcrumbSearch();\n" +
                "\n" +
                "          \n" +
                "          breadcrumb.editableBreadcrumb();\n" +
                "\n" +
                "          \n" +
                "          $(function () {searchComponents.hoverOverImageTriggersTitleHover();});\n" +
                "\n" +
                "          SPUtils.afterEvent(\"spATFEvent\", function () {\n" +
                "            \n" +
                "            registerAivHandler(\"atf\");\n" +
                "\n" +
                "              \n" +
                "              setFinancialValidation();\n" +
                "          });\n" +
                "\n" +
                "          \n" +
                "          if (typeof processBundlesFlyouts == 'function') {\n" +
                "              processBundlesFlyouts('atf');\n" +
                "          }\n" +
                "\n" +
                "          \n" +
                "                      amzn.sx.utils.jsDepMgr.when('popover', 'promotionPopover', function() {\n" +
                "                          processPromotionsPopover();\n" +
                "                      });\n" +
                "          \n" +
                "\n" +
                "      });\n" +
                "\n" +
                "      </script>\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"raw-search-js-btf\" class=\"searchUndoAUIHacks\">\n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   \n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"search-js-btf-external\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                "<script language=\"javascript\">var cloudfrontImg = new Image();if (location.protocol == \"http:\") {if (window.addEventListener) {window.addEventListener(\"load\", function() {setTimeout(function(){ cloudfrontImg.src = \"http://cloudfront-labs.xaws.com/x.png\"; }, 400);}, false);} else if (window.attachEvent) {window.attachEvent(\"onload\", function() {setTimeout(function(){ cloudfrontImg.src = \"http://cloudfront-labs.xaws.com/x.png\"; }, 400);});}} </script> \n" +
                "\n" +
                " \n" +
                " \n" +
                "\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div><div id=\"btfResults\" class=\"grid results  cols3\">\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_6\" class=\"rsltGrid prod celwidget\" name=\"B00AW9M8N6\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/61uq9ib4JvL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><span class=\"lrg bold\">Saving Mr Banks</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Jesse Eisenberg,&#32;Mark Ruffalo,&#32;Isla Fisher, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7\"><span class=\"bld lrg red\">£2.40</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/dp/B00AW9M8N6/ref=sr_1_7_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9M8N6/ref=sr_1_7_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7&amp;condition=new\"><span class=\"price bld\">£1.13</span> new <span class=\"grey\">(41 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00AW9M8N6/ref=sr_1_7_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-7&amp;condition=used\"><span class=\"price bld\">£6.14</span> used <span class=\"grey\">(5 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00AW9M8N6&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00AW9M8N6\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.1 out of 5 stars\" href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/product-reviews/B00AW9M8N6/ref=sr_1_7_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Now-You-See-Me-Blu-ray/product-reviews/B00AW9M8N6/ref=sr_1_7_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">431</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_7\" class=\"rsltGrid prod celwidget\" name=\"B009934S5M\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51K7eJ6In1L._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><span class=\"lrg bold\">Star Wars Episode 1</span></a> <span class=\"med reg\">(2014) <span class=\"starring\">Starring Chris Pine,&#32;Zachary Quinto,&#32;Karl Urban, et al.</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/dp/B009934S5M/ref=sr_1_8?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8\"><span class=\"bld lrg red\">£2.00</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li>\n" +
                "        <span class=\"grey sml\">\n" +
                "          Get it by <span class='bld grn nowrp'>Thursday, Oct 9</span></span>\n" +
                "    </li><li class=\"sss2\">\n" +
                "    FREE Delivery on orders over £10</span>\n" +
                "</li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009934S5M/ref=sr_1_8_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8&amp;condition=new\"><span class=\"price bld\">£1.99</span> new <span class=\"grey\">(40 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009934S5M/ref=sr_1_8_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-8&amp;condition=used\"><span class=\"price bld\">£1.99</span> used <span class=\"grey\">(10 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009934S5M&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009934S5M\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"4.4 out of 5 stars\" href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M/ref=sr_1_8_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4-5\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Star-Darkness-Blu-ray-Digital-Region/product-reviews/B009934S5M/ref=sr_1_8_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">1,369</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"result_8\" class=\"rsltGrid prod celwidget\" name=\"B00FGSJERG\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/51w-ZJCnRRL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><span class=\"lrg bold\">The Short Goodbye [Blu-ray]</span></a> <span class=\"med reg\">(1973) <span class=\"starring\">Starring Elliott Gould,&#32;Nina van Pallandt and Sterling Hayden</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9\"><span class=\"bld lrg red\">£1.90</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"grey sml\">Not in stock; order now and we'll deliver when available</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/dp/B00FGSJERG/ref=sr_1_9_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00FGSJERG/ref=sr_1_9_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9&amp;condition=new\"><span class=\"price bld\">£2.36</span> new <span class=\"grey\">(33 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B00FGSJERG/ref=sr_1_9_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-9&amp;condition=used\"><span class=\"price bld\">£3.00</span> used <span class=\"grey\">(6 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B00FGSJERG&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B00FGSJERG\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.8 out of 5 stars\" href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/product-reviews/B00FGSJERG/ref=sr_1_9_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/Long-Goodbye-Blu-ray-Elliott-Gould/product-reviews/B00FGSJERG/ref=sr_1_9_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">34</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "<br class=\"unfloat\" />\n" +
                "<div class=\"rowDividerGrid entireRowGrid\"></div>\n" +
                "    <div id=\"result_9\" class=\"rsltGrid prod celwidget\" name=\"B009VI68FS\">\n" +
                "    <div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">\n" +
                "        <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><div class=\"imageBox\">\n" +
                "    <img onload=\"viewCompleteImageLoaded(this, new Date().getTime(), 24, false);\" src=\"http://ecx.images-x.com/images/I/510lYKs8X%2BL._AA160_.jpg\"  class=\"productImage cfMarker\" alt=\"Product Details\" />\n" +
                "        </div></a></div>\n" +
                "<h3 class=\"newaps\">\n" +
                "    <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><span class=\"lrg bold\">Magic Mike</span></a> <span class=\"med reg\">(2013) <span class=\"starring\">Starring Channing Tatum,&#32;Maggie Gyllenhaal and Jamie Foxx</span></span>\n" +
                "        </h3><ul class=\"rsltGridList grey\">\n" +
                "    \n" +
                "    <li class=\"newp\">\n" +
                "    <div class=\"\">\n" +
                "    <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10\"><span class=\"bld lrg red\">£2.50</span>\n" +
                "    <span class=\"lrg\">Blu-ray</span>\n" +
                "                  </a><div class=\"imageBox\">\n" +
                "    <img  src=\"http://g-ecx.images-x.com/images/G/02/nav2/images/gui/prime-check-badge-14._CB370663031_.gif\"  height=\"14\" width=\"45\" class=\"prmImg cfMarker\" alt=\"prime\"  align=\"baseline\"/>\n" +
                "        </div></div>\n" +
                "    </li>\n" +
                " <li><span class=\"red sml\">Only 5 left in stock - order soon.</span></li><li class=\"promotions_popup\"> <div class=\"promotions_popup\"> <ul class=\"a-color-base\">\n" +
                "\t            <li>Blu-ray 3 for £17</li>\n" +
                "\t                       </ul>\n" +
                "          <div class=\"seeDetails\" >\n" +
                "                  <a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10#productPromotions\">See product for more details</a></div>\n" +
                "          </div>\n" +
                "    </li>\n" +
                "<li>FREE Delivery on orders over £10</span> and <span class=\"morePromotions\"><a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/dp/B009VI68FS/ref=sr_1_10_det?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10#productPromotions\">1 more promotion</a> <img src=\"http://g-ecx.images-x.com/images/G/02/x-locale/common/transparent-pixel._CB384789948_.gif\" class=\"srSprite spr_arrow\" /></span></li><li class=\"sect mbc\">More buying choices - Blu-ray</li>\n" +
                "            <li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI68FS/ref=sr_1_10_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10&amp;condition=new\"><span class=\"price bld\">£1.02</span> new <span class=\"grey\">(35 offers)</span></a> </li>\n" +
                "<li class=\"med grey mkp2\">\n" +
                "        <a href=\"http://www.x.co.uk/gp/offer-listing/B009VI68FS/ref=sr_1_10_olp?s=dvd&amp;ie=UTF8&amp;qid=1412710559&amp;sr=1-10&amp;condition=used\"><span class=\"price bld\">£2.49</span> used <span class=\"grey\">(7 offers)</span></a> </li><li class=\"rvw\">\n" +
                "    <span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/review/widgets/average-customer-review/popover/ref=acr_search__popover?ie=UTF8&amp;asin=B009VI68FS&amp;contextId=search&amp;ref=acr_search__popover&quot;}\"><span name=\"B009VI68FS\" ref=\"sr_cr_\" class=\"asinReviewsSummary\">\n" +
                "                        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\"><a alt=\"3.8 out of 5 stars\" href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/product-reviews/B009VI68FS/ref=sr_1_10_cm_cr_acr_img?ie=UTF8&amp;showViewpoints=1\"><i class=\"a-icon a-icon-star a-star-4\"></i></a><i class=\"a-icon a-icon-popover\"></i></a></span>\n" +
                "                </span><span class=\"rvwCnt\">(<a href=\"http://www.x.co.uk/White-House-Blu-ray-Channing-Tatum/product-reviews/B009VI68FS/ref=sr_1_10_cm_cr_acr_txt?ie=UTF8&amp;showViewpoints=1\">256</a>)</span>\n" +
                "            </li>\n" +
                "    </ul>\n" +
                "  \n" +
                "<br clear=\"all\">\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +

                "</body>\n" +
                "</html>\n" +
                "\n";
    }

}
