package utils.itext7;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 4:20 PM 2019/7/17
 * @Modified By:
 */
public class MyLine implements ILineDrawer {
    private float lineWidth = 1;
    private float offset = 5;
    private Color color = Color.BLACK;
    @Override
    public void draw(PdfCanvas canvas, Rectangle drawArea) {
        canvas.saveState()
                .setStrokeColor(color)
                .setLineWidth(lineWidth)
                .moveTo(drawArea.getX(), drawArea.getY() + lineWidth / 2 + offset)
                .lineTo(drawArea.getX() + drawArea.getWidth(), drawArea.getY() + lineWidth / 2 + offset)
                .stroke()
                .restoreState();
    }

    @Override
    public float getLineWidth() {
        return lineWidth;
    }
    @Override
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    public float getOffset() {
        return offset;
    }
    public void setOffset(float poffset) {
        this.offset = offset;
    }
}
