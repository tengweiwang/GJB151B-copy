package utils.itext7;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.property.TabAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 4:15 PM 2019/10/12
 * @Modified By:
 */
public class BodyParagraph1 extends Paragraph {
    private static final PageSize pageSize = PageSize.A4;
    private static final float width = pageSize.getWidth();

    public BodyParagraph1(String text, PdfFont font, int fontSize) {
        if (text == null)
            text = "";
        text = "\ua0a0\ua0a0" + text;
        List<TabStop> tabstops = new ArrayList();
        tabstops.add(new TabStop(width, TabAlignment.LEFT));
        super.addTabStops(tabstops);
        super.add(new Tab()).add(text).add(new Tab());
        super.setFont(font);
        super.setFontSize(fontSize);
    }

    public BodyParagraph1() {
        List<TabStop> tabstops = new ArrayList();
        tabstops.add(new TabStop(width, TabAlignment.LEFT));
        super.addTabStops(tabstops);
    }
}
