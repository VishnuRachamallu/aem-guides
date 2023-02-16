package apps.weretail.components.content.demo;

import com.adobe.cq.sightly.WCMUsePojo;

public class Demo extends WCMUsePojo {
    private String title1;
    private String title2;
    private int title3;

    @Override
    public void activate() throws Exception {
       title1 = getProperties().get("title1", "").toLowerCase();
       title2 = getProperties().get("title2", "").toLowerCase();
    }

    public int getTitle3() {
        return Integer.parseInt(title1)/Integer.parseInt(title2);
    }

   }