package com.company.testcascade.web.screens.customer;

import com.company.testcascade.entity.Order;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.testcascade.entity.Customer;

import javax.inject.Inject;

@UiController("testcascade_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {

    @Inject
    private InstanceContainer<Customer> customerDc;
    @Inject
    private CollectionContainer<Order> ordersDc;
    @Inject
    private CollectionLoader<Order> ordersDl;
    @Inject
    private Table<Order> ordersTable;
    @Inject
    private InstanceLoader<Customer> customerDl;
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        customerDl.load();
        ordersDl.setParameter("customer", getEditedEntity());
        ordersDl.load();
    }


    public void onAddOrderClick() {
            ItemsForOrder screen = screenBuilders.screen(this)
                    .withLaunchMode(OpenMode.THIS_TAB)
                    .withScreenClass(ItemsForOrder.class)
                    .withAfterCloseListener(e -> {
                                ordersDl.load();
                                ordersTable.refresh();
                                customerDl.load();
                            }
                    )
                    .build();
            screen.setCustomer(getEditedEntity());
            screen.show();
    }
}