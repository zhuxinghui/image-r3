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
package org.rivu.image.web.listener;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.rivu.image.core.RivuContext;
import org.rivu.image.tools.db.GenDAO;

/**
 * @author jaddy0302 Rivulet WebApplicationContextListener.java 2010-3-3
 * 
 */
public class WebApplicationContextListener implements HttpSessionListener,
	ServletContextListener, ServletContextAttributeListener {
/**
* 
*/
public void contextInitialized(ServletContextEvent sce) {
		RivuContext.setContextPath(sce.getServletContext().getRealPath(""));
		Logger.getAnonymousLogger().info(GenDAO.class.getName());
		RivuContext.SAVE_FILE_DIR = sce.getServletContext().getRealPath(RivuContext.FILE_URL) ;
		RivuContext.SAVE_DB_DIR = sce.getServletContext().getRealPath(RivuContext.FILE_DB_URL) ;
		File file = new File(RivuContext.SAVE_DB_DIR ,"db") ;
		if(!file.exists()){
			file.mkdirs() ;
		}
	}
	
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			GenDAO.close() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
