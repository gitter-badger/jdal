/*
 * Copyright 2009-2011 Jose Lus Martin.
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
package org.jdal.swing.bind;

import javax.swing.JToggleButton;

import org.jdal.ui.bind.AbstractBinder;

/**
 * Property binder for ToggleButtons
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 */
public class ToggleButtonBinder extends AbstractBinder {

	/**
	 * {@inheritDoc}
	 */
	public void doRefresh() {
		((JToggleButton) component).setSelected((Boolean) getValue() == null ?
				Boolean.FALSE : (Boolean) getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	public void doUpdate() {
		setValue(((JToggleButton) component).isSelected());
	}
	
}
