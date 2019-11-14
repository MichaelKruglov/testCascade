package com.company.testcascade.web.screens.customer;

import com.company.testcascade.entity.Customer;
import com.company.testcascade.entity.Item;
import com.company.testcascade.entity.Order;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("testcascade_ItemsForOrder")
@UiDescriptor("items-for-order.xml")
@LoadDataBeforeShow
public class ItemsForOrder extends Screen {
    @Inject
    private CollectionContainer<Item> itemsDc;
    @Inject
    private CollectionLoader<Item> itemsDl;
    @Inject
    private Table<Item> itemsTable;
    @Inject
    private Metadata metadata;
    private Customer customer;
    @Inject
    private DataManager dataManager;

    public void onChooseButtonClick() {
        Order order = metadata.create(Order.class);
        order.setItem(itemsTable.getSingleSelected());
        order.setCustomer(customer);
        dataManager.commit(order);  //Временное решение! Нужно, чтобы все коммитилось при закрытии внешнего редактора (CustomerEdit)
        super.closeWithDefaultAction();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void onCancelClick() {
        super.closeWithDefaultAction();
    }
}