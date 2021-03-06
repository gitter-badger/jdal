/*
 * CCopyright 2009-2011 original author or authors.
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


import java.util.ArrayList;

import org.jdal.dao.PageChangedEvent;
import org.jdal.dao.Paginator;
import org.jdal.dao.PaginatorListener;


/**
 * Default implementation of Paginator.
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 * @see org.jdal.swing.PaginatorView
 */
public class DefaultPaginator  implements Paginator {

	/** Number of total records */
	private int totalRecords;
	/** Page index number */
	private int page = 1;
	/** Number of records per page */
	private int pageSize = 10;
	
	private ArrayList<PaginatorListener> listeners = new ArrayList<PaginatorListener>();
	
	
	/**
	 * {@inheritDoc}
	 */
	public int getCount() {
		return totalRecords;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		return page < getTotalPages();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasPage(int indexPage) {
		return indexPage <= getTotalPages() &&  indexPage > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasPrevious() {
		return page > 1;
	}

	/**
	 *  {@inheritDoc}
	 */
	public void setPage(int indexPage) {
		page = indexPage;
		firePageChangedEvent();
		
	}

	/**
	 * {@inheritDoc}
	 */
	public int getStartIndex() {
		return (page - 1)*pageSize;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getTotalPages() {
		if (pageSize > 0) {
			
			return totalRecords/pageSize + (totalRecords%pageSize == 0 ? 0 : 1);
		}
		return 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addPaginatorListener(PaginatorListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removePaginatorListener(PaginatorListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setCount(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			// need to recalculate current page
			page = (int) Math.ceil(getStartIndex()/pageSize) + 1;
			this.pageSize = pageSize;
			firePageChangedEvent();
		}
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	public void firstPage() {
		setPage(1);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void lastPage() {
		setPage(getTotalPages());
	}

	/**
	 * {@inheritDoc}
	 */
	public void nextPage() {
			setPage(page + 1);
		
	}

	/**
	 * {@inheritDoc}
	 */

	public void previousPage() {
			setPage(page - 1);
	}
	
	/**
	 * Notify Listener that current page changed
	 */
	private void firePageChangedEvent() {
		for (PaginatorListener listener : listeners) {
			listener.pageChanged(new PageChangedEvent(this, page, getStartIndex(), getTotalPages(), pageSize));
		}
	}
	
}
