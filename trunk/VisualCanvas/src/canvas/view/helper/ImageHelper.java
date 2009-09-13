/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package canvas.view.helper;

import canvas.utils.Tool;
import canvas.view.widget.XObjectScene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.netbeans.api.visual.model.ObjectScene;
import org.openide.util.Exceptions;

/**
 *
 * @author Isaac
 */
public class ImageHelper {

    private static final Logger logger = Logger.getLogger(ImageHelper.class.getName());
    
    public static final String[] ACCEPTED_EXTENSIONS = (String[]) ArrayUtils.addAll(ImageIO.getReaderFormatNames(), new String[] { "tif", "tiff" });
    
    public static BufferedImage toBufferedImage(String base64Text) {
        BufferedImage result = null;
        try {
            if (base64Text == null) {
                result = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            }
            result = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(base64Text.getBytes())));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }
    
    /**
     * Convert ObjectScene to base64 text
     * @param scene used to convert
     * @return base64 text, or empty string if ObjectScene.getBounds().isEmpty() is true or anything wrong.
     */
    public static String convertSceneToBase64(ObjectScene scene) {
        Rectangle bounds = scene.getBounds();
        BufferedImage bimg = null;
        if ( ! bounds.isEmpty() ) {
            bimg = new BufferedImage( bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB );
            scene.paint( bimg.createGraphics() );            
        }
        return toBase64Text(bimg);
    }    
    
    /**
     * Convert BufferedImage to base64 text
     * @param bimg to convert
     * @return base64 text, or empty string if bimg is null or anything wrong.
     */
    public static String toBase64Text(BufferedImage bimg) {
        if (bimg == null) {
            return "";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bimg, "jpg", baos);
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Write image error. bimg : " + bimg , ex);
            return "";
        }
        return Tool.byteToString(Base64.encodeBase64Chunked(baos.toByteArray()));
    }
    
    public static BufferedImage paintXObjectScene(XObjectScene objectScene) {
        BufferedImage result = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
        BufferedImage emptyImage = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
        if ( objectScene == null ) { return result; }
        /* Scene.validate(Graphics2D) used to calculate scene's bounds off screen.
         * Reference: http://www.netbeans.org/nonav/issues/showattachment.cgi/46107/patch104474 */
        Graphics2D emptyGraphics = emptyImage.createGraphics ();
        objectScene.validate(emptyGraphics);
        emptyGraphics.dispose();
        if ( ! objectScene.getBounds().isEmpty()) {
            Rectangle viewBounds = objectScene.convertSceneToView (objectScene.getBounds ());
            result = new BufferedImage (viewBounds.width, viewBounds.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = result.createGraphics ();
            objectScene.paint (graphics);
            graphics.dispose ();
        }
        return result;
    }
    
    private static boolean isImageIOSupport(String fileName) {
        for (String supportFileExtName : ImageIO.getReaderFormatNames()) {
            if ( fileName.endsWith(supportFileExtName) ) {
                return true;
            }
        }
        return false;
    }
    
    public static BufferedImage readImage(File file) {
        if ( file == null ) { return null; }
        BufferedImage bimg = null;
        try {
            if ( isImageIOSupport(file.getName()) ) {
                bimg = ImageIO.read(file);
            } else {
                // do load image if default ImageIO can't load image
                bimg = createBufferedImageByJAI( file );
            }
        } catch (IOException ex) {
            logger.log( Level.WARNING, "Read image file failed: " + file, ex );
            throw new RuntimeException( "Read image file failed: " + file );
        }
        return bimg;
    }
    
    public static BufferedImage createBufferedImageByJAI( File file ) {
        RenderedImage input = JAI.create("fileload", file.getPath());
        BufferedImage bimg = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ((Graphics2D) bimg.getGraphics()).drawRenderedImage(input, new AffineTransform());
        bimg.getGraphics().dispose();        
        return bimg;
    }
    
    public synchronized static boolean isAcceptedImageFile(File file) {
        boolean result = false;
        if ( file == null ) { return false; }
        if ( file.isDirectory() ) { return true; }
        String fileName = file.getName().toLowerCase();
        for ( int i = ACCEPTED_EXTENSIONS.length - 1; i >= 0; i--) {
            if ( fileName.endsWith(ACCEPTED_EXTENSIONS[i]) ) {
                result = true;
            }
        }        
        return result;
    }
    
    public static String retrieveAcceptedChoosedFileDescription() {
        StringBuffer sb = new StringBuffer();
        sb.append("Image Types ");
        sb.append("(");
        for ( int i = 0; i < ACCEPTED_EXTENSIONS.length; i++ ) {
            if ( i != ACCEPTED_EXTENSIONS.length - 1 ) {
                sb.append( "*." + ACCEPTED_EXTENSIONS[i] + "," );
            } else {
                sb.append( "*." + ACCEPTED_EXTENSIONS[i] );
            }
        }
        sb.append(")");
        return sb.toString();        
    }
    
}
