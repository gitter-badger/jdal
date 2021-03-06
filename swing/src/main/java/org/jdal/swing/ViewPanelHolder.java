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
package org.jdal.swing;

import javax.swing.JComponent;

import org.jdal.swing.View;

/**
 * PanelHolder implementation for Views
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 */
public class ViewPanelHolder extends PanelHolder {

	public ViewPanelHolder() {
		
	}
	
	public ViewPanelHolder(View<?> view) {
		super();
		this.view = view;
	}

	private View<?> view;

	/**
	 * @return the view
	 */
	public View<?> getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View<?> view) {
		this.view = view;
	}

	@Override
	public JComponent getPanel() {
		return view.getPanel();
	}

	@Override
	public String getName() {
		return super.getName() != null ? super.getName() : 
			messageWrapper.getMessage(view.getName());
	}
}
