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
package canvas;
/**
 *
 * @author Isaac
 */
public interface XCanvasGlobal {

    public enum OK_CANCEL { OK, CANCEL };
    public static final String IMAGE_FILE_PATH = "C:/vghtc/draw";
    public static final String TAG_NAME = "CANVAS";

    /** 
     * This url will put in XCanvasReporter's printed string, which has img tag and src attribute.<br/>
     * "iqnote" will be set in web.xml to direct iq-note servlets.<br/>
     * "service" will be set in FrontControllerServlet, "ImageServlet" will direct to ImageServletHelper.<br/>
     * "path" is a fake image path end with .png to tell ImageServlet that it's png image, <br/>
     * it's because XCanvas's reporter reports dynamic image edited by user.<br/>
     * "ImageServletHelper.PARAM_SERVLET_IMAGE_PAINTER_IMPL_CLS_NAME" specify ServletImagePainter's concrete class, XCanvasReporter is the one.
     */
    public static final String SERVLET_URL = "iqnote?service=imageServiceServlet";    //" + ImageServletHelper.PARAM_PATH + "=dynamicImage.png&" + ImageServletHelper.PARAM_SERVLET_IMAGE_PAINTER_IMPL_CLS_NAME + "=" + XCanvasHtmlReporter.class.getName();
    
}
