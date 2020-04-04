package utils.itext7;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 9:48 PM 2019/7/18
 * @Modified By:
 */
public class PageFooter extends MidParagraph {
    private static PdfFont font;

    static {
        try {
            font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PageFooter(Integer pageNumber) {
        super(String.valueOf(pageNumber), font, 14);
        super.setFixedPosition(200, 30, 100);

    }
}
