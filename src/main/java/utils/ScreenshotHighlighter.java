package utils;

import org.openqa.selenium.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public class ScreenshotHighlighter {
    private static final String REPORTS_DIR = "test-output/reports/screenshots/";
    public static String highlight(WebDriver driver, WebElement el, String fileName, String note) {

        try {
            // Scroll để element nằm giữa viewport
            if (el != null) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", el
                );
                Thread.sleep(300);
            }

            // Chụp screenshot
            File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage img = ImageIO.read(shot);

            if (el != null) {
                JavascriptExecutor js = (JavascriptExecutor) driver;

                // Lấy bounding box của element (CSS px) + viewport size
                @SuppressWarnings("unchecked")
                Map<String, Number> box = (Map<String, Number>) js.executeScript(
                        "const r = arguments[0].getBoundingClientRect();" +
                                "return {left:r.left, top:r.top, width:r.width, height:r.height, " +
                                "vw: window.innerWidth, vh: window.innerHeight};", el
                );

                double left = box.get("left").doubleValue();
                double top = box.get("top").doubleValue();
                double width = box.get("width").doubleValue();
                double height = box.get("height").doubleValue();
                double vw = box.get("vw").doubleValue();
                double vh = box.get("vh").doubleValue();

                // Scale CSS px -> pixel ảnh
                double scaleX = img.getWidth() / vw;
                double scaleY = img.getHeight() / vh;

                int x = (int) Math.round(left * scaleX);
                int y = (int) Math.round(top * scaleY);
                int w = (int) Math.round(width * scaleX);
                int h = (int) Math.round(height * scaleY);

                // Vẽ khung đỏ
                Graphics2D g2 = img.createGraphics();
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(x, y, w, h);

                if (note != null && !note.isEmpty()) {
                    g2.setFont(new Font("Arial", Font.BOLD, 18));
                    g2.setColor(Color.RED);
                    // Vẽ chữ ngay trên khung
                    g2.drawString(note, x, y - 10);
                }
                g2.dispose();
            }

           // Thư mục screenshots trong test-output
            String dir = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots";
            File outDir = new File(dir);
            if (!outDir.exists()) outDir.mkdirs();
            String finalPath = getUniqueFilePath(dir, fileName);
            ImageIO.write(img, "png", new File(finalPath));
            System.out.println("[ScreenshotHighlighter] Saved: " + finalPath);

            return finalPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getUniqueFilePath(String dir, String baseName) {
        File file = new File(dir, baseName + ".png");
        int count = 1;
        while (file.exists()) {
            file = new File(dir, baseName + "(" + count + ").png");
            count++;
        }
        return file.getAbsolutePath();
    }
}
