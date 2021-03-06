/*
 * Copyright 2008-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdal.beans;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * Minimalistic singleton with application context
 * 
 * @author Jose Luis Martin
 * @since 1.0
 */
public class AppCtx {
	
	/** spring application context */
	private static ClassPathXmlApplicationContext context = null;
	/** init properties */
	private static PropertySource<?> propertySource;
	private static String config;
	
	/** 
	 * Search on classpath for context definition files and return the application context
	 * @return the ApplicationContext
	 */
	public synchronized static ApplicationContext getInstance() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext();
			if (propertySource != null) {
				context.getEnvironment().getPropertySources().addFirst(propertySource);
			}
			String location = "classpath*:/" + (config != null ? config + "/" : "") + "applicationContext*.xml";
			context.setConfigLocation(location);
			context.refresh();
		}
		
		return context;
	}
	
	public synchronized static void setProperties(String name, Properties properties) {
		propertySource = new PropertiesPropertySource(name, properties);
	}
	
	public static void setConfigPackage(String config) {
		AppCtx.config = config;
	}
}
