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
package org.rivu.image.web.servlet;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
import org.apache.struts2.ServletActionContext;
import org.rivu.image.web.ServletHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author jaddy0302 Rivulet ServletInterceptor.java 2010-3-3
 * 
 */
public class ServletInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	private PlatformTransactionManager transactionManager = null;
	private WebApplicationContext wac = null;
	private String ret = null;


	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Object o = ai.getAction();
		/**
		 * 用户代码
		 */
		if (o instanceof ServletHandler) {
			/**
			 * Request
			 */
			((ServletHandler) o).setServletRequest(ServletActionContext
					.getRequest());
			/**
			 * Response
			 */
			((ServletHandler) o).setServletResponse(ServletActionContext
					.getResponse());
			
			((ServletHandler) o).setAction(ai.getProxy().getNamespace(), ai.getProxy().getActionName());
			ServletActionContext.getResponse().setHeader("Pragma","No-cache"); 
			ServletActionContext.getResponse().setHeader("Cache-Control","no-cache");
			ServletActionContext.getResponse().setHeader("Cache-Control", "no-store");
			ServletActionContext.getResponse().setDateHeader("Expires", 0); 
		}
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		try {
			ret = ai.invoke();
		} catch (Exception e) {
			e.printStackTrace() ;
			ServletActionContext.getResponse().addHeader("emsg", e.getMessage()) ;
			ServletActionContext.getResponse().sendError(400);
			return null ;
		}
		return ret;
	}
	
}
