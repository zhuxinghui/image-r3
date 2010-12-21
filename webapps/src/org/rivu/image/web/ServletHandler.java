/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rivu.image.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
/**
 * @author jaddy0302 Rivulet ServletHandler.java 2010-3-3
 * 
 */
public interface ServletHandler {
	/**
     * Request
     * @param request HttpServletRequest
     * @throws Exception
     */
    public void setServletRequest(HttpServletRequest request) throws Exception;
    /**
     * Response
     * @param response HttpServletResponse
     * @throws Exception
     */
    public void setServletResponse(HttpServletResponse response) throws Exception;
    /**
     * 
     */
    public void setAction(String namespace , String action) ;
    
}
