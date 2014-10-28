package net.wazim.jordan.stub;

public class AmazonIndividualPageBuilder {

    private String blurayName;
    private double usedPrice;
    private double newPrice;

    public AmazonIndividualPageBuilder withName(String blurayName) {
        this.blurayName = blurayName;
        return this;
    }

    public AmazonIndividualPageBuilder withUsedPrice(double usedPrice) {
        this.usedPrice = usedPrice;
        return this;
    }

    public AmazonIndividualPageBuilder withNewPrice(double newPrice) {
        this.newPrice = newPrice;
        return this;
    }

    public String build() {
        return "<div id=\"centerCol\" class=\"centerColAlign\">        \n" +
                "    <div id=\"title_feature_div\" class=\"feature\" data-feature-name=\"title\">\n" +
                "<div class=\"a-section a-spacing-none\">\n" +
                "    <h1 id=\"title\" class=\"a-size-large a-spacing-none\">\n" +
                "      <span id=\"productTitle\" class=\"a-size-large\">"+blurayName+"</span>\n" +
                "    </h1>\n" +
                "</div>\n" +
                "    </div>\n" +
                "    <div id=\"qpeTitleTag_feature_div\" class=\"feature\" data-feature-name=\"qpeTitleTag\">\n" +
                "    </div>\n" +
                "    <div id=\"byline_feature_div\" class=\"feature\" data-feature-name=\"byline\">\n" +
                "<div id=\"byline\" class=\"a-section a-spacing-micro bylineHidden feature\">\n" +
                "\t        <span class=\"author notFaded\" data-width=\"279\">\n" +
                "\t\t\t\t<a class=\"a-link-normal\" href=\"/s/ref=dp_byline_sr_dvd_1?ie=UTF8&amp;field-keywords=Leonardo+DiCaprio&amp;search-alias=dvd\">Leonardo DiCaprio</a> \n" +
                "       \t\t<span class=\"contribution\" spacing=\"none\">\n" +
                "       \t\t\t<span class=\"a-color-secondary\">(Actor, Primary Contributor), </span>\n" +
                "       \t\t</span>\n" +
                "       \t\n" +
                "    \t</span>\n" +
                "\t\t\t<span class=\"author notFaded\" data-width=\"234\">\n" +
                "\t\t\t\t<a class=\"a-link-normal\" href=\"/s/ref=dp_byline_sr_dvd_2?ie=UTF8&amp;field-keywords=Ellen+Page&amp;search-alias=dvd\">Ellen Page</a> \n" +
                "       \t \n" +
                "       \t\t<span class=\"contribution\" spacing=\"none\">\n" +
                "       \t\t\t<span class=\"a-color-secondary\">(Actor, Primary Contributor), </span>\n" +
                "       \t\t</span>\n" +
                "       \t\n" +
                "    \t</span>\n" +
                "\t\t\t<span class=\"author notFaded\" data-width=\"167\">\n" +
                "\t\t\t\t<a class=\"a-link-normal\" href=\"/s/ref=dp_byline_sr_dvd_6?ie=UTF8&amp;field-keywords=Christopher+Nolan&amp;search-alias=dvd\">Christopher Nolan</a> \n" +
                "       \t\t<span class=\"contribution\" spacing=\"none\">\n" +
                "       \t\t\t<span class=\"a-color-secondary\">(Director)</span>\n" +
                "       \t\t</span>\n" +
                "       \t\n" +
                "    \t</span>\n" +
                "\t     <span data-width=\"52\" class=\"more\" style=\"display: none;\">\n" +
                "             <a class=\"a-link-normal showMoreLink\" href=\"#\">&amp; \n" +
                "                 <span class=\"moreCount\">0</span>\n" +
                "                 more</a>\n" +
                "         </span>\n" +
                "\t            <i class=\"a-icon a-icon-text-separator\"></i>\n" +
                "\t        \n" +
                "\t        <span class=\"a-color-secondary\">Rated: </span><span>Suitable for 12 years and over</span>\n" +
                "\t            <i class=\"a-icon a-icon-text-separator\"></i>\n" +
                "\t        \n" +
                "\t        <span class=\"a-color-secondary\">Format: </span><span>Blu-ray</span>\n" +
                "</div> \n" +
                "    </div>\n" +
                "    <div id=\"averageCustomerReviews_feature_div\" class=\"feature\" data-feature-name=\"averageCustomerReviews\">\n" +
                "        <div id=\"averageCustomerReviews\" class=\"a-spacing-none\" data-asin=\"B003NE4V3C\" data-ref=\"dpx_acr_pop_\">\n" +
                "\t    <span id=\"acrPopover\" class=\"reviewCountTextLinkedHistogram noUnderline\" title=\"4 out of 5 stars\">\n" +
                "\t    \t<span class=\"a-declarative\" data-action=\"a-popover\" data-a-popover=\"{&quot;position&quot;:&quot;triggerBottom&quot;,&quot;max-width&quot;:&quot;700&quot;,&quot;closeButton&quot;:&quot;false&quot;,&quot;url&quot;:&quot;/gp/customer-reviews/widgets/average-customer-review/popover/ref=dpx_acr_pop_?contextId=dpx&amp;asin=B003NE4V3C&quot;}\">\n" +
                "\t\t        <a href=\"javascript:void(0)\" class=\"a-popover-trigger a-declarative\">\n" +
                "\t\t            <i class=\"a-icon a-icon-star a-star-4\"></i>\n" +
                "\t\t        <i class=\"a-icon a-icon-popover\"></i></a>\n" +
                "\t        </span>\n" +
                "\t        <span class=\"a-letter-space\"></span>\n" +
                "\t    </span>\n" +
                "        <a id=\"acrCustomerReviewLink\" class=\"a-link-normal\" href=\"#customerReviews\">\n" +
                "\t\t\t<span id=\"acrCustomerReviewText\" class=\"a-size-base\">756 customer reviews</span>\n" +
                "\t\t</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"zeitgeistBadge_feature_div\" class=\"feature\" data-feature-name=\"zeitgeistBadge\">\n" +
                "    </div>\n" +
                "    <div id=\"price_feature_div\" class=\"feature\" data-feature-name=\"price\">\n" +
                "<div id=\"price\" class=\"a-section a-spacing-small\">\n" +
                "<table class=\"a-lineitem\">\n" +
                "<tbody><tr>\n" +
                "    <td class=\"a-color-secondary a-size-base a-text-right a-nowrap\">Price:</td>\n" +
                "    <td class=\"a-span12\">\n" +
                "        <span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">£"+newPrice+"</span>\n" +
                "        <span id=\"ourprice_shippingmessage\">\t\n" +
                "    <span class=\"a-size-base a-color-base\"><!-- MsgId:cfs_free_shipping_eligible_inline:web -->\n" +
                "&amp; <b>FREE Delivery</b> in the UK on orders over £10. <a href=\"/gp/help/customer/display.html/ref=mk_sss_dp_1?ie=UTF8&amp;pop-up=1&amp;nodeId=200039400\" target=\"AmazonHelp\" onclick=\"return amz_js_PopWin(this.href,'AmazonHelp','width=550,height=550,resizable=1,scrollbars=1,toolbar=0,status=0');\">Details</a>\n" +
                "</span>\n" +
                "        </span>\n" +
                "    </td>\n" +
                "</tr>\n" +

                "</tbody></table>\n" +
                "</div>\n" +
                "    </div>\n" +
                "    <div id=\"primenote_feature_div\" class=\"feature\" data-feature-name=\"primenote\">\n" +
                "    </div>\n" +
                "    <div id=\"availability_feature_div\" class=\"feature\" data-feature-name=\"availability\">\n" +
                "<div id=\"availability\" class=\"a-section a-spacing-none\">\n" +
                "    <span class=\"a-size-medium a-color-success\">\n" +
                "            Only 11 left in stock.\n" +
                "    </span>\n" +
                "</div>\n" +
                "<div id=\"merchant-info\" class=\"a-section a-spacing-mini\">\n" +
                "    Dispatched from and sold by Amazon.\n" +
                "    Gift-wrap available.\n" +
                "</div>\n" +
                "    </div>\n" +
                "    <div id=\"dynamicDeliveryMessage_feature_div\" class=\"feature\" data-feature-name=\"dynamicDeliveryMessage\">\n" +
                "    <div id=\"dynamicDeliveryMessage\" class=\"a-section a-spacing-mini a-spacing-top-mini\">\n" +
                "            <div id=\"ddmDeliveryMessage\" class=\"a-section a-spacing-mini\">\n" +
                "                \n" +
                "                <div id=\"ddmShippingMessage\" class=\"a-section\">\n" +
                "                    \n" +
                "                        \n" +
                "                            <b>Want it tomorrow, 29 Oct.?</b> Order it within <span id=\"ddmFastestCountdown\" class=\"a-color-success a-text-bold\">1 hr</span> and choose <b>One-Day Delivery</b> at checkout. <a href=\"/gp/help/customer/display.html/ref=ddm_ft_dp?ie=UTF8&amp;nodeId=200275230&amp;pop-up=1#\" target=\"AmazonHelp\" onclick=\"return amz_js_PopWin('/gp/help/customer/display.html/ref=ddm_ft_dp?ie=UTF8&amp;nodeId=200275230&amp;pop-up=1#','AmazonHelp','width=550,height=600,resizable=1,scrollbars=1,toolbar=1,status=1');\">Details</a>\n" +
                "                        \n" +
                "                        \n" +
                "                    \n" +
                "                </div>\n" +
                "            </div>\n" +
                "    </div>\n" +
                "    </div>\n" +
                "    <div id=\"holidayAvailabilityMessage_feature_div\" class=\"feature\" data-feature-name=\"holidayAvailabilityMessage\">\n" +
                "    </div>\n" +
                "    <div id=\"addOnItem_feature_div\" class=\"feature\" data-feature-name=\"addOnItem\">\n" +
                "    </div>\n" +
                "    <div id=\"andonCord_feature_div\" class=\"feature\" data-feature-name=\"andonCord\">\n" +
                "    </div>\n" +
                "    <div id=\"olp_feature_div\" class=\"feature\" data-feature-name=\"olp\">\n" +
                "    <div class=\"a-section a-spacing-small a-spacing-top-small\">\n" +
                "        \n" +
                "            <span class=\"olp-padding-right\"><a href=\"/gp/offer-listing/B003NE4V3C/ref=dp_olp_new?ie=UTF8&amp;condition=new\">18&nbsp;new</a>&nbsp;from&nbsp;<span class=\"a-color-price\">£"+newPrice+"</span></span>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "            <span class=\"olp-padding-right\"><a href=\"/gp/offer-listing/B003NE4V3C/ref=dp_olp_used?ie=UTF8&amp;condition=used\">17&nbsp;used</a>&nbsp;from&nbsp;<span class=\"a-color-price\">£"+usedPrice+"</span></span>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "            <span class=\"olp-padding-right\"><a href=\"/gp/offer-listing/B003NE4V3C/ref=dp_olp_collectible?ie=UTF8&amp;condition=collectible\">1&nbsp;collectible</a>&nbsp;from&nbsp;<span class=\"a-color-price\">£12.00</span></span>\n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "    </div>\n" +
                "    </div>\n" +
                "    <div id=\"originalPackagingMessage_feature_div\" class=\"feature\" data-feature-name=\"originalPackagingMessage\">\n" +
                "    <div id=\"originalPackagingMessage\" class=\"a-section a-spacing-small\">\n" +
                "        \n" +
                "        \n" +
                "    </div>\n" +
                "    </div>\n" +
                "    <div id=\"newerVersion_feature_div\" class=\"feature\" data-feature-name=\"newerVersion\">\n" +
                "    </div>\n" +
                "    <div id=\"dvdRentalBadge_feature_div\" class=\"feature\" data-feature-name=\"dvdRentalBadge\">\n" +
                "<style type=\"text/css\"><!--\n" +
                "div.dv-cross-linking *{-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}div.dv-cross-linking a.dv-watch-now{position:relative;padding-left:0}div.dv-cross-linking a.dv-watch-now span.dv-watch-now-icon{position:absolute;background-image:url(http://z-ecx.images-amazon.com/images/G/01/digital/video/global/aiv-sprite._V341564437_.png);background-position:0 -322px;width:19px;height:19px}div.dv-cross-linking a.dv-watch-now:hover span.dv-watch-now-icon{background-position:-24px -322px;cursor:pointer}div.dv-cross-linking a.dv-watch-now span.dv-watch-now-text{margin-left:25px;margin-top:2px}div.dv-cross-linking div.dv-cross-linking-offer{padding-top:7px;padding-bottom:7px}div.dv-cross-linking h4{margin-top:0;margin-bottom:0;font-size:13px}div.dv-cross-linking-limited-width h4{float:left;margin:0 5px}div.dv-cross-linking.dv-cross-linking-aiv h4{background-image:url(http://z-ecx.images-amazon.com/images/G/01/digital/video/global/amazon-instant-video._V339898279_.png);background-repeat:no-repeat;width:118px;height:31px;text-indent:-9999em;margin-top:7px}div.dv-cross-linking.dv-cross-linking-prime h4{background-image:url(http://z-ecx.images-amazon.com/images/G/01/digital/video/global/prime-instant-video._V339898249_.png);background-repeat:no-repeat;width:150px;height:31px;text-indent:-9999em;margin-top:7px}div.dv-cross-linking-limited-width{padding-left:315px}div.dv-cross-linking.dv-cross-linking-aiv h4 a{display:block;width:118px;height:31px}div.dv-cross-linking.dv-cross-linking-prime h4 a{display:block;width:150px;height:31px}div.dv-cross-linking a.dv-watch-now{font-size:16px}div.dv-cross-linking div.dv-cross-linking-offer span.strong{white-space:nowrap}div.dv-cross-linking a.a-link-child:before{color:#e47911}div.dv-cross-linking a.dv-watch-now.a-link-child:before{content:'';display:none}div.dv-cross-linking.dv-cross-linking-dark{font-size:13px}div.dv-cross-linking.dv-cross-linking-dark div.dv-cross-linking-offer span.price{font-family:arial,sans-serif;color:#999}div.dv-cross-linking.dv-cross-linking-dark div.dv-cross-linking-offer span.strong{font-weight:700}div.dv-cross-linking.dv-cross-linking-light{font-size:13px}div.dv-cross-linking.dv-cross-linking-light div.dv-cross-linking-offer{border-top:1px #d8dbdf solid}div.dv-cross-linking.dv-cross-linking-light div.dv-cross-linking-offer-first{border-top:0 #d8dbdf none}div.dv-cross-linking.dv-cross-linking-light div.dv-cross-linking-offer-link{margin-top:6px;margin-bottom:3px}div.dv-cross-linking-other-formats a span.dv-watch-now-icon{display:block;position:absolute;background-image:url(http://z-ecx.images-amazon.com/images/G/01/digital/video/global/aiv-sprite._V341564437_.png);background-position:0 -322px;width:19px;height:19px;top:22px;left:9px}div.dv-cross-linking-other-formats a:hover span.dv-watch-now-icon{background-position:-24px -322px;cursor:pointer}div.dv-cross-linking-other-formats a.dv-watch-now span.a-color-secondary{padding-left:23px;padding-top:3px}div.dv-cross-linking-other-formats a.a-button.a-button-toggle div.a-button-inner{min-height:48px}\n" +
                "--></style>\n" +
                "\n" +
                "\n" +
                "<div class=\"cBox grayBox dv-cross-linking dv-cross-linking-light dv-cross-linking-aiv dv-cross-linking-prime\">\n" +
                "    <span class=\"cBoxTL\"><!-- &nbsp; --></span>\n" +
                "    <span class=\"cBoxTR\"><!-- &nbsp; --></span>\n" +
                "    <span class=\"cBoxR\"><!-- &nbsp; --></span>\n" +
                "    <span class=\"cBoxBL\"><!-- &nbsp; --></span>\n" +
                "    <span class=\"cBoxBR\"><!-- &nbsp; --></span>\n" +
                "    <span class=\"cBoxB\"><!-- &nbsp; --></span>\n" +
                "\n" +
                "    <div class=\"cBoxInner\">  \n" +
                "        <h4><a href=\"/gp/browse/ref=dp_rt_cl_0?ie=UTF8&amp;node=3280626031\">Prime Instant Video</a></h4>\n" +
                "        <div class=\"dv-cross-linking-offer dv-cross-linking-offer-first\">\n" +
                "            Watch <a href=\"/gp/product/B00G3ED8DI/ref=dp_rt_cl_dp_0\">"+blurayName+"</a> instantly for <span class=\"price\">£0.00</span> with <span class=\"strong\">Prime Instant Video</span>\n" +
                "                <div class=\"dv-cross-linking-offer-link\">\n" +
                "                        <strong class=\"hqpBold\">›&nbsp;</strong>\n" +
                "                    <a href=\"/gp/video/signup/ref=dp_rt_cl_pft?ie=UTF8&amp;primeCampaignId=videos&amp;redirectURL=L2dwL3Byb2R1Y3QvQjAwRzNFRDhESS9yZWY9ZHBfcnRfY2xfc2lnbl9zdWM%3D%0A&amp;return_url=L2dwL3Byb2R1Y3QvQjAwRzNFRDhESS9yZWY9ZHBfcnRfY2xfc2lnbl9zdWM%3D%0A&amp;session-id=277-8374346-6039225\" class=\"\">\n" +
                "                            Start your 30-day free trial\n" +
                "                    </a>\n" +
                "                </div>\n" +
                "        </div>\n" +
                "        <div class=\"dv-cross-linking-offer \">\n" +
                "            Also available to <a href=\"/gp/product/B00EROXFAC/ref=dp_rt_cl_dp_1\">rent on Blu-ray</a> from <a href=\"/gp/browse/ref=dp_rt_cl_1?ie=UTF8&amp;node=3054240031\">LOVEFiLM By Post</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "    <div id=\"productAlert_feature_div\" class=\"feature\" data-feature-name=\"productAlert\">\n" +
                "    </div>\n" +
                "</div>";
    }


}
