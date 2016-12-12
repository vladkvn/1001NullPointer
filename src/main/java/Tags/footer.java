package Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
* Created by vladkvn on 06.12.2016.
*/
public class footer extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print( "<table style=\"width: 75%\" align=\"center\">" );
            pageContext.getOut().print( "<tr>" );
            pageContext.getOut().print( "<td>" );
            pageContext.getOut().print( "<form method=\"post\" action=\"/myInfo\">" );
            pageContext.getOut().print( "<input style=\"height:100%; width:100%\" type=\"submit\" value=\"Профиль\">" );
            pageContext.getOut().print( "</form>" );
            pageContext.getOut().print( "</td>" );
            pageContext.getOut().print( "<td>" );
            pageContext.getOut().print( "<form method=\"get\" action=\"/my_Con\">" );
            pageContext.getOut().print( "<input style=\"height:100%; width:100%\" type=\"submit\" value=\"Контракты\">" );
            pageContext.getOut().print( "</form>" );
            pageContext.getOut().print( "</td>" );
            pageContext.getOut().print( "<td>" );
            pageContext.getOut().print( "<form method=\"post\" action=\"/exit\">" );
            pageContext.getOut().print( "<input style=\"height:100%; width:100%\" type=\"submit\" value=\"Выход\">" );
            pageContext.getOut().print( "</td>" );
            pageContext.getOut().print( "</form>" );
            pageContext.getOut().print( "</tr>" );
            pageContext.getOut().print( "</table>" );
        }
        catch(IOException io) {
            io.printStackTrace();
        }
        return SKIP_BODY;
    }
}
