package info.joseluismartin.vaadin.ui.table;

import info.joseluismartin.dao.Page;
import info.joseluismartin.dao.PageChangedEvent;
import info.joseluismartin.dao.PaginatorListener;
import info.joseluismartin.service.PersistentService;
import info.joseluismartin.vaadin.ui.Box;

import java.io.Serializable;

import com.vaadin.data.Container;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;


/**
 * <p>
 * Vaadin Table with paginator. Use a BeanItemContainer as 
 * table datasource.
 * </p>
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 */
// FIXME: May be better extends ConfigurableTable, review it.
public class PageableTable<T> extends CustomComponent implements PaginatorListener, 
	Container.ItemSetChangeListener {

	private static final long serialVersionUID = 1L;
	
	private ConfigurableTable table;
	private VaadinPaginator<T> paginator;
	private PersistentService<T, Serializable>  service;
	private Page<T> page;
	private BeanItemContainer<T> container;
	
	public void init() {
		// get initial page and wrap data in container
		paginator.addPaginatorListener(this);
		loadPage();
		// set external sorting, ie do't call Container.sort()
		table.setSorter(new PageSorter());
	
		// build Componenet
		VerticalLayout vbox = new VerticalLayout();
		vbox.addComponent(table);
		Box.addVerticalStruct(vbox, 5);
		paginator.getComponent().setWidth(table.getWidth() + "px");
		vbox.addComponent(paginator.getComponent());
	
		this.setCompositionRoot(vbox);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pageChanged(PageChangedEvent event) {
		table.setPageLength(page.getPageSize());
		loadPage();
	}

	/**
	 * Load models from page and add to bean item container
	 */
	private void loadPage() {
		page = service.getPage(paginator.getModel());
		if (page.getData() != null && page.getData().size() > 0) {
			if (container == null) {
				container = new BeanItemContainer<T>(page.getData());
				table.setContainerDataSource(container);
			}
			else {
				container.removeAllItems();
				for (T bean : page.getData()) {
					container.addBean(bean);
				}
			}
		}
		
		paginator.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void containerItemSetChange(ItemSetChangeEvent event) {
		paginator.refresh();
	}
	
	public ConfigurableTable getTable() {table.removeAllItems();
		return table;
	}

	public void setTable(ConfigurableTable table) {
		this.table = table;
	}

	public VaadinPaginator<T> getPaginator() {
		return paginator;
	}

	public void setPaginator(VaadinPaginator<T> paginator) {
		this.paginator = paginator;
	}

	public PersistentService<T, Serializable> getService() {
		return service;
	}

	public void setService(PersistentService<T, Serializable> service) {
		this.service = service;
	}
	
	class PageSorter implements TableSorter {
		
		@Override
		public void sort(Object[] propertyId, boolean[] ascending) {
			page.setSortName(propertyId[0].toString());
			page.setOrder(ascending[0] ? Page.Order.ASC : Page.Order.DESC);
			loadPage();
		}
	}
}