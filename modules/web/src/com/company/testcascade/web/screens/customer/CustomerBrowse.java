package com.company.testcascade.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.company.testcascade.entity.Customer;

@UiController("testcascade_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}