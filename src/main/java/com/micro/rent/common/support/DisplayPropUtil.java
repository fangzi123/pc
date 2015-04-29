package com.micro.rent.common.support;

import java.util.Properties;

public class DisplayPropUtil {
    private static Properties displayColumn = null;
    private static Properties displaySelect = null;

    public static Properties getDisplayProp() throws Exception {
        if (displayColumn == null) {
            displayColumn = new Properties();
            displayColumn.load(DisplayPropUtil.class.getClassLoader()
                    .getResourceAsStream("display/display_column.properties"));

        }

        return displayColumn;
    }

    public static Properties getDisplaySelectProp() throws Exception {
        if (displaySelect == null) {
            displaySelect = new Properties();
            displaySelect.load(DisplayPropUtil.class.getClassLoader()
                    .getResourceAsStream("display/display_select.properties"));

        }

        return displaySelect;
    }
}
