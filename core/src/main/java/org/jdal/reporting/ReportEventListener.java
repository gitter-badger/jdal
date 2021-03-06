/*
 * Copyright 2008-2011 the original author or authors.
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
package org.jdal.reporting;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Interface to be implemented by those objects that need to know about the changes in a report
 * @author Jose A. Corbacho
 *
 */
public interface ReportEventListener extends EventListener {

	/**
	 * Notify changes in a report
	 * @param event
	 */
	public void reportChanged(EventObject event);
}
