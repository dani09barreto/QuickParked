package org.devscite.Utils;

/**
 * View role
 * MASTER is the main view, only one MASTER view can exist, when it dies, all the views die with it
 * SLAVE_UNIQUE, only one view of it's type can exist, multiple SLAVE_UNIQUE views with different types can exist
 * SLAVE, multiple views of multiple types can exist
 */
public enum ViewType {
    MASTER,
    SLAVE_UNIQUE,
    SLAVE
}
