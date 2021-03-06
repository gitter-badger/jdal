/*
 * Copyright 2009-2014 Jose Luis Martin
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
package org.jdal.vaadin.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * Adapter to make any component a Vaadin View
 * 
 * @author Jose Luis Martin
 * @since 1.0
 */
public class ComponentViewAdapter extends CustomComponent implements View {
	
	public ComponentViewAdapter(Component component) {
		if (component.getParent() != null) {
			component.setParent(null);
		}
		
		setCompositionRoot(component);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// do nothing
	}
}
